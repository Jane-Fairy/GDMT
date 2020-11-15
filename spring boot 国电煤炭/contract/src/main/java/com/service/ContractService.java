package com.service;

import com.entity.Goods;
import com.entity.ResultBean;

public interface ContractService {

    //生成合同
    ResultBean addContract(Goods goods, Integer userId);

    //修改合同状态
    ResultBean updateContractState(Integer contractId);

    //查看合同
    ResultBean findContract(Integer contractId);

    //查看当前用户所有合同
    ResultBean findContractAll(Integer userId);
}
