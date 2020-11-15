package com.service.impl;

import com.Component.RabbitProvider;
import com.dao.ContractDao;
import com.entity.Contract;
import com.entity.Goods;
import com.entity.ResultBean;
import com.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private RabbitProvider rabbitProvider;

    //添加合同信息
    @Override
    public ResultBean addContract(Goods goods, Integer userId) {
        ResultBean resultBean = null;
        Contract contract = new Contract();
        if(goods.getInfoType() == 0) {
            contract.setSellState(0);
            contract.setBuyState(1);
            contract.setSellId(goods.getUserId());
            contract.setBuyId(userId);
        } else {
            contract.setSellState(1);
            contract.setBuyState(0);
            contract.setSellId(userId);
            contract.setBuyId(goods.getUserId());
        }
        contract.setGoodsId(goods.getGoodsId());
        contract.setContractTime(new Date());
        contract.setContractState(0);
        try {
            contractDao.addContract(contract);
            resultBean = new ResultBean(200,"合同生成成功！",contract);
        } catch (Exception e) {
            resultBean = new ResultBean(400, "合同添加失败！未知错误！");
        }
        return resultBean;
    }

    //修改合同状态
    @Override
    public ResultBean updateContractState(Integer contractId) {
        ResultBean resultBean = null;
        try {
            int sum = contractDao.updateContractState(contractId);
            if(sum > 0) {
                resultBean = new ResultBean(200, "修改合同状态成功！", true);

                rabbitProvider.toOrder(contractDao.findContract(contractId));
            } else {
                resultBean = new ResultBean(201, "该合同不存在", false);
            }
        } catch (Exception e) {
            resultBean = new ResultBean(400, "修改状态失败！", false);
        }
        return resultBean;
    }

    //查看合同
    @Override
    public ResultBean findContract(Integer contractId) {
        ResultBean resultBean = null;
        try {
            if(contractId == null) {
                resultBean = new ResultBean(202, "合同id为空！", false);
            } else {
                Contract contract = contractDao.findContract(contractId);
                if(contract == null) {
                    resultBean = new ResultBean(201, "不存在该合同！", false);
                } else {
                    resultBean = new ResultBean(200, "查询合同成功！", contract);
                }
            }
        } catch (Exception e) {
            resultBean = new ResultBean(400, "查询合同失败！未知错误！", false);
        }
        return resultBean;
    }

    //查询当前用户所有合同
    @Override
    public ResultBean findContractAll(Integer userId) {
        ResultBean resultBean = null;
        try {
            List<Contract> contractList = contractDao.findContractAll(userId);
            if(contractList != null && contractList.size() > 0) {
                resultBean = new ResultBean(200, "查询成功！", contractList);
            } else {
                resultBean = new ResultBean(201, "无合同！", false);
            }
        } catch (Exception e) {
            resultBean = new ResultBean(400, "未知错误！", false);
        }
        return resultBean;
    }
}
