package com.zhihao.miao.shop.request;

import com.zhihao.miao.shop.model.ProductInventory;
import com.zhihao.miao.shop.service.ProductInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/6
 * @since 1.0
 */
public class ProductInventoryCacheRefreshRequest implements Request{

    private Logger logger = LoggerFactory.getLogger(getClass());

    //商品id
    private Integer productId;

    //商品库存Service
    private ProductInventoryService productInventoryService;

    //是否强制刷新
    private boolean forceRefresh;

    public ProductInventoryCacheRefreshRequest(Integer productId,
                           ProductInventoryService productInventoryService,
                           boolean forceRefresh) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
        this.forceRefresh=forceRefresh;
    }

    @Override
    public void process() {
        // 从数据库中查询最新的商品库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        logger.info("===========日志===========: 已查询到商品最新的库存数量，商品id=" + productId + ", 商品库存数量=" + productInventory.getInventoryCnt());
        // 将最新的商品库存数量，刷新到redis缓存中去
        productInventoryService.setProductInventoryCache(productInventory);
    }

    public Integer getProductId() {
        return productId;
    }

    public boolean isForceRefresh() {
        return forceRefresh;
    }
}
