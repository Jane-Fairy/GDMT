package com.controller;

import com.entity_personal.ResultBean;
import com.entity_personal.GoodsState;
import com.github.pagehelper.PageInfo;
import com.service.impl.StatementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AngelTianJin
 * @version none 报表中心
 * @date 2020/11/5 15:32
 */
@RestController
@RequestMapping("/statement")
public class StatementController {
    /**
     * 注入对象StatementServiceImpl
     */
    private StatementServiceImpl statementService ;

    @Autowired
    public void setStatementService(StatementServiceImpl statementService){
        this.statementService = statementService;
    }

    /**
     *
     * 从后台获取煤种，销售量，销售额，销售时间
     * @return
     */
    @GetMapping("/getGoodsStateInfo/{page}/{size}")
    public ResultBean getGoodsStateInfo(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageInfo pageInfo = null;
        pageInfo   = statementService.getGoodState(page , size);
        /**
         * 判断页码是否规范
         */
        if (( page < pageInfo.getTotal() / pageInfo.getPageSize()+2))  {
            return new ResultBean(200, "查询成功",  pageInfo);
        }else {
            return new ResultBean(400, "查询失败!", null);
        }
    }
}
