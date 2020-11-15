package com.service;

import com.dao.AnnInfoDao;
import com.entity.AnnInfo;

import java.util.List;

public interface AnnInfoService {
    List<AnnInfo> getAllAnnInfo();
    List<AnnInfo> getAllAnno();
    List<AnnInfo> getAllInfo();
    int deleteInfoById(int annId);
    int updateInfoById(AnnInfo annInfo);
    int insertInfo(AnnInfo annInfo);
    AnnInfo SeleteAnnOrInfoById(int annId);
}
