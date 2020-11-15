package com.dao;

import com.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginDao {

    //根据用户名查询用户
    @Select("select * from user where user_name=#{userName}")
    @Results({
            @Result(property = "userId",column = "user_id",id = true),
            @Result(property = "info",column = "user_id",javaType = com.entity.Info.class,one = @One(select = "com.dao.LoginDao.findInfoByUserId")),
            @Result(property = "roles",column = "user_id",javaType = List.class,many = @Many(select = "com.dao.LoginDao.findUserRolesByUserId")),
            @Result(property = "orders",column = "user_id",javaType = List.class,many = @Many(select = "com.dao.LoginDao.findOrderByUserId"))
    })
    Users userLogin(String userName);

    //根据用户id查询用户认证信息
    @Select("select * from user_info where user_id=#{userId}")
    Info findInfoByUserId(Integer userId) throws Exception;

    //根据用户id查询用户角色
    @Select("select * from user_role where role_id in (select role_id from user_and_role where user_id=#{userId})")
    List<UserRole> findUserRolesByUserId(Integer userId) throws Exception;

    //根据用户id查询用户订单
    @Select("select * from `order` where order_id in (select order_id from user_order where sell_id=#{userId} or buy_id=#{userId})")
    List<Order> findOrderByUserId(Integer userId) throws Exception;

    //根据管理员名称查询管理员
    @Select("select * from `admin` where admin_name=#{adminName}")
    @Results({
            @Result(id = true,property = "adminId",column = "admin_id"),
            @Result(property = "adminRoles",column = "admin_id",javaType = List.class,many = @Many(select = "com.dao.LoginDao.findAdminRoleByAdminId"))
    })
    Admin adminLogin(String adminName);

    //根据管理员id查询管理员角色
    @Select("select * from admin_role where adminRole_id in (select role_id from admin_and_role where admin_id=#{adminId})")
    List<AdminRole> findAdminRoleByAdminId(Integer adminId) throws Exception;

    @Select("select admin_name from admin where admin_name=#{adminName}")
    String countAdmin(String adminName);

    @Select("select user_name from user where user_name=#{userName}")
    String countUser(String userName);

}
