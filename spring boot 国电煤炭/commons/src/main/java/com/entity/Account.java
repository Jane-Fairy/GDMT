package com.entity;

import java.io.Serializable;

/**
 * 账户表：account
 */
public class Account implements Serializable {

    //类型：int ，键：主键 ， 非空判断：false ， 默认值：无 ， 描述：账户Id , 备注：无
    private Integer accId;
    //类型：double ， 键：无 ， 非空判断：false ， 默认值 ：无 ， 描述：余额 ， 备注：无
    private Double money;
    //类型：int ， 键：外键 ， 非空判断：false ， 默认值 ：无 ， 描述：用户Id ， 备注：用户Id
    private Integer userId;
    //类型：double ， 键：五 ， 非空判断：false ， 默认值 ：无 ， 描述：冻结资金 ， 备注：
    private Double freeze;

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getFreeze() {
        return freeze;
    }

    public void setFreeze(Double freeze) {
        this.freeze = freeze;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accId=" + accId +
                ", money=" + money +
                ", userId=" + userId +
                ", freeze=" + freeze +
                '}';
    }
}
