package com.zhihao.miao.shop.service;

import com.zhihao.miao.shop.request.Request;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * 请求异步执行的service
 * @Date :2018/2/6
 * @since 1.0
 */
public interface RequestAsyncProcessService {

    void process(Request request);
}
