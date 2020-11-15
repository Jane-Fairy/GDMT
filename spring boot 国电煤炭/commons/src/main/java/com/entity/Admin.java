package com.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 管理员账户表
 */
public class Admin implements Serializable {
    private Integer adminId;
    private String adminName;
    private String adminPassword;
    //管理员状态（0：开启，1：禁用）
    private Integer adminState;
    private Date createTime;
    private Date modifyTime;
    private List<AdminRole> adminRoles;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getAdminState() {
        return adminState;
    }

    public void setAdminState(Integer adminState) {
        this.adminState = adminState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<AdminRole> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(List<AdminRole> adminRoles) {
        this.adminRoles = adminRoles;
    }
}
