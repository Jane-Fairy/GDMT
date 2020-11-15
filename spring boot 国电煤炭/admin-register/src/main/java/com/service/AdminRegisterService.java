package com.service;

import com.entity.Admin;

/**
 * @Author Harlan
 * @Date 2020/11/4
 */
public interface AdminRegisterService {

    /**
     * 注册管理员
     * @param admin 管理员信息
     * @return 是否成功
     */
    Boolean saveAdmin(Admin admin);


    /**
     * 通过用户查询管理员
     * @param adminName 用户名
     * @return 是否存在
     */
    Boolean findAdminByName(String adminName);
}
