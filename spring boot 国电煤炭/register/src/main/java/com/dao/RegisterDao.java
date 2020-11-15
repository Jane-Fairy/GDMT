package com.dao;

import com.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author Harlan
 * @Date 2020/10/29
 */
@Repository
public interface RegisterDao {

    /**
     * 用户注册
     * @param users 用户信息
     * @return 数据库主键
     */
    @Insert("insert into user(user_name, user_password, user_state, create_time, modify_time) " +
            "values(#{userName}, #{userPassword}, #{userState}, #{createTime}, #{modifyTime})")
    @Options(useGeneratedKeys = true, keyColumn = "user_id")
    int registerUsers(Users users);

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    @Select("select * from user where user_name = #{userName}")
    Users findUserByUsername(String username);

    /**
     * 通过用户id查询用户
     * @param userId 用户id
     * @return 用户信息
     */
    @Select("select * from user where user_id = #{userId}")
    Users selectUserById(Integer userId);

    /**
     * 通过用id修改密码
     * @param user 用户
     * @return 受影响行数
     */
    @Update("update user set user_password = #{userPassword} , modify_time = #{modifyTime} where user_id = #{userId}")
    int updateUserById(Users user);
}
