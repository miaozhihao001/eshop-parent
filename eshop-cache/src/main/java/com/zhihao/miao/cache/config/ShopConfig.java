package com.zhihao.miao.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/6
 * @since 1.0
 */
@Configuration
public class ShopConfig {

    @Value("${redis.master.first.host}")
    private String redis_master_first_host;

    @Value("${redis.master.first.port}")
    private int redis_master_first_port;

    @Value("${redis.master.second.host}")
    private String redis_master_second_host;

    @Value("${redis.master.second.port}")
    private int redis_master_second_port;

    @Value("${redis.master.third.host}")
    private String redis_master_third_host;

    @Value("${redis.master.third.port}")
    private int redis_master_third_port;

    @Bean
    public JedisCluster JedisClusterFactory() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort(redis_master_first_host, redis_master_first_port));
        jedisClusterNodes.add(new HostAndPort(redis_master_second_host, redis_master_second_port));
        jedisClusterNodes.add(new HostAndPort(redis_master_third_host, redis_master_third_port));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        return jedisCluster;
    }
}
