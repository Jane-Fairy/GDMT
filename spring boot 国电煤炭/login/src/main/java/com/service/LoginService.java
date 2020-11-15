package com.service;

import com.entity.Admin;
import com.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface LoginService extends UserDetailsService {

     Users userLogin(String name);

     Admin adminLogin(String name);
}
