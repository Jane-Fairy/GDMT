package com.service;

import com.entity.Goods;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author Harlan
 * @Date 2020/10/29
 */
public interface GoodsService {

    /**
     * 查询所有商品
     * @param pageNum 当前页码
     * @param pageSize 每页条数
     * @return 商品List
     */
    List<Goods> findAllGoods(int pageNum, int pageSize);


    /**
     * 通过商品状态查询商品
     * @param state 商品状态
     * @param pageNum 当前页码
     * @param pageSize 每页条数
     * @return 商品List
     */
    List<Goods> findGoodsByState(int state, int pageNum, int pageSize);


    /**
     * 通过商品id查询商品
     * @param goodId 商品id
     * @return 商品信息
     */
    Goods findGoodsById(int goodId);


    /**
     * 通过关键字查询商品
     * @param keyWord 商品关键字
     * @return 商品List
     * @throws IOException 异常
     */
    List<Map<String, Object>> searchGoodsByKeyWord(String keyWord) throws IOException;


    /**
     * 通过商品类型查询
     * @param goodsType 商品类型
     * @param pageNum 当前页码
     * @param pageSize 每页数据条数
     * @return 商品信息
     */
    List<Goods> findGoodsByType(String goodsType, Integer pageNum, Integer pageSize);


    /**
     * 通过商品id更改商品状态
     * @param goodsId 商品id
     * @param goodsState 商品状态
     * @return 商品信息
     */
    Goods changeGoodsStateById(Integer goodsId, Integer goodsState);
}
