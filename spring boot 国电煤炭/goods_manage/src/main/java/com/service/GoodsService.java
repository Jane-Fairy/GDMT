package com.service;

import com.entity.Goods;

import java.util.List;

/**
 * @author 寰
 * @create 2020-10-29-14:29
 */
public interface GoodsService {
    /**
     * 查询所有商品信息
     * @return
     */
    List<Goods> findAll(int page,int pageSize);

    /**
     * 插入数据
     * @param goods
     * @return
     */
    boolean insert(Goods goods);

    Goods findGoodsById(Integer id);

    boolean deleteGoodsById(Integer id);

    List<Goods> findGoodsByGoodsState(Integer page,Integer pageSize,Integer state,Integer userid);
}
