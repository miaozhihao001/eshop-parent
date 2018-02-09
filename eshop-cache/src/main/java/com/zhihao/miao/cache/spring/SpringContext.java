package com.zhihao.miao.cache.spring;

import org.springframework.context.ApplicationContext;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * spring上下文
 * @Date :2018/2/9
 * @since 1.0
 */
public class SpringContext {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.applicationContext = applicationContext;
    }
}
