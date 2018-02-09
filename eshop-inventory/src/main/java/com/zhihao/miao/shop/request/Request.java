package com.zhihao.miao.shop.request;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/6
 * @since 1.0
 */
public interface Request {

    void process();
    Integer getProductId();
    boolean isForceRefresh();
}
