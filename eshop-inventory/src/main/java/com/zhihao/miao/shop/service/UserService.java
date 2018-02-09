package com.zhihao.miao.shop.service;

import com.zhihao.miao.shop.model.User;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * 用户Service接口
 * @Date :2018/2/6
 * @since 1.0
 */
public interface UserService {

    //查询用户信息
    User findUserInfo();


    //查询redis中缓存的用户信息
    User getCachedUserInfo();
}
