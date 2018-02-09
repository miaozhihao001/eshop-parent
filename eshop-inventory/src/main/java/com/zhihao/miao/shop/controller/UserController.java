package com.zhihao.miao.shop.controller;

import com.zhihao.miao.shop.model.User;
import com.zhihao.miao.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/6
 * @since 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    public User getUserInfo() {
        User user = userService.findUserInfo();
        return user;
    }

    @RequestMapping("/getCachedUserInfo")
    public User getCachedUserInfo() {
        User user = userService.getCachedUserInfo();
        return user;
    }
}
