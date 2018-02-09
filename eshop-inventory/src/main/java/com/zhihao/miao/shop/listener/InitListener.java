package com.zhihao.miao.shop.listener;

import com.zhihao.miao.shop.thread.RequestProcessorThreadPool;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/6
 * @since 1.0
 */
@Component
public class InitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 初始化工作线程池和内存队列
        RequestProcessorThreadPool.init();
    }
}
