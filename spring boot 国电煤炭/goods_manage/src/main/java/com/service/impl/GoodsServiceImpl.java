package com.service.impl;

import com.dao.GoodsDao;
import com.entity.Goods;
import com.github.pagehelper.PageHelper;
import com.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 寰
 * @create 2020-10-29-14:34
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    /**
     * 查询所有商品信息
     *
     * @return
     */
    @Override
    public List<Goods> findAll(int page,int pageSize) {
        //参数pageNum是页码值  参数pageSize代表的是每页显示条数
        PageHelper.startPage(page,pageSize);
        return goodsDao.findAll();
    }

    /**
     * 插入数据
     *
     * @param goods
     * @return
     */
    @Override
    public boolean insert(Goods goods) {
        return goodsDao.insert(goods);
    }

    @Override
    public Goods findGoodsById(Integer id) {
        return goodsDao.findGoodsById(id);
    }

    @Override
    public boolean deleteGoodsById(Integer id) {
        return goodsDao.deleteGoodsById(id);
    }

    @Override
    public List<Goods> findGoodsByGoodsState(Integer page,Integer pageSize,Integer state,Integer userid) {
        //参数pageNum是页码值  参数pageSize代表的是每页显示条数
        PageHelper.startPage(page,pageSize);
        return goodsDao.findGoodsByGoodsState(state,userid);
    }
}
