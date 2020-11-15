package com.service;

/**
 * @Author Harlan
 * @Date 2020/10/29
 */
public interface RegisterService {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 成功/失败
     */
     boolean registerUser(String username, String password);

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return true(存在)
     */
     boolean userNameExist(String username);


    /**
     * 通过用户id判断存在
     * @param userId 用户id
     * @return 是否存在
     */
    boolean existUserById(Integer userId);

    /**
     * 通过用户id修改密码
     * @param userId 用户id
     * @param password 新密码
     * @return 是否成功
     */
    boolean modifyPasswordById(Integer userId, String password);
}
