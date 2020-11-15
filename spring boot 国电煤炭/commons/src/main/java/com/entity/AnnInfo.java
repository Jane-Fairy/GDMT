package com.entity;

import java.util.Date;

/**
 * 公告信息表：ann_info
 */
public class AnnInfo {
    //主键  信息id  备注：自增
    private Integer annId;
    //信息标题
    private String annTitle;
    //外键 管理员Id
    private Integer annBy;
    //发布时间
    private Date annTime;
    //推送内容
    private String annBody;
    //类型  备注：0.咨询 1.公告
    private Integer annType;

    public Integer getAnnId() {
        return annId;
    }

    public void setAnnId(Integer annId) {
        this.annId = annId;
    }

    public String getAnnTitle() {
        return annTitle;
    }

    public void setAnnTitle(String annTitle) {
        this.annTitle = annTitle;
    }

    public Integer getAnnBy() {
        return annBy;
    }

    public void setAnnBy(Integer annBy) {
        this.annBy = annBy;
    }

    public Date getAnnTime() {
        return annTime;
    }

    public void setAnnTime(Date annTime) {
        this.annTime = annTime;
    }

    public String getAnnBody() {
        return annBody;
    }

    public void setAnnBody(String annBody) {
        this.annBody = annBody;
    }

    public Integer getAnnType() {
        return annType;
    }

    public void setAnnType(Integer annType) {
        this.annType = annType;
    }
}
