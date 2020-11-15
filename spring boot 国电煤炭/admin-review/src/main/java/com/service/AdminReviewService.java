package com.service;

import com.entity.Goods;
import com.entity.Info;
import com.entity.Message;
import com.entity.Users;
import java.util.List;

/**
 * @Author Harlan
 * @Date 2020/11/5
 */
public interface AdminReviewService {

    /**
     * 显示未审核的资料
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @return 用户资料
     */
    List<Info> showInfoNotReview(Integer pageNum, Integer pageSize);

    /**
     * 显示未审核资料的用户
     * @return 用户账号信息
     * @param pageSize 每页显示条数
     * @param pageNum 当前页码
     */
    List<Users> showUserNotReview(Integer pageNum, Integer pageSize);

    /**
     * 驳回用户资料信息
     * @param userId 用户id
     * @param message 驳回信息
     * @param adminId 管理员id
     * @param reviewType 是否驳回
     * @return 是否成功
     */
    Boolean reviewUserInfo(Integer reviewType, Integer userId, Integer adminId, Message message);

    /**
     * 判断用户是否存在
     * @param userId 用户id
     * @return 是否存在
     */
    Boolean existUserById(Integer userId);

    /**
     * 判断用户资料是否存在
     * @param userId 用户id
     * @return 是否存在
     */
    Boolean existInfoById(Integer userId);

    /**
     * 通过id查询管理员是否存在
     * @param adminId 管理员id
     * @return 是否存在
     */
    Boolean existAdminById(Integer adminId);

    /**
     * 查询未审核的商品
     * @param pageNum 当前页码
     * @param pageSize 每页数据条数
     * @return 商品信息
     */
    List<Goods> showGoodsNotReview(Integer pageNum, Integer pageSize);

    /**
     * 通过商品信息种类查询未审核商品
     * @param goodsInfoType 商品种类
     * @param pageNum 当前页码
     * @param pageSize 每页显示数据条数
     * @return 商品信息
     */
    List<Goods> showGoodsNotReviewByInfoType(Integer goodsInfoType, Integer pageNum, Integer pageSize);

    /**
     * 通过id判断商品是否存在
     * @param goodsId 商品id
     * @return 是否存在
     */
    Boolean existGoodsById(Integer goodsId);


    /**
     * 审核商品信息
     * @param goodsState 商品状态
     * @param goodsId 商品id
     * @param adminId 管理员id
     * @param message 消息
     * @return 是否成功
     */
    Boolean reviewGoods(Integer goodsState, Integer goodsId, Integer adminId, Message message);
}
