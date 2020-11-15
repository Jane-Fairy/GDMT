package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 */
public class Goods implements Serializable {
    private Integer goodsId;
    private Integer userId;
    //煤种
    private String goodsType;
    //0：出售，1：采购
    private Integer infoType;
    //交易类型（0：挂牌，1：公开竞价，2：封闭竞价）
    private Integer tType;
    //交易数量（万吨）
    private Integer goodsNumber;
    //单价（元/吨）
    private Double goodsPrice;
    //状态（0：未审核，1：驳回，2：审核通过，3：下架）
    private Integer goodsState;
    //截止日期
    private Date goodsDate;
    //产地
    private String goodsPlace;
    //交割地
    private String goodsDeli;
    //交货方式（0：到厂交货，1：场地交货）
    private String goodsDeliType;
    //联系人
    private String goodsLinkman;
    //联系电话
    private String goodsTel;
    //低位发热量（kcal/kg）
    private Double goodsQnet;
    //全水分(%)
    private Double goodsMt;
    //收到基全硫（%）
    private Double goodsStar;
    //空干基挥灰分（%）
    private Double goodsVad;
    //收到基灰分（%）
    private Double goodsAar;
    //粒度(mm)
    private Double goodsGran;
    //灰熔点(°C)
    private Double goodsSt;
    //哈氏可磨系数(HGI)
    private Double goodsHac;
    //备注
    private String goodsRemark;
    //保证金
    private Double userFreeze;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public Integer gettType() {
        return tType;
    }

    public void settType(Integer tType) {
        this.tType = tType;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Integer goodsState) {
        this.goodsState = goodsState;
    }

    public Date getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(Date goodsDate) {
        this.goodsDate = goodsDate;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getGoodsDeli() {
        return goodsDeli;
    }

    public void setGoodsDeli(String goodsDeli) {
        this.goodsDeli = goodsDeli;
    }

    public String getGoodsDeliType() {
        return goodsDeliType;
    }

    public void setGoodsDeliType(String goodsDeliType) {
        this.goodsDeliType = goodsDeliType;
    }

    public String getGoodsLinkman() {
        return goodsLinkman;
    }

    public void setGoodsLinkman(String goodsLinkman) {
        this.goodsLinkman = goodsLinkman;
    }

    public String getGoodsTel() {
        return goodsTel;
    }

    public void setGoodsTel(String goodsTel) {
        this.goodsTel = goodsTel;
    }

    public Double getGoodsQnet() {
        return goodsQnet;
    }

    public void setGoodsQnet(Double goodsQnet) {
        this.goodsQnet = goodsQnet;
    }

    public Double getGoodsMt() {
        return goodsMt;
    }

    public void setGoodsMt(Double goodsMt) {
        this.goodsMt = goodsMt;
    }

    public Double getGoodsStar() {
        return goodsStar;
    }

    public void setGoodsStar(Double goodsStar) {
        this.goodsStar = goodsStar;
    }

    public Double getGoodsVad() {
        return goodsVad;
    }

    public void setGoodsVad(Double goodsVad) {
        this.goodsVad = goodsVad;
    }

    public Double getGoodsAar() {
        return goodsAar;
    }

    public void setGoodsAar(Double goodsAar) {
        this.goodsAar = goodsAar;
    }

    public Double getGoodsGran() {
        return goodsGran;
    }

    public void setGoodsGran(Double goodsGran) {
        this.goodsGran = goodsGran;
    }

    public Double getGoodsSt() {
        return goodsSt;
    }

    public void setGoodsSt(Double goodsSt) {
        this.goodsSt = goodsSt;
    }

    public Double getGoodsHac() {
        return goodsHac;
    }

    public void setGoodsHac(Double goodsHac) {
        this.goodsHac = goodsHac;
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    public Double getUserFreeze() {
        return userFreeze;
    }

    public void setUserFreeze(Double userFreeze) {
        this.userFreeze = userFreeze;
    }
}
