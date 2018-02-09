package com.zhihao.miao.shop.dao;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/6
 * @since 1.0
 */
public interface RedisDao {

    void set(String key, String value);
    String get(String key);
    void delete(String key);
}
