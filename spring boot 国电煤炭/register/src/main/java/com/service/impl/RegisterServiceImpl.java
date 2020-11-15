package com.service.impl;

import com.dao.RegisterDao;
import com.entity.Users;
import com.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 注册Service
 * @Author Harlan
 * @Date 2020/10/29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean registerUser(String username, String password) {
        Users userInfo = new Users();
        userInfo.setUserName(username);
        //密码加密
        String passwordEncode = passwordEncoder.encode(password);
        userInfo.setUserPassword(passwordEncode);
        //设置用户状态(3/未提交资料)
        userInfo.setUserState(3);
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        int primaryKey = registerDao.registerUsers(userInfo);
        return primaryKey == 1;
    }

    @Override
    public boolean userNameExist(String username) {
        Users userInfo = registerDao.findUserByUsername(username);
        return userInfo != null;
    }

    @Override
    public boolean existUserById(Integer userId) {
        return registerDao.selectUserById(userId) != null;
    }

    @Override
    public boolean modifyPasswordById(Integer userId, String password) {
        Users u = new Users();
        u.setUserId(userId);
        u.setUserPassword(passwordEncoder.encode(password));
        u.setModifyTime(new Date());
        return registerDao.updateUserById(u) == 1;
    }


}
