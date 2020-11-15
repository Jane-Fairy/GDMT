package com.dao;

import com.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 寰
 * @create 2020-10-29-14:30
 * 商品dao
 */
@Repository
@Mapper
public interface GoodsDao {

    /**
     * 查询所有商品
     */
    @Select("select * from goods where goods_state=2")
    List<Goods> findAll();

    /**
     * 插入一条商品信息
     * @param goods
     * @return
     */
    @Insert("insert into goods(goods_id,user_id,goods_type,info_type,t_type,goods_number,goods_price,goods_state,goods_date,goods_place,goods_deli,goods_deli_type,goods_linkman,goods_tel,goods_qnet,goods_mt,goods_star,goods_vad,goods_aar,goods_gran,goods_st,goods_hac,goods_remark,user_freeze)values(#{goodsId},#{userId},#{goodsType},#{infoType},#{tType},#{goodsNumber},#{goodsPrice},#{goodsState},#{goodsDate},#{goodsPlace},#{goodsDeli},#{goodsDeliType},#{goodsLinkman},#{goodsTel},#{goodsQnet},#{goodsMt},#{goodsStar},#{goodsVad},#{goodsAar},#{goodsGran},#{goodsSt},#{goodsHac},#{goodsRemark},#{userFreeze})")
    boolean insert(Goods goods);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from goods where goods_id=#{goodsId}")
    Goods findGoodsById(Integer id);

    /**
     * 根据id删除商品信息
     * @param id
     * @return
     */
    @Delete("delete from goods where goods_id=#{goodsId}")
    boolean deleteGoodsById(Integer id);

    /**
     * 用户登录过后
     * 根据状态码来显示商品的状态
     * 0：未审核，1：驳回，2：审核通过，3：下架
     * @param state
     * @return
     */
    @Select("select * from goods where goods_state=#{state} and user_id=#{userid}")
    List<Goods> findGoodsByGoodsState(Integer state,Integer userid);



}
