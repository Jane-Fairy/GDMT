package com.entity;

import java.io.Serializable;

/**
 * 物流订单
 */
public class Torder implements Serializable {
    private Integer torderId;
    //物流公司ID
    private Integer userId;
    //卖方ID
    private Integer sellerId;
    //物流订单状态（0：已揽件，1：已签收，2：停滞）
    private Integer torderState;
    //物流追踪
    private String torderInfo;
    //物流公司联系电话
    private String phone;

    public Integer getTorderId() {
        return torderId;
    }

    public void setTorderId(Integer torderId) {
        this.torderId = torderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getTorderState() {
        return torderState;
    }

    public void setTorderState(Integer torderState) {
        this.torderState = torderState;
    }

    public String getTorderInfo() {
        return torderInfo;
    }

    public void setTorderInfo(String torderInfo) {
        this.torderInfo = torderInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
