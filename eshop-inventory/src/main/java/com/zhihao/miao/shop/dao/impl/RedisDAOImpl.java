package com.zhihao.miao.shop.dao.impl;

import com.zhihao.miao.shop.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/6
 * @since 1.0
 */
@Repository("redisDao")
public class RedisDAOImpl implements RedisDao {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public void delete(String key) {
        jedisCluster.del(key);
    }
}
