package com.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentDao {

    //冻结
    @Update("update account set money=money-#{money},freeze=freeze+#{money} where user_id=#{userId}")
    int toFreeze(@Param("userId") Integer userId, @Param("money") Double money);

    //取消冻结
    @Update("update account set money=money+#{money},freeze=freeze-#{money} where user_id=#{userId}")
    int toAccount(@Param("userId") Integer userId, @Param("money") Double money);

    //扣款
    @Update("update account set freeze=freeze-#{money} where user_id=#{userId}")
    int def(@Param("userId") Integer userId, @Param("money") Double money);

    //到账
    @Update("update account set money=money+#{money} where user_id=#{userId}")
    int add(@Param("userId") Integer userId, @Param("money") Double money);

    //创建账户
    @Update("update account set money=5000000")
    int create(Integer userId);

    //查询余额
    @Select("select money from account where user_id=#{userId}")
    Double find(Integer userId);

    //查询冻结
    @Select("select freeze from account where user_id=#{userId}")
    Double findFreeze(Integer userId);
}
