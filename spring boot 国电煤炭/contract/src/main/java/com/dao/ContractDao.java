package com.dao;

import com.entity.Contract;
import com.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContractDao {

    //添加合同
    @Insert("INSERT INTO contract(goods_id,sell_state,buy_state,sell_id,buy_id,contract_time,contract_state)")
    @Options(useGeneratedKeys = true, keyProperty = "contractId", keyColumn = "contract_id")
    Integer addContract(Contract contract);

    //修改合同状态
    @Update("UPDATE contract SET sell_state=1,buy_state=1,contract_state=1 WHERE contract_id=#{contractId}")
    Integer updateContractState(Integer contractId);

    //查询合同
    @Select("SELECT * FROM contract WHERE contract_id=#{contractId}")
    @Results({
            @Result(id = true, property = "contractId", column = "contract_id"),
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "goods", column = "goods_id", javaType = com.entity.Goods.class,
                    one = @One(select = "com.dao.ContractDao.findGoodsById"))
    })
    Contract findContract(Integer contractId);

    //查询goods
    @Select("SELECT * FROM goods WHERE goods_id=#{goodsId}")
    Goods findGoodsById(Integer goodsId);

    //查询当前用户所有合同
    //查询合同
    @Select("SELECT * FROM contract WHERE sell_id=#{userId} OR buy_id=#{userId}")
    @Results({
            @Result(id = true, property = "sellId", column = "sell_id"),
            @Result(id = true, property = "buyId", column = "buy_id"),
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "goods", column = "goods_id", javaType = com.entity.Goods.class,
                    one = @One(select = "com.dao.ContractDao.findGoodsById"))
    })
    List<Contract> findContractAll(Integer userId);
}
