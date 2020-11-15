package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 寰
 * @create 2020-10-28-16:50
 * 订单表Bean
 */
public class Order implements Serializable {
    private Integer orderId;//订单id
    private String orderNumber;//订单编号   规则:时间戳+商品类型+当前时间天数
    private Integer contractId;//合同id
    private Date orderTime;//订单时间
    private Integer orderState;//订单状态  0:未确认 1:确认 2:失效 3:完成
    private Integer torderId;//物流id
    private Double sellFreeze;//卖方保证金
    private Double buyFreeze;//买方保证金
    private Integer sellState;//卖方状态 0:未发货 1:已发货
    private Integer buyState;//买方状态  0:未付款 1:已付款
    private Users sell;
    private Users buy;
    private Double money;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getTorderId() {
        return torderId;
    }

    public void setTorderId(Integer torderId) {
        this.torderId = torderId;
    }

    public Double getSellFreeze() {
        return sellFreeze;
    }

    public void setSellFreeze(Double sellFreeze) {
        this.sellFreeze = sellFreeze;
    }

    public Double getBuyFreeze() {
        return buyFreeze;
    }

    public void setBuyFreeze(Double buyFreeze) {
        this.buyFreeze = buyFreeze;
    }

    public Integer getSellState() {
        return sellState;
    }

    public void setSellState(Integer sellState) {
        this.sellState = sellState;
    }

    public Integer getBuyState() {
        return buyState;
    }

    public void setBuyState(Integer buyState) {
        this.buyState = buyState;
    }

    public Users getSell() {
        return sell;
    }

    public void setSell(Users sell) {
        this.sell = sell;
    }

    public Users getBuy() {
        return buy;
    }

    public void setBuy(Users buy) {
        this.buy = buy;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
