package com.zhihao.miao.shop.service;

import com.zhihao.miao.shop.model.ProductInventory;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * 商品库存Service接口
 * @Date :2018/2/6
 * @since 1.0
 */
public interface ProductInventoryService {

    //更新商品库存
    void updateProductInventory(ProductInventory productInventory);

    //删除Redis中的商品库存的缓存
    void removeProductInventoryCache(ProductInventory productInventory);

    //根据商品id查询商品库存
    ProductInventory findProductInventory(Integer productId);

    //设置商品库存的缓存
    void setProductInventoryCache(ProductInventory productInventory);

    //获取商品库存的缓存
    ProductInventory getProductInventoryCache(Integer productId);
}
