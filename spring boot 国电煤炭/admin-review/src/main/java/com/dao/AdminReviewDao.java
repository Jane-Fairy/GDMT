package com.dao;

import com.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Author Harlan
 * @Date 2020/11/4
 */
@Repository
public interface AdminReviewDao {

    /**
     * 通过用户状态查询用户信息
     * @param userState 用户状态
     * @return 用户信息
     */
    @Select("select * from user_info where user_id in (select user_id from user where user_state = #{userState})")
    List<Info> selectInfoByUserState(@Param("userState") int userState);


    /**
     * 通过用户状态查询用户
     * @param userState 用户状态
     * @return 用户
     */
    @Select("select * from user where user_state = #{userState}")
    @Results({
            @Result(id = true, property = "userId", column = "user_id"),
            @Result(property = "info", column = "user_id", javaType = Info.class, one = @One(select = "com.dao.AdminReviewDao.selectInfoByUserId"))
    })
    List<Users> selectUserByState(int userState);


    /**
     * 通过用户id更新用户状态
     * @param userId 用户id
     * @param userState 用户状态
     * @return 受影行数
     */
    @Update("update user set user_state = #{userState} where user_id = #{userId}")
    Integer updateUserStateById(@PathParam("userId") int userId, @PathParam("userState") int userState);


    /**
     * 通过用户id查询用户信息
     * @param userId 用户id
     * @param userState 用户状态
     * @return 用户信息
     */
    @Select("select * from user_info where user_id in (select user_id from user where user_state = #{userState} and user_id = #{userId})")
    Info selectInfoByUserIdAndState(@PathParam("userId") int userId, @PathParam("userState") int userState);


    /**
     * 通过用户id查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    @Select("select * from user_info where user_id = #{userId}")
    Info selectInfoByUserId(int userId);


    /**
     * 通过商品状态查询商品
     * @param goodsState 商品状态
     * @return 商品信息
     */
    @Select("select * from goods where goods_state = #{goodsState}")
    List<Goods> selectGoodsByState(int goodsState);


    /**
     * 通过商品id查询商品
     * @param goodsId 商品id
     * @return 商品信息
     */
    @Select("select * from goods where goods_id = #{goodsId}")
    Goods selectGoodsById(int goodsId);


    /**
     * 通过商品id修改商品状态
     * @param goodsId 商品id
     * @param goodsState 商品状态
     * @return 受影响行数
     */
    @Update("update goods set goods_state = #{goodsState} where goods_id = #{goodsId}")
    Integer updateGoodsStateById(int goodsId, int goodsState);


    /**
     * 插入消息
     * @param message 消息内容
     * @return 受影行数
     */
    @Insert("insert into message(message_from, message_to, message_title, message_content, message_date, message_type, message_index, message_state) values (#{messageFrom}, #{messageTo}, #{messageTitle}, #{messageContent}, #{messageDate}, #{messageType}, #{messageIndex}, #{messageState})")
    Integer insertMessage(Message message);

    /**
     * 通过id删除消息
     * @param messageId 消息id
     * @return 收影响行数
     */
    @Delete("delete from message where message_id = #{messageId}")
    Integer deleteMessageById(int messageId);


    /**
     * 通过消息id更改消息状态
     * @param messageId 消息id
     * @param messageState 消息状态
     * @return 受影响行数
     */
    @Update("update message set message_state = #{messageState} where message_id = #{messageId}")
    int updateMessageStateById(@PathParam("messageId") int messageId, @PathParam("messageState") int messageState);

    /**
     * 通过id查询用户
     * @param userId 用户id
     * @return 用户信息
     */
    @Select("select * from user where user_id = #{userId}")
    Users selectUserById(int userId);

    /**
     * 通过账户名查询用户
     * @param userName 账户名
     * @return 用户信息
     */
    @Select("select * from user where user_name = #{userName}")
    Users selectUserByUserName(String userName);

    /**
     * 通过id查询管理员
     * @param adminId 管理员id
     * @return 管理员信息
     */
    @Select("select * from admin where admin_id = #{adminId}")
    Admin selectAdminById(int adminId);


    /**
     * 通过商品状态和种类查询
     * @param goodsState 商品状态
     * @param goodsInfoType 商品信息种类
     * @return 商品信息
     */
    @Select("select * from goods where goods_state = #{goodsState} and info_type = #{goodsInfoType}")
    List<Goods> selectGoodsByStateAndInfoType(@PathParam("goodsState") int goodsState, @PathParam("goodsInfoType") int goodsInfoType);


    /**
     * 通过商品id查询用户名
     * @param goodsId 商品id
     * @return 用户名
     */
    @Select("select user_name from user where user_id in (select user_id from goods where goods_id = #{good_id})")
    String selectUsernameByGoodsId(Integer goodsId);

    /**
     * 插入账户信息
     * @param account 账户信息
     * @return 受影响行数
     */
    @Insert("insert into account(money, user_id, freeze) values (#{money}, #{userId}, #{freeze})")
    int insertAccount(Account account);

    /**
     * 通过userid查询账户信息
     * @param userId 用户id
     * @return 账户信息
     */
    @Select("select * from account where user_id = #{userId}")
    Account selectAccountByUserId(Integer userId);
}
