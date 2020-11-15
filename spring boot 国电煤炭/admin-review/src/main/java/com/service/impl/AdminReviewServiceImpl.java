package com.service.impl;

import com.dao.AdminReviewDao;
import com.entity.*;
import com.github.pagehelper.PageHelper;
import com.service.AdminReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author Harlan
 * @Date 2020/11/5
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminReviewServiceImpl implements AdminReviewService {

    @Autowired
    private AdminReviewDao adminReviewDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 用户未审核状态
     */
    private final int USER_NOT_REVIEW = 0;
    /**
     * 用户信息驳回状态
     */
    private final int USER_REJECT = 4;
    /**
     * 用户信息审核通过
     */
    private final int USER_PASS = 1;
    /**
     * 商品未审核状态
     */
    private final int GOODS_NOT_REVIEW = 0;
    private final int GOODS_REJECT = 1;
    private final int GOODS_PASS = 2;

    @Override
    public List<Info> showInfoNotReview(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminReviewDao.selectInfoByUserState(USER_NOT_REVIEW);
    }

    @Override
    public List<Users> showUserNotReview(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminReviewDao.selectUserByState(USER_NOT_REVIEW);
    }

    @Override
    public Boolean reviewUserInfo(Integer reviewType ,Integer userId, Integer adminId, Message message) {
        String adminName = adminReviewDao.selectAdminById(adminId).getAdminName();
        String userName = adminReviewDao.selectUserById(userId).getUserName();
        Integer infoId = adminReviewDao.selectInfoByUserId(userId).getInfoId();
        Date now = new Date();
        StringBuilder messageContent = new StringBuilder("尊敬的用户: ")
                .append(userName).append("您好. \n 您的账户信息于北京时间:")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(now))
                .append("审核通过. 感谢您的支持!");
        int resState;
        int resAccount = 0;
        if (reviewType == 1){
            message.setMessageTitle("账户信息审核通过");
            message.setMessageContent(messageContent.toString());
            resState = adminReviewDao.updateUserStateById(userId, USER_PASS);
            Account account = new Account();
            account.setUserId(userId);
            account.setFreeze(0.0);
            account.setMoney(0.0);
            resAccount = adminReviewDao.insertAccount(account);
            rabbitTemplate.convertAndSend("account","account.info", adminReviewDao.selectAccountByUserId(userId));
        }else {
            message.setMessageTitle("账户信息审核驳回");
            resState = adminReviewDao.updateUserStateById(userId, USER_REJECT);
        }
        message.setMessageIndex(infoId);
        message.setMessageDate(now);
        message.setMessageType(0);
        message.setMessageState(0);
        message.setMessageFrom(adminName);
        message.setMessageTo(userName);
        int resMsg = adminReviewDao.insertMessage(message);
        if (reviewType == 0){
            return resState + resMsg == 2;
        } else if (reviewType == 1){
            return resAccount + resState + resMsg == 3;
        }else {
            return false;
        }
    }

    @Override
    public Boolean existUserById(Integer userId) {
        return adminReviewDao.selectUserById(userId) != null;
    }

    @Override
    public Boolean existInfoById(Integer userId) {
        return adminReviewDao.selectInfoByUserIdAndState(userId, USER_NOT_REVIEW) != null;
    }

    @Override
    public Boolean existAdminById(Integer adminId) {
        return adminReviewDao.selectAdminById(adminId) != null;
    }

    @Override
    public List<Goods> showGoodsNotReview(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminReviewDao.selectGoodsByState(GOODS_NOT_REVIEW);
    }

    @Override
    public List<Goods> showGoodsNotReviewByInfoType(Integer goodsInfoType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminReviewDao.selectGoodsByStateAndInfoType(GOODS_NOT_REVIEW, goodsInfoType);
    }

    @Override
    public Boolean existGoodsById(Integer goodsId) {
        return adminReviewDao.selectGoodsById(goodsId) != null;
    }

    @Override
    public Boolean reviewGoods(Integer goodsState, Integer goodsId, Integer adminId, Message message) {
        String userName = adminReviewDao.selectUsernameByGoodsId(goodsId);
        String adminName = adminReviewDao.selectAdminById(adminId).getAdminName();
        Date now = new Date();
        int resState;
        if (goodsState == GOODS_REJECT){
            message.setMessageTitle("商品信息被驳回");
            resState  = adminReviewDao.updateGoodsStateById(goodsId, GOODS_REJECT);
        }else {
            message.setMessageTitle("商品信息审核通过");
            StringBuilder messageContent = new StringBuilder("尊敬的用户: ")
                    .append(userName).append(" 您好.\n 您的商品于北京时间:")
                    .append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(now))
                    .append("审核通过, 感谢您的支持!");
            message.setMessageContent(messageContent.toString());
            resState = adminReviewDao.updateGoodsStateById(goodsId, GOODS_PASS);
        }
        if (resState == 0){
            return false;
        }
        message.setMessageDate(now);
        message.setMessageFrom(adminName);
        message.setMessageTo(userName);
        message.setMessageState(0);
        message.setMessageIndex(goodsId);
        message.setMessageType(1);
        int resMsg = adminReviewDao.insertMessage(message);
        return resMsg + resState == 2;
    }
}