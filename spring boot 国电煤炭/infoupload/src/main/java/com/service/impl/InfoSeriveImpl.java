package com.service.impl;

import com.dao.InfoDao;
import com.entity.*;
import com.service.InfoService;
import com.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@Transactional
public class InfoSeriveImpl implements InfoService {

    @Autowired
    private InfoDao infoDao;

    //查询用户认证资料状态（内部调用）
    public int infoState(int userId) {
        int state = -2;
        try {
            Users users = infoDao.findInfoState(userId);
            if(users == null) {
                state = -1;
            } else {
                state = users.getUserState();
            }
        } catch (Exception e) {
            state = -2;
        }
        return state;
    }

    //查询用户认证资料状态
    @Override
    public ResultBean findInfoState(int userId) {
        ResultBean resultBean = null;
        try {
            int state = infoState(userId);
            if(state == -1) {
                resultBean = new ResultBean(201, "不存在该用户", state);
            } else {
                if(state == 0) {
                    resultBean = new ResultBean(200, "待审核", state);
                } else if (state == 1) {
                    resultBean = new ResultBean(200, "认证成功", state);
                } else if (state == 2) {
                    resultBean = new ResultBean(200, "禁止", state);
                } else if (state == 3) {
                    resultBean = new ResultBean(200, "未提交资料", state);
                } else if (state == 4) {
                    resultBean = new ResultBean(200, "认证失败", state);
                }
            }
        } catch (Exception e) {
            resultBean = new ResultBean(400, "未知错误！", -1);
        }
        return resultBean;
    }

    //查询用户认证资料
    @Override
    public ResultBean<Info> findInfoByUserId01(int userId) {
        ResultBean<Info> resultBean = null;
        try {
            Info info = infoDao.findInfoByUserId(userId);
            if(info == null) {
                resultBean = new ResultBean<>(201, "该用户无资料！");
            } else {
                resultBean = new ResultBean<>(200, "查询成功", info);
            }
        } catch (Exception e) {
            resultBean = new ResultBean<>(400, "未知错误！");
        }
        return resultBean;
    }

    //查询所有用户认证资料
    @Override
    public ResultBean<List<Info>> findAll() {
        ResultBean<List<Info>> resultBean;
        try {
            List<Info> infos = infoDao.findAll();
            if(infos != null && infos.size() > 0) {
                resultBean = new ResultBean<>(200, "查询所有用户资料成功！", infos);
            } else {
                resultBean = new ResultBean<>(201, "无用户资料！");
            }
        } catch (Exception e) {
            resultBean = new ResultBean<>(400, "查询所有用户资料失败！未知错误！");
        }
        return resultBean;
    }

    //添加用户认证资料
    @Override
    public ResultBean addInfo(Info info) {
        ResultBean resultBean;
        try {
            int state = infoState(info.getUserId());
            if(state == 3) {
                int num = infoDao.addInfo(info);
                if(num > 0) {
                    Users users = new Users();
                    users.setUserId(info.getUserId());
                    users.setUserState(0);
                    infoDao.updateInfoState(users);
                    resultBean = new ResultBean<>(200, "添加认证资料成功！", true);
                } else {
                    resultBean = new ResultBean<>(201, "添加认证资料失败！", false);
                }
            } else {
                resultBean = new ResultBean<>(201, "存在认证资料，添加失败！", false);
            }
        } catch (Exception e) {
            resultBean = new ResultBean<>(400, "添加认证资料失败！未知错误", false);
        }
        return resultBean;
    }

    //删除用户认证资料
    @Override
    public ResultBean delInfoByInfoId(int infoId) {
        ResultBean resultBean;
        try{
            int num = infoDao.delInfoByInfoId(infoId);
            if(num > 0) {
                Users users = new Users();
                users.setUserId(infoId);
                users.setUserState(3);
                infoDao.updateInfoState(users);
                resultBean = new ResultBean(200, "删除用户资料成功！", true);
            } else {
                resultBean = new ResultBean(201, "删除用户资料失败！无此资料", false);
            }
        } catch (Exception e) {
            resultBean = new ResultBean(400, "删除用户资料失败！未知错误！", false);
        }
        return resultBean;
    }

    //更新用户认证资料
    @Override
    public ResultBean updateInfo(Info info) {
        ResultBean resultBean;
        try {
            int num = infoDao.updateInfo(info);
            if(num > 0) {
                Users users = new Users();
                users.setUserId(info.getUserId());
                users.setUserState(0);
                infoDao.updateInfoState(users);
                resultBean = new ResultBean<>(200, "修改认证资料成功！", true);
            } else {
                resultBean = new ResultBean<>(201, "修改认证资料失败！无此资料！", false);
            }
        } catch (Exception e) {
            resultBean = new ResultBean<>(400, "修改认证资料失败！未知错误！", false);
        }
        return resultBean;
    }

    //更新用户认证资料审核状态
    @Override
    public ResultBean updateInfoState(Users users) {
        ResultBean resultBean;
        try {
            int state = infoState(users.getUserId());
            if(state == 1 || state == 4) {
                int sum = infoDao.updateInfoState(users);
                if(sum > 0) {
                    resultBean = new ResultBean<>(200, "修改认证资料状态成功！", true);
                } else {
                    resultBean = new ResultBean<>(202, "修改认证资料状态失败！不存在资料！", false);
                }
            } else {
                resultBean = new ResultBean<>(202, "修改认证资料状态失败！当前状态不可修改", false);
            }
        } catch (Exception e) {
            resultBean = new ResultBean<>(400, "修改认证资料状态失败！未知错误！", false);
        }
        return resultBean;
    }

    //查询用户账户
    @Override
    public ResultBean findUserAccount(Integer userId) {
        ResultBean resultBean = null;
        try {
            Account account = infoDao.findAccount(userId);
            if(account != null) {
                resultBean = new ResultBean(200, "查询用户账户成功！", account);
            } else {
                resultBean = new ResultBean(201, "该用户无账户！", false);
            }
        } catch (Exception e) {
            resultBean = new ResultBean(400, "未知错误！", false);
        }
        return resultBean;
    }

    //查询用户接受消息
    @Override
    public ResultBean findUserMassage(String username) {
        ResultBean resultBean = null;
        try {
            List<Message> messagesList = infoDao.findMessage(username);
            Message message = new Message();
            message.setMessageIndex(-1);
            if(messagesList != null && messagesList.size() > 0) {
                for(Message m : messagesList) {
                    if(m.getMessageIndex() > message.getMessageIndex()) {
                        message = m;
                    }
                }
                resultBean = new ResultBean(200, "查询消息成功！", message);
            } else {
                resultBean = new ResultBean(201, "无消息！", false);
            }
        } catch (Exception e) {
            resultBean = new ResultBean(400, "未知错误！", false);
        }
        return resultBean;
    }

    //上传附件
    @Override
    public ResultBean upload(MultipartFile upload, Integer userId) {
        ResultBean resultBean = null;
        FileUtils fileUtils=new FileUtils();
        try {
            if(upload == null) {
                resultBean = new ResultBean(201, "文件不能为空！", false);
            } else {
                String path=fileUtils.upload(upload,userId);
                resultBean = new ResultBean(200, "文件上传成功！",path);
            }
        } catch (Exception e) {
            resultBean = new ResultBean(400, "文件上传失败！", false);
        }
        return resultBean;
    }
}
