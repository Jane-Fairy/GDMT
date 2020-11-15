package com.dao;

import com.entity_personal.GoodsState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author AngelTianJin
 * @version none
 * @date 2020/11/5 16:53
 */
@Repository
@Mapper
public interface StatementMapping {

    /**
     *
     * @return
     */
    @Select("select g.goods_type,g.goods_number,goods_price as totalSell,c.contract_time from goods g," +
            "contract c where  g.goods_id = c.goods_id order by c.contract_time DESC" )
    List<GoodsState> getGoodsState();

}
