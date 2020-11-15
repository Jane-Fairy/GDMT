package com.controller;

import com.entity.Goods;
import com.entity.ResultBean;
import com.github.pagehelper.PageInfo;
import com.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 寰
 * @create 2020-10-29-14:38
 * 用户商品controller
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询所有用户通过审核的商品
     * @return
     */
    @PostMapping(value = "/userFindAll")
    public Object findAll(@RequestParam(name = "page",defaultValue = "1") int page){
        List<Goods> goodsList = goodsService.findAll(page,15);
        PageInfo pageInfo=new PageInfo(goodsList);
        if(goodsList!=null){
            ResultBean resultBean = new ResultBean(200,"查询成功",pageInfo);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"查询失败");
        return resultBean;
    }

    /**
     * 用户添加插入商品
     * @return
     */
    @PostMapping(value = "/userInsert")
    public Object insert( Goods goods){
        boolean flag = goodsService.insert(goods);
        if(flag){
            ResultBean resultBean = new ResultBean(200,"插入成功",flag);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"插入失败");
        return resultBean;

    }

    /**
     * 日期格式化
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    /**
     * 根据id查询商品
     * @return
     */
    @PostMapping (value = "/useFindById")
    public Object findById(HttpServletRequest request){
        String goodsId = request.getParameter("goodsId");
        Goods goodsById = goodsService.findGoodsById(Integer.parseInt(goodsId));
        if(goodsById!=null){
            ResultBean resultBean = new ResultBean(200,"查询成功",goodsById);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"查询失败");
        return resultBean;

    }

    /**
     * 删除商品
     * @return
     */
    @PostMapping (value = "/userDelete")
    public Object delete(HttpServletRequest request,Integer goodsId){
//        String goodsId = request.getParameter("goodsId").trim();
//        boolean flag = goodsService.deleteGoodsById(Integer.parseInt(goodsId));
        boolean flag = goodsService.deleteGoodsById(goodsId);
        if(flag){
            ResultBean resultBean = new ResultBean(200,"删除成功");
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"删除失败");
        return resultBean;
    }

    /**
     * 查询用户全部未审核的商品信息
     */
    @PostMapping(value = "/userFindStateZero")
    public ResultBean findStateZero(@RequestParam("userId") Integer userId,@RequestParam(name = "page",defaultValue = "1") int page,@RequestParam(name = "pageNum",defaultValue = "15") int pageNum){
        //0：未审核，1：驳回，2：审核通过，3：下架
        List<Goods> goodsList = goodsService.findGoodsByGoodsState(page,pageNum,0,userId);
        PageInfo pageInfo=new PageInfo(goodsList);

        if(goodsList!=null){
            ResultBean resultBean = new ResultBean(200,"查询成功",pageInfo);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"没有商品");
        return resultBean;
    }

    /**
     * 查询用户审核通过的商品信息
     */
    @PostMapping(value = "/userFindStateTwo")
    public Object findStateTwo(@RequestParam("userId") Integer userId,@RequestParam(name = "page",defaultValue = "1") int page){
        //0：未审核，1：驳回，2：审核通过，3：下架
        List<Goods> goodsList = goodsService.findGoodsByGoodsState(page,15,2,userId);
        PageInfo pageInfo=new PageInfo(goodsList);
        if(goodsList!=null){
            ResultBean resultBean = new ResultBean(200,"查询成功",pageInfo);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"没有商品");
        return resultBean;
    }
    /**
     * 查询用户下架的商品信息
     */
    @PostMapping(value = "/userFindStateThree")
    public Object findStateThree(@RequestParam("userId") Integer userId,@RequestParam(name = "page",defaultValue = "1")int page){
        //0：未审核，1：驳回，2：审核通过，3：下架
        List<Goods> goodsList = goodsService.findGoodsByGoodsState(page,15,3,userId);
        PageInfo pageInfo=new PageInfo(goodsList);
        if(goodsList!=null){
            ResultBean resultBean = new ResultBean(200,"查询成功",pageInfo);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"没有商品");
        return resultBean;
    }




}
