package com.controller;

import com.entity.Admin;
import com.entity.ResultBean;
import com.service.AdminRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Harlan
 * @Date 2020/11/4
 */
@RestController
public class AdminRegisterController {

    /**
     * 管理员名称正则
     */
    private final String USERNAME_REGEX = "[a-zA-Z0-9_]{4,16}";
    /**
     * 密码正则(8-16, 数字, 大小写)
     */
    private final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    @Autowired
    private AdminRegisterService adminRegisterService;

    /**
     * 管理员注册接口
     * @param admin 管理员信息
     * @return 注册结果
     */
    @PostMapping("admin/register")
    public ResultBean<Boolean> registerAdmin(Admin admin){
        String adminName = admin.getAdminName();
        String adminPassword = admin.getAdminPassword();
        if (adminName == null || adminPassword == null || !adminName.matches(USERNAME_REGEX) || !adminPassword.matches(PASSWORD_REGEX)){
            return new ResultBean<>(200, "信息填写错误", false);
        }else {
            if (adminRegisterService.findAdminByName(adminName)){
                boolean flag = adminRegisterService.saveAdmin(admin);
                if (flag){
                    return new ResultBean<>(200, "注册成功", true);
                }else {
                    return new ResultBean<>(200, "注册失败", false);
                }
            }else {
                return new ResultBean<>(200, "用户名已被注册", false);
            }
        }
    }

}
