package com.controller;

import com.entity.*;
import com.github.pagehelper.PageInfo;
import com.service.AdminReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Author Harlan
 * @Date 2020/11/4
 */
@RestController
public class AdminReviewController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdminReviewService adminReviewService;

    private final int PAGE_SIZE = 15;

    /**
     * 查询未审核用户信息
     * @param pageNum 当前页码
     * @return 信息列表
     */
    @GetMapping("/admin/info/notReview/{pageNum}")
    public ResultBean<PageInfo<Info>> showInfoNotReview(@PathVariable("pageNum") Integer pageNum){
        PageInfo<Info> pageInfo = new PageInfo<>(adminReviewService.showInfoNotReview(pageNum, PAGE_SIZE));
        return new ResultBean<>(200, "查询成功", pageInfo);
    }


    /**
     * 查询未审核用户列表
     * @param pageNum 当前页码
     * @return 用户列表
     */
    @GetMapping("/admin/user/notReview/{pageNum}")
    public ResultBean<PageInfo<Users>> showUserNotReview(@PathVariable("pageNum") Integer pageNum){
        PageInfo<Users> pageInfo = new PageInfo<>(adminReviewService.showUserNotReview(pageNum, PAGE_SIZE));
        return new ResultBean<>(200, "查询成功", pageInfo);
    }

    /**
     * 用户信息审核
     * @param reviewType 审核码 0 驳回:1 通过
     * @param userId 用户id
     * @param adminId 管理员id
     * @param message 审核信息
     * @return 审核结果
     */
    @PostMapping("/admin/info/review")
    public ResultBean<Boolean> reviewUserInfo(Integer reviewType, Integer userId, Integer adminId, Message message){
        int maxReviewType = 1;
        if (reviewType == null || reviewType > maxReviewType || reviewType < 0){
            return new ResultBean<>(400, "资料状态码错误", false);
        }

        if (userId == null || adminId == null){
            return new ResultBean<>(400, "参数错误", false);
        }
        //判空(审核失败需要发送消息)
        if (reviewType == 0) {
            if (StringUtils.isEmpty(message.getMessageContent())) {
                return new ResultBean<>(400, "请填写驳回详细信息", false);
            }
        }
        //用户存在判断
        if (!adminReviewService.existUserById(userId)){
            return new ResultBean<>(400, "用户不存在", false);
        }
        //用户信息存在判断
        if (!adminReviewService.existInfoById(userId)){
            return new ResultBean<>(400,"该用户未提交审核资料", false);
        }
        //判断发送人是否存在
        if (!adminReviewService.existAdminById(adminId)){
            return new ResultBean<>(400, "管理员账号不存在", false);
        }
        boolean res = adminReviewService.reviewUserInfo(reviewType, userId, adminId, message);
        if (reviewType == 0){
            return new ResultBean<>(200, res ? "驳回成功" : "驳回失败", res);
        }else {
            return new ResultBean<>(200, res ? "审核通过" : "通过失败", res);
        }
    }

    /**
     * 查询未审核商品
     * @param pageNum 当前页码
     * @return 商品列表
     */
    @GetMapping("/admin/goods/notReview/{pageNum}")
    public ResultBean<PageInfo<Goods>> showGoodsNotReview(@PathVariable("pageNum") Integer pageNum){
        List<Goods> goodsList = adminReviewService.showGoodsNotReview(pageNum, PAGE_SIZE);
        if (goodsList.size() > 0 ){
            PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
            return new ResultBean<>(200, "查询成功", pageInfo);
        }else {
             return new ResultBean<>(201,"未查询到商品信息", null);
        }
    }

    /**
     * 查询未审核商品列表
     * @param pageNum 当前页码
     * @return 商品列表
     */
    @GetMapping("/admin/demandGoods/notReview/{pageNum}")
    public ResultBean<PageInfo<Goods>> showDemandGoodsNotReview(@PathVariable("pageNum") Integer pageNum){
        //需求类型
        int infoType = 1;
        List<Goods> goodsList = adminReviewService.showGoodsNotReviewByInfoType(infoType, pageNum, PAGE_SIZE);
        if (goodsList.size() > 0){
            PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
            return new ResultBean<>(200, "查询成功", pageInfo);
        }else {
            return new ResultBean<>(201, "未查询到商品信息", null);
        }
    }

    /**
     * 查询供给商品未审核列表
     * @param pageNum 当前页码
     * @return 商品列表
     */
    @GetMapping("/admin/provideGoods/notReview/{pageNum}")
    public ResultBean<List<Goods>> showProvideGoodsNotReview(@PathVariable("pageNum") Integer pageNum){
        //出售类型
        int infoType = 0;
        List<Goods> goodsList = adminReviewService.showGoodsNotReviewByInfoType(infoType, pageNum, PAGE_SIZE);
        if (goodsList.size() > 0){
            return new ResultBean<>(200, "查询成功", goodsList);
        }else {
            return new ResultBean<>(201, "未查询到商品信息", null);
        }
    }

    /**
     * 商品信息审核
     * @param goodsState 商品审核状态码 1驳回:2通过
     * @param goodsId 商品id
     * @param adminId 管理员id
     * @param message 审核信息
     * @return 审核结果
     */
    @PostMapping("/admin/goods/review")
    public ResultBean<Boolean> reviewGoods(Integer goodsState, Integer goodsId, Integer adminId, Message message){
        //通过
        int maxGoodsState = 2;
        //驳回
        int minGoodsState = 1;
        if (goodsState == null || goodsState > maxGoodsState || goodsState < minGoodsState){
            return new ResultBean<>(400, "商品状态错误", false);
        }
        //判空
        if (goodsId == null || adminId == null){
            return new ResultBean<>(400, "参数错误", false);
        }
        if (goodsState == 1){
            if (StringUtils.isEmpty(message.getMessageContent())){
                return new ResultBean<>(400, "请填写驳回详细信息", false);
            }
        }
        if (!adminReviewService.existGoodsById(goodsId)){
            return new ResultBean<>(400, "商品信息不存在", false);
        }
        if (!adminReviewService.existAdminById(adminId)){
            return new ResultBean<>(400, "管理员账号信息不存在", false);
        }
        boolean res = adminReviewService.reviewGoods(goodsState, goodsId, adminId, message);
        if (goodsState == 1){
            return new ResultBean<>(200, res ? "驳回成功" : "驳回失败", res);
        }else {
            return new ResultBean<>(200, res ? "通过成功" : "通过失败", res);
        }
    }
}
