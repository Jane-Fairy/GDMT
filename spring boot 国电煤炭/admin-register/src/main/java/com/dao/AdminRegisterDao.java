package com.dao;

import com.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author Harlan
 * @Date 2020/11/4
 */
@Repository
public interface AdminRegisterDao {

    /**
     * 保存管理员信息
     * @param admin 管理员信息
     * @return 主键id
     */
    @Insert("insert into admin(admin_name, admin_password, admin_state, create_time, modify_time) values(#{adminName}, #{adminPassword}, #{adminState}, #{createTime}, #{modifyTime})")
    @Options(useGeneratedKeys = true, keyColumn = "admin_id")
    int insertAdmin(Admin admin);

    /**
     * 通过用户名查询管理员
     * @param adminName 用户名
     * @return 管理员信息
     */
    @Select("select * from admin where admin_name = #{adminName}")
    Admin findAdminByName(String adminName);
}
