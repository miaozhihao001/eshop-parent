package com.zhihao.miao.shop.controller;

import com.zhihao.miao.shop.model.ProductInventory;
import com.zhihao.miao.shop.request.ProductInventoryCacheRefreshRequest;
import com.zhihao.miao.shop.request.ProductInventoryDBUpdateRequest;
import com.zhihao.miao.shop.request.Request;
import com.zhihao.miao.shop.service.ProductInventoryService;
import com.zhihao.miao.shop.service.RequestAsyncProcessService;
import com.zhihao.miao.shop.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/6
 * @since 1.0
 */
@RestController
public class ProductInventoryController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;

    @Autowired
    private ProductInventoryService productInventoryService;

    /**
     * 更新商品库存,直接将请求放入到相应的内存队列中去了
     */
    @RequestMapping("/updateProductInventory")
    public Response updateProductInventory(ProductInventory productInventory) {

        logger.info("===========日志===========: 接收到更新商品库存的请求，商品id=" + productInventory.getProductId()
                + ", 商品库存数量=" + productInventory.getInventoryCnt());

        Response response;

        try {
            Request request = new ProductInventoryDBUpdateRequest(
                    productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }

        return response;
    }

    /**
     * 获取商品库存
     *
     * 1.先将该商品放入到相应的内存队列中（根据商品id进行路由）
     * 2.让后尝试去redis中去读取，读到就返回，查询为null就等待20ms
     * 3.判断总的等待时间是否大于200ms（也就是hang住10次左右），小于200ms继续去redis去读取
     * 4.大于200ms还是没有读到缓存值就去数据库中去读取一次，有直接返回，没有返回null
     */
    @RequestMapping("/getProductInventory")
    public ProductInventory getProductInventory(Integer productId) {
        logger.info("===========日志===========: 接收到一个商品库存的读请求，商品id=" + productId);

        ProductInventory productInventory = null;

        try {
            Request request = new ProductInventoryCacheRefreshRequest(
                    productId, productInventoryService, false);
            requestAsyncProcessService.process(request);

            // 将请求扔给service异步去处理以后，就需要while(true)一会儿，在这里hang住
            // 去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作，将最新的数据刷新到缓存中
            long startTime = System.currentTimeMillis();
            long endTime = 0L;
            long waitTime = 0L;

            // 等待超过200ms没有从缓存中获取到结果
            while(true) {
//				if(waitTime > 25000) {
//					break;
//				}

                // 一般公司里面，面向用户的读请求控制在200ms就可以了
                if(waitTime > 200) {
                    break;
                }

                // 尝试去redis中读取一次商品库存的缓存数据
                productInventory = productInventoryService.getProductInventoryCache(productId);

                // 如果读取到了结果，那么就返回
                if(productInventory != null) {
                    logger.info("===========日志===========: 在200ms内读取到了redis中的库存缓存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
                    return productInventory;
                }

                // 如果没有读取到结果，那么等待一段时间
                else {
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }

            // 直接尝试从数据库中读取数据
            productInventory = productInventoryService.findProductInventory(productId);
            if(productInventory != null) {
                // 将缓存刷新一下
                // 这个过程，实际上是一个读操作的过程，但是没有放在队列中串行去处理，还是有数据不一致的问题，
                //所以这时候还是去往队列中放一个request去异步执行，并且强制刷新读请求(设置值为true)
                request = new ProductInventoryCacheRefreshRequest(
                        productId, productInventoryService, true);
                requestAsyncProcessService.process(request);

                // 代码会运行到这里，只有三种情况：
                // 1、就是说，上一次也是读请求，数据刷入了redis，但是redis LRU算法给清理掉了，标志位还是false
                // 所以此时下一个读请求是从缓存中拿不到数据的，再放一个读Request进队列，让数据去刷新一下
                // 2、可能在200ms内，就是读请求在队列中一直积压着，没有等待到它执行（在实际生产环境中，基本是比较坑了）
                // 所以就直接查一次库，然后给队列里塞进去一个刷新缓存的请求
                // 3、数据库里本身就没有，缓存穿透，穿透redis，请求到达mysql库

                return productInventory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ProductInventory(productId, -1L);
    }
}
