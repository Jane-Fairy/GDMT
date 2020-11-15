package com.service;

import com.entity_personal.GoodsState;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author AngelTianJin
 * @version none
 * @date 2020/11/5 15:35
 */

public interface StatementService {
    PageInfo getGoodState(Integer page, Integer size);
}
