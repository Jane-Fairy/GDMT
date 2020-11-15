package com.dao;

import com.entity.AnnInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnnInfoDao {

    //获取所有公告和资讯
    @Select("select * from gdmt.ann_info")
    List<AnnInfo> getAllAnnInfo();

    //获取所有资讯信息
    @Select("select * from gdmt.ann_info where ann_type=0")
    List<AnnInfo> getAllAnno();

    //获取所有公告
    @Select("select * from gdmt.ann_info where ann_type=1")
    List<AnnInfo> getAllInfo();

    //根据id删除公告或资讯
    @Delete("delete from gdmt.ann_info where ann_id=#{annId}")
    int deleteInfoById(int annId);

    //根据id查询公告或资讯
    @Select("select * from gdmt.ann_info where ann_id=#{annId}")
    AnnInfo SeleteAnnOrInfoById(int annId);

    //根据id更新公告或资讯信息
    @Update("update gdmt.ann_info set ann_title=#{annTitle},ann_body=#{annBy},ann_time=#{annTime},ann_body=#{annBody},ann_type=#{annType} where ann_id=#{annId}")
    int updateInfoById(AnnInfo annInfo);

    //新增公告或资讯
    @Insert("insert into gdmt.ann_info (ann_title,ann_by,ann_time,ann_body,ann_type) values(#{annTitle},#{annBy},#{annTime},#{annBody},#{annType})")
    int insertInfo(AnnInfo annInfo);

    //分页查询所有公告
    @Insert("select * from gdmt.ann_info limit ")
    List<AnnInfo> getLimitAnno(int currentPage,int lineSize);


}
