package com.zhihao.miao.cache.listener;

import com.zhihao.miao.cache.kafka.KafkaConsumer;
import com.zhihao.miao.cache.spring.SpringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/9
 * @since 1.0
 */
public class InitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        SpringContext.setApplicationContext(context);
        //参数是kakfa的主题
        new Thread(new KafkaConsumer("cache-message")).start();
    }
}
