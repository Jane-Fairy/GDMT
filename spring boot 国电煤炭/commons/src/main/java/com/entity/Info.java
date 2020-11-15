package com.entity;

import java.io.Serializable;

/**
 * 用户认证资料
 */
public class Info implements Serializable {
    private Integer infoId;
    private Integer userId;
    private String companyName;
    //企业类型(0:供应商，1:物流)
    private Integer companyType;
    //法人代表
    private String companyLegal;
    //业务联系人
    private String companyLinkman;
    //联系方式
    private String companyTel;
    //开户行
    private String companyBank;
    //开户行卡号
    private String companyBankNumber;
    //营业执照编号
    private String companyBln;
    //税务登记编号
    private String companyTrn;
    //企业简介
    private String companyDesc;
    //同一社会信用代码
    private String companyUscc;
    //附件（文件地址）
    private String companyPaper;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyLegal() {
        return companyLegal;
    }

    public void setCompanyLegal(String companyLegal) {
        this.companyLegal = companyLegal;
    }

    public String getCompanyLinkman() {
        return companyLinkman;
    }

    public void setCompanyLinkman(String companyLinkman) {
        this.companyLinkman = companyLinkman;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyBank() {
        return companyBank;
    }

    public void setCompanyBank(String companyBank) {
        this.companyBank = companyBank;
    }

    public String getCompanyBankNumber() {
        return companyBankNumber;
    }

    public void setCompanyBankNumber(String companyBankNumber) {
        this.companyBankNumber = companyBankNumber;
    }

    public String getCompanyBln() {
        return companyBln;
    }

    public void setCompanyBln(String companyBln) {
        this.companyBln = companyBln;
    }

    public String getCompanyTrn() {
        return companyTrn;
    }

    public void setCompanyTrn(String companyTrn) {
        this.companyTrn = companyTrn;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyUscc() {
        return companyUscc;
    }

    public void setCompanyUscc(String companyUscc) {
        this.companyUscc = companyUscc;
    }

    public String getCompanyPaper() {
        return companyPaper;
    }

    public void setCompanyPaper(String companyPaper) {
        this.companyPaper = companyPaper;
    }
}
