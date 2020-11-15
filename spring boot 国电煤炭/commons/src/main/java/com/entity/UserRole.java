package com.entity;

/**
 * 用户角色表：user_role
 */
public class UserRole {
    //主键 自增 角色id
    private Integer roleId;
    //角色名
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
