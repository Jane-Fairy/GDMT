package com.controller;

import com.entity.Order;
import com.entity.ResultBean;
import com.entity.Torder;
import com.github.pagehelper.PageInfo;
import com.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 寰
 * @create 2020-11-04-16:27
 */
@RestController
@RequestMapping("/logistics")
public class LogisticsController {
    @Autowired
    private LogisticsService logisticsService;

    /**
     * 创建物流订单
     * @param order
     * @param userId
     * @param phone
     * @return
     */
    @PostMapping("/create/{userId}/{phone}")
    public ResultBean creat(@RequestBody Order order,@PathVariable("userId")Integer userId,@PathVariable("phone") String phone){
        return logisticsService.createTorder(order,userId,phone);
    }

    /**
     * 计算运煤总价
     * @param goodsId
     * @return
     */
    @PostMapping("/price")
    public Object price(Integer goodsId){
        int goodsQuantity = logisticsService.findGoodsQuantity(goodsId);
        //运送煤的单价5000/万吨
        int sum=goodsQuantity*5000;
        if (goodsQuantity!=0){
            ResultBean resultBean = new ResultBean(200,"总价计算成功",sum);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"查询失败");
        return resultBean;
    }

    /**
     * 查询用户下所有已揽件的订单
     * @param userId
     * @return
     */
    @PostMapping("/findAllByZero")
    public Object findAllByZero(@RequestParam(name="userId") Integer userId,@RequestParam(name = "page",defaultValue = "1") int page) {
        List<Torder> list = logisticsService.findAllByState(page,15,0,userId);
        PageInfo pageInfo = new PageInfo(list);
        if (list!=null&&list.size()!=0){
            ResultBean resultBean = new ResultBean(200,"查询成功",pageInfo);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"查询失败");
        return resultBean;
    }
    /**
     * 查询用户下所有已签收的订单
     * @param userId
     * @return
     */
    @PostMapping("/findAllByOne")
    public Object findAllByOne(@RequestParam(name="userId") Integer userId,@RequestParam(name = "page",defaultValue = "1") int page) {
        List<Torder> list = logisticsService.findAllByState(page,15,1,userId);
        PageInfo pageInfo = new PageInfo(list);
        if (list!=null&&list.size()!=0){
            ResultBean resultBean = new ResultBean(200,"查询成功",pageInfo);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"查询失败");
        return resultBean;
    }
    /**
     * 查询用户下所有停滞的订单
     * @param userId
     * @return
     */
    @PostMapping("/findAllByTwo")
    public Object findAllByTwo(@RequestParam(name="userId") Integer userId,@RequestParam(name = "page",defaultValue = "1") int page) {
        List<Torder> list = logisticsService.findAllByState(page,15,2,userId);
        PageInfo pageInfo = new PageInfo(list);
        if (list!=null&&list.size()!=0){
            ResultBean resultBean = new ResultBean(200,"查询成功",pageInfo);
            return resultBean;
        }
        ResultBean resultBean = new ResultBean(400,"查询失败");
        return resultBean;
    }

}
