package com.controller;

import com.entity.Info;
import com.entity.ResultBean;
import com.entity.Users;
import com.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/info/upload")
public class InfoController {

    @Autowired
    private InfoService infoService;

    //查询用户认证资料状态
    @RequestMapping("/findInfoState")
    public ResultBean findInfoState(@RequestParam("userId") Integer userId) {
        return infoService.findInfoState(userId);
    }

    //查询用户认证资料
    @RequestMapping("/findInfo")
    public ResultBean findInfo(@RequestParam("userId") Integer userId) {
        return infoService.findInfoByUserId01(userId);
    }

    //查询全部用户资料
    @RequestMapping("/admin/findInfoAll")
    public ResultBean findInfoAll() {
        return infoService.findAll();
    }

    //添加用户资料
    @RequestMapping(value = "/addInfo")
    public ResultBean addInfo(@RequestBody Info info) {
        return infoService.addInfo(info);
    }

    //删除用户资料
    @RequestMapping("/admin/delInfoByInfoId")
    public ResultBean delInfoByInfoId(@RequestParam("infoId") Integer infoId){
        return infoService.delInfoByInfoId(infoId);
    }

    //修改用户资料
    @RequestMapping(value = "/updateInfo")
    public ResultBean updateInfo(@RequestBody Info info){
        return infoService.updateInfo(info);
    }

    //修改资料审核状态
    @RequestMapping(value = "/admin/updateInfoType")
    public ResultBean updateInfoState(@RequestBody Users users) {
        return infoService.updateInfoState(users);
    }

    //查询用户账户
    @RequestMapping(value = "/findUserAccount")
    public ResultBean findUserAccount(@RequestParam("userId") Integer userId) {
        return infoService.findUserAccount(userId);
    }

    //查询用户接受消息
    @RequestMapping("/findUserMassage")
    public ResultBean findUserMassage(@RequestParam("username") String username) {
        return infoService.findUserMassage(username);
    }

    //上传附件
    @RequestMapping("/upload")
    public ResultBean upload(@RequestParam("image") MultipartFile upload, @RequestParam("userId") Integer userId) {
        return infoService.upload(upload, userId);
    }
}