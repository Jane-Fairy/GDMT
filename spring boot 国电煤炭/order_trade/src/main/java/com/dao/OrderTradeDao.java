package com.dao;

import com.entity.Contract;
import com.entity.Goods;
import com.entity.Order;
import com.entity.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderTradeDao {

    //查找所有订单
    @Select("select * from `order`")
    @Results({
            @Result(id = true,property = "orderId",column = "order_id"),
            @Result(property = "sell",column = "order_id",javaType = com.entity.Users.class,one = @One(select = "com.dao.OrderTradeDao.findOderSell")),
            @Result(property = "buy",column = "order_id",javaType = com.entity.Users.class,one = @One(select = "com.dao.OrderTradeDao.findOderBuy"))
    })
    List<Order> findAllOrder();

    //根据id查找订单
    @Select("select * from `order` where order_id=#{orderId}")
    @Results({
            @Result(id = true,property = "orderId",column = "order_id"),
            @Result(property = "sell",column = "order_id",javaType = com.entity.Users.class,one = @One(select = "com.dao.OrderTradeDao.findOderSell")),
            @Result(property = "buy",column = "order_id",javaType = com.entity.Users.class,one = @One(select = "com.dao.OrderTradeDao.findOderBuy"))
    })
    Order findOrderById(Integer orderId);

    //根据用户查找订单
    @Select("select * from `order` where order_id in (select order_id from user_order where sell_id=#{userId} or buy_id=#{userId})")
    @Results({
            @Result(id = true,property = "orderId",column = "order_id"),
            @Result(property = "sell",column = "order_id",javaType = com.entity.Users.class,one = @One(select = "com.dao.OrderTradeDao.findOderSell")),
            @Result(property = "buy",column = "order_id",javaType = com.entity.Users.class,one = @One(select = "com.dao.OrderTradeDao.findOderBuy")),
    })
    List<Order> findOrderByUser(Integer userId);

    //查找订单卖家
    @Select("select * from user where user_id=(select sell_id from user_order where order_id=#{orderId})")
    Users findOderSell(Integer orderId);

    //查找订单买家
    @Select("select * from user where user_id=(select buy_id from user_order where order_id=#{orderId})")
    Users findOderBuy(Integer orderId);

    //改变订单买方状态
    @Update("update `order` set buy_state=#{buySate} where order_id=#{orderId}")
    int updateBuyState(@Param("orderId") Integer orderId,@Param("buySate") Integer buyState);

    //改变订单卖方状态
    @Update("update `order` set sell_state=#{sellSate} where order_id=#{orderId}")
    int updateSellState(@Param("orderId") Integer orderId,@Param("sellSate") Integer sellState);

    //改变订单状态
    @Update("update `order` set order_state=#{orderSate} where order_id=#{orderId}")
    int updateOrderState(@Param("orderId") Integer orderId,@Param("orderSate") Integer orderState);

    //摘牌生成订单
    @Insert("insert into `order`(order_number,contract_id,order_time,sell_freeze,buy_freeze,money) values(#{orderNumber},#{contractId},#{orderTime},#{sellFreeze},#{buyFreeze},#{money})")
    @Options(useGeneratedKeys = true,keyProperty = "orderId",keyColumn = "order_id")
    int createOrder(Order order);

    //用户订单关系添加
    @Insert("insert into user_order(sell_id,buy_id,order_id) values(#{sellId},#{buyId},#{orderId})")
    int addUserOrder(@Param("sellId") Integer sellId,@Param("buyId") Integer buyId,@Param("orderId") Integer orderId);

    //删除订单用户关系
    @Delete("delete from user_order where order_id=#{orderId}")
    int delUserOrder(Integer orderId);

    //删除订单
    @Delete("delete from order where order_id=#{orderId}")
    int delOrder(Integer orderId);

    //订单签收
    @Update("update `order` set order_state=4 where order_id=#{orderId}")
    int orderComplete(Integer orderId);

    //买方付款
    @Update("update `order` set buy_state=1 where order_id=#{orderId}")
    int buyPay(Integer orderId);

    //买方冻结资金更新
    @Update("update `order` set buy_freeze=buy_freeze+#{money} where order_id=#{orderId}")
    int buyFreezeUpdate(@Param("orderId") Integer orderId,@Param("money") Double money);

    //卖家发货
    @Update("update `order` set sell_state=1 where order_id=#{orderId}")
    int sellSend(Integer orderId);

    //通过合同id查商品详情
    @Select("select * from goods where goods_id=(select goods_id from contract where contract_id=#{contractId})")
    Goods findGoods(Integer contractId);

}
