package com.service;

import com.entity.Info;
import com.entity.ResultBean;
import com.entity.Users;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InfoService {

    //查询用户资料认证状态（内部调用）
    int infoState(int userId);

    //查询用户资料认证状态
    ResultBean findInfoState(int userId);

    //查询用户待审或审核通过的认证资料
    ResultBean<Info> findInfoByUserId01(int userId);

    //查询所有用户认证资料
    ResultBean<List<Info>> findAll();

    //添加用户认证资料
    ResultBean addInfo(Info info);

    //删除用户认证资料
    ResultBean delInfoByInfoId(int infoId);

    //修改用户认证资料
    ResultBean updateInfo(Info info);

    //修改用户认证资料状态
    ResultBean updateInfoState(Users users);

    //查询用户账户
    ResultBean findUserAccount(Integer userId);

    //查询用户消息
    ResultBean findUserMassage(String username);

    ResultBean upload(MultipartFile upload, Integer userId);
}
