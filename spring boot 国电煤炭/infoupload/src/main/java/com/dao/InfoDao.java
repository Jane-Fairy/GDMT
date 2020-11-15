package com.dao;

import com.entity.Account;
import com.entity.Info;
import com.entity.Message;
import com.entity.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InfoDao {

    //查询用户资料认证状态
    @Select("SELECT user_state FROM user WHERE user_id=#{userId}")
    Users findInfoState(int userId);

    //查询所有用户认证资料
    @Select("SELECT * FROM user_info")
    List<Info> findAll();

    //根据用户id查询认证资料
    @Select("SELECT * FROM user_info WHERE user_id=#{userId}")
    Info findInfoByUserId(int userId);

    //添加用户认证资料
    @Insert("INSERT INTO " +
            "user_info(user_id,company_name,company_type,company_legal,company_linkman,company_tel,company_bank," +
            "company_bank_number,company_bln,company_trn,company_desc,company_uscc,company_paper)" +
            "values(#{userId},#{companyName},#{companyType},#{companyLegal},#{companyLinkman},#{companyTel}," +
            "#{companyBank},#{companyBankNumber},#{companyBln},#{companyTrn},#{companyDesc},#{companyUscc},#{companyPaper})")
    int addInfo(Info info);

    //删除用户认证资料
    @Delete("DELETE FROM user_info WHERE info_id=#{infoId}")
    int delInfoByInfoId(int infoId);

    //更新用户认证资料
    @Update("UPDATE user_info SET " +
            "company_name=#{companyName},company_type=#{companyType},company_legal=#{companyLegal}," +
            "company_linkman=#{companyLinkman},company_tel=#{companyTel},company_bank=#{companyBank}," +
            "company_bank_number=#{companyBankNumber},company_bln=#{companyBln},company_trn=#{companyTrn}," +
            "company_desc=#{companyDesc},company_uscc=#{companyUscc},company_paper=#{companyPaper} WHERE info_id=#{infoId}")
    int updateInfo(Info info);

    //更新用户认证资料状态
    @Update("UPDATE user SET " +
            "user_state=#{userState} WHERE user_id=#{userId}")
    int updateInfoState(Users users);

    //查询用户账户
    @Select("SELECT * FROM account WHERE user_id=#{userId}")
    Account findAccount(Integer userId);

    //查询用户消息
    @Select("SELECT * FROM message WHERE message_to=#{username}")
    List<Message> findMessage(String username);
}
