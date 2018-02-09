package com.zhihao.miao.cache.controller;

import com.zhihao.miao.cache.model.ProductInfo;
import com.zhihao.miao.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * 缓存Controller
 * @Date :2018/2/8
 * @since 1.0
 */
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/testPutCache")
    public String testPutCache(ProductInfo productInfo) {
        cacheService.saveLocalCache(productInfo);
        return "success";
    }

    @RequestMapping("/testGetCache")
    public ProductInfo testGetCache(Long id) {
        return cacheService.getLocalCache(id);
    }
}
