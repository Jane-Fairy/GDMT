package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 寰
 * @create 2020-10-28-17:00
 * 合同表
 */
public class Contract implements Serializable {
    private Integer contractId;//合同id
    private Integer goodsId;//商品id
    private Integer sellState;//卖方状态  0:未确认 1:确认
    private Integer buyState;//买方状态  0:未确认 1:确认
    private Integer sellId;//卖方id
    private Integer buyId;//买方id
    private Date contractTime;//合同生成时间
    private Integer contractState;//合同状态  0:有效 1:无效
    private Goods goods;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public Integer getContractState() {
        return contractState;
    }

    public void setContractState(Integer contractState) {
        this.contractState = contractState;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
