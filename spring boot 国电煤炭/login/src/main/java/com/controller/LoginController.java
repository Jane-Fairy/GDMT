package com.controller;

import com.entity.Admin;
import com.entity.ResultBean;
import com.entity.Users;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/admin/logined/{name}")
    @CrossOrigin
    public ResultBean hasAdminLogin (@PathVariable String name){
        try {
            Admin admin=loginService.adminLogin(name);
            return new ResultBean(200,"登录成功",admin);
        }catch (Exception e){
            return new ResultBean(400,"登录异常");
        }
    }

    @RequestMapping("/user/logined/{name}")
    @CrossOrigin
    public ResultBean hasUserLogin (@PathVariable String name){
        try {
            Users users=loginService.userLogin(name);
            if (!users.getUserState().equals(2)){
                return new ResultBean(200,"登录成功",users);
            }
        }catch (Exception e){
            return new ResultBean(400,"登录异常");
        }
        return new ResultBean(400,"该账号已禁用");
    }

}
