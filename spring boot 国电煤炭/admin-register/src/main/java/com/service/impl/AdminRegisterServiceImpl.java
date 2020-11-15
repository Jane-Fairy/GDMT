package com.service.impl;

import com.dao.AdminRegisterDao;
import com.entity.Admin;
import com.service.AdminRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author Harlan
 * @Date 2020/11/4
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminRegisterServiceImpl implements AdminRegisterService {

    @Autowired
    private AdminRegisterDao adminRegisterDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Boolean saveAdmin(Admin admin) {
        admin.setAdminState(1);
        admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
        admin.setCreateTime(new Date());
        admin.setModifyTime(new Date());
        int primaryKey = adminRegisterDao.insertAdmin(admin);
        return primaryKey == 1;
    }

    @Override
    public Boolean findAdminByName(String adminName) {
        Admin admin = adminRegisterDao.findAdminByName(adminName);
        return admin == null;
    }
}
