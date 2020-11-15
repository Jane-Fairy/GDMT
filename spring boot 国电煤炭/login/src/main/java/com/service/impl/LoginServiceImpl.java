package com.service.impl;

import com.dao.LoginDao;
import com.entity.Admin;
import com.entity.AdminRole;
import com.entity.UserRole;
import com.entity.Users;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public Users userLogin(String name) {
        return loginDao.userLogin(name);
    }

    @Override
    public Admin adminLogin(String name) {
        return loginDao.adminLogin(name);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
        Users users=loginDao.userLogin(s);
        if (users!=null){
            List<SimpleGrantedAuthority> list=new ArrayList<>();
            for (UserRole userRole:users.getRoles()){
                list.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRoleName()));
            }
            User user=new User(users.getUserName(),users.getUserPassword(),list);
            return user;
        }
        Admin admin=loginDao.adminLogin(s);
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (AdminRole adminRole:admin.getAdminRoles()){
            list.add(new SimpleGrantedAuthority("ROLE_"+adminRole.getAdminRoleName()));
        }
        User user=new User(admin.getAdminName(),admin.getAdminPassword(),list);
        return user;
    }

}

