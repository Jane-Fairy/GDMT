package com.dao;

import com.entity.Goods;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Author Harlan
 * @Date 2020/10/29
 */
@Repository
public interface GoodsDao {

    /**
     * 查询所有商品
     * @return 商品List
     */
    @Select("select * from goods")
    List<Goods> findAllGoods();

    /**
     * 通过商品状态查询商品
     * @param state 商品状态
     * @return 商品List
     */
    @Select("select * from goods where goods_state = #{state}")
    List<Goods> findGoodsByState(int state);

    /**
     * 通过商品id查询商品
     * @param goodsId 商品id
     * @return 商品信息
     */
    @Select("select * from goods where goods_id = #{goodsId}")
    Goods findGoodsById(int goodsId);

    /**
     * 通过商品类型查询
     * @param goodsType 商品类型
     * @return 商品信息
     */
    @Select("select * from goods where goods_type = #{goodsType}")
    List<Goods> findGoodsByType(String goodsType);


    /**
     * 通过id修改商品状态
     * @param goodsId 商品id
     * @param goodsState 商品状态
     */
    @Update("update goods set goods_state = #{goodsState} where goods_id = #{goodsId}")
    void changeGoodsStateById(@PathParam("goodsId") Integer goodsId, @PathParam("goodsState") Integer goodsState);
}
