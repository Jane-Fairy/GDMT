package com.service.serviceImpl;

import com.dao.AnnInfoDao;
import com.entity.AnnInfo;
import com.service.AnnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnnInfoServiceImpl implements AnnInfoService {

    @Autowired
    private AnnInfoDao annInfoDao;

    @Override
    public List<AnnInfo> getAllAnnInfo() {
        return annInfoDao.getAllAnnInfo();
    }

    @Override
    public List<AnnInfo> getAllAnno() {
        return annInfoDao.getAllAnno();
    }



    @Override
    public List<AnnInfo> getAllInfo() {
        return annInfoDao.getAllInfo();
    }

    @Override
    public int deleteInfoById(int annId) {
        return annInfoDao.deleteInfoById(annId);
    }

    @Override
    public int updateInfoById(AnnInfo annInfo) {
        return annInfoDao.updateInfoById(annInfo);
    }

    @Override
    public int insertInfo(AnnInfo annInfo) {
        return annInfoDao.insertInfo(annInfo);
    }

    @Override
    public AnnInfo SeleteAnnOrInfoById(int annId) {
        return annInfoDao.SeleteAnnOrInfoById(annId);
    }
}
