package com.dao;

import com.entity.Torder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 寰
 * @create 2020-11-04-16:16
 */
@Repository
@Mapper
public interface LogisticsDao {

    /**
     * 根据商品id获取商品的数量
     * @param goodsId
     * @return
     */
    @Select("select goods_number from goods where goods_id=#{goodsIde}")
    int findGoodsQuantity(Integer goodsId);

    /**
     * 根据用户id查询所有有的物流订单
     * @param torderState
     * @return
     */
    @Select("select * from torder where torder_state=#{torderState} and user_id=#{userId}")
    List<Torder> findAllByState(Integer torderState,Integer userId);

    /**
     * 更新订单物流id
     * @param orderId
     * @param torderId
     */
    @Update("update order set torder_id=#{torderId} where order_id=#{orderId}")
    void updateOrderTorderId(@Param("orderId") Integer orderId,@Param("torderId") Integer torderId);

    @Insert("insert into torder(user_id,sell_id,phone)")
    @Options(useGeneratedKeys = true,keyProperty = "torderId",keyColumn = "torder_id")
    void createTorder(Torder torder);

}
