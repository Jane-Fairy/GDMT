package com.service.impl;

import com.dao.StatementMapping;
import com.entity_personal.GoodsState;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.StatementService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author AngelTianJin
 * @version none
 * @date 2020/11/5 17:10
 */

@Service
@Transactional
public class StatementServiceImpl implements StatementService {

    private  StatementMapping statementMapping;
    @Autowired
    public void setStatementMapping(StatementMapping statementMapping){
        this.statementMapping = statementMapping;
    }

    @Override
    public PageInfo getGoodState(Integer page, Integer size) {
        /**
         * 分页操作
         */
        PageHelper.startPage(page,size);
        List<GoodsState> list = statementMapping.getGoodsState();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
