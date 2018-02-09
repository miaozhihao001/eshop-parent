package com.zhihao.miao.cache.service;

import com.zhihao.miao.cache.model.ProductInfo;
import com.zhihao.miao.cache.model.ShopInfo;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 *  缓存service接口
 * @Date :2018/2/8
 * @since 1.0
 */
public interface CacheService {

    /**
     * 将商品信息保存到本地缓存中
     * @param productInfo
     * @return
     */
    public ProductInfo saveLocalCache(ProductInfo productInfo);

    /**
     * 从本地缓存中获取商品信息
     * @param id
     * @return
     */
    public ProductInfo getLocalCache(Long id);

    /**
     * 将商品信息保存到本地的ehcache缓存中
     * @param productInfo
     */
    public ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo);

    /**
     * 从本地ehcache缓存中获取商品信息
     * @param productId
     * @return
     */
    public ProductInfo getProductInfoFromLocalCache(Long productId);

    /**
     * 将店铺信息保存到本地的ehcache缓存中
     *
     * @param shopInfo
     * @return
     */
    public ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo);

    /**
     * 从本地ehcache缓存中获取店铺信息
     * @param shopId
     * @return
     */
    public ShopInfo getShopInfoFromLocalCache(Long shopId);

    /**
     * 将商品信息保存到redis中
     * @param productInfo
     */
    public void saveProductInfo2ReidsCache(ProductInfo productInfo);

    /**
     * 将店铺信息保存到redis中
     * @param shopInfo
     */
    public void saveShopInfo2ReidsCache(ShopInfo shopInfo);
}
