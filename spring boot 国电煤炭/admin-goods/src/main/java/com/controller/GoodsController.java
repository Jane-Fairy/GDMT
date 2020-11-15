package com.controller;

import com.entity.Goods;
import com.entity.ResultBean;
import com.github.pagehelper.PageInfo;
import com.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 管理员商品Controller
 *
 * @Author Harlan
 * @Date 2020/10/29
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    private final int PAGE_SIZE = 15;

    /**
     * 查询所有
     * @return 信息
     */
    @GetMapping("/admin/goods/{pageNum}")
    public ResultBean<PageInfo<Goods>> findAllGoods(@PathVariable("pageNum") int pageNum) {
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsService.findAllGoods(pageNum, PAGE_SIZE));
        return new ResultBean<>(200, "查询成功", pageInfo);
    }


    /**
     * 通过商品状态查询
     * @param state 商品状态
     * @return 信息
     */
    @GetMapping("/admin/goods/state/{state}/{pageNum}")
    public ResultBean<PageInfo<Goods>> findGoodsByState(@PathVariable("state") Integer state, @PathVariable("pageNum") int pageNum) {
        //判空
        if (state == null) {
            return new ResultBean<>(400, "参数错误", null);
        }

        int maxState = 3;
        if (state < 0 || state > maxState) {
            return new ResultBean<>(400, "商品状态代码错误", null);
        } else {
            List<Goods> goodsList = goodsService.findGoodsByState(state, pageNum, PAGE_SIZE);
            PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
            return new ResultBean<>(200, "查询成功", pageInfo);
        }
    }


    /**
     * 通过商品id查询
     * @param goodsId 商品id
     * @return 信息
     */
    @GetMapping("/admin/goods/id/{goodsId}")
    public ResultBean<Goods> findGoodsById(@PathVariable("goodsId") Integer goodsId) {
        //判空
        if (goodsId == null) {
            return new ResultBean<>(400, "参数错误", null);
        }

        Goods goods = goodsService.findGoodsById(goodsId);
        if (goods == null) {
            return new ResultBean<>(200, "未查询到商品", null);
        } else {
            return new ResultBean<>(200, "商品信息查询成功", goods);
        }
    }

    /**
     * 通过商品种类查询商品
     * @param goodsType 商品种类
     * @param pageNum 当前页码
     * @return 商品信息
     */
    @GetMapping("/admin/goods/type/{goodsType}/{pageNum}")
    public ResultBean<PageInfo<Goods>> findGoodsByType(@PathVariable("goodsType") String goodsType, @PathVariable("pageNum") Integer pageNum) {
        //判空
        if (goodsType == null || goodsType.length() < 1) {
            return new ResultBean<>(400, "参数错误", null);
        }
        List<Goods> goodsList = goodsService.findGoodsByType(goodsType, pageNum, PAGE_SIZE);
        if (goodsList == null || goodsList.size() < 1) {
            return new ResultBean<>(200, "未查询到商品", null);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        return new ResultBean<>(200, "查询成功", pageInfo);
    }


    /**
     * 通过关键字查询
     * @param keyWord 关键字
     * @return 信息
     */
    @GetMapping("/admin/goods/search/{keyWord}")
    public ResultBean<List<Map<String, Object>>> searchGoodsByKeyWord(@PathVariable("keyWord") String keyWord) throws IOException {
        //判空
        if (keyWord == null || keyWord.length() < 1) {
            return new ResultBean<>(400, "参数错误", null);
        }
        List<Map<String, Object>> listMaps = goodsService.searchGoodsByKeyWord(keyWord);
        if (listMaps == null || listMaps.size() < 1) {
            return new ResultBean<>(200, "未查询到商品", null);
        } else {
            return new ResultBean<>(200, "查询成功", listMaps);
        }
    }


    /**
     * 修改商品状态
     * @param goodsId 商品id
     * @param goodsState 商品状态码
     * @return 修改结果
     */
    @PostMapping("/admin/goods/changeState")
    public ResultBean<Goods> changeGoodsStateById(Integer goodsId, Integer goodsState) {
        int maxType = 3;
        if (goodsId == null || goodsState == null || goodsState > maxType || goodsState < 0) {
            return new ResultBean<>(400, "参数错误", null);
        } else {
            Goods g = goodsService.changeGoodsStateById(goodsId, goodsState);
            if (g == null) {
                return new ResultBean<>(200, "商品id错误", null);
            } else {
                return new ResultBean<>(200, "修改成功", g);
            }
        }
    }
}
