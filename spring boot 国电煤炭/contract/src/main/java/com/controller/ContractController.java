package com.controller;

import com.entity.Goods;
import com.entity.ResultBean;
import com.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    //生成合同
    @RequestMapping("/addContract/{userId}/{sellId}/{goodsId}/{infoType}")
    public ResultBean addContract(@PathVariable("goodsId") Integer goodsId, @PathVariable("infoType") Integer infoType,@PathVariable("sellId") Integer sellId, @PathVariable("userId") Integer userId) {//userId乙方id
        Goods goods = new Goods();
        goods.setUserId(sellId);
        goods.setGoodsId(goodsId);
        goods.setInfoType(infoType);
        return contractService.addContract(goods, userId);
    }

    //确认合同
    @RequestMapping("/updateContractState")
    public ResultBean updateContractState(@RequestParam("contractId") Integer contractId){
        return contractService.updateContractState(contractId);
    }

    //查看合同
    @RequestMapping("/findContract")
    public ResultBean findContract(@RequestParam("contractId") Integer contractId) {
        return contractService.findContract(contractId);
    }

    //查看当前用户所有合同
    @RequestMapping("/findContractAll")
    public ResultBean findContractAll(@RequestParam("userId") Integer userId) {
        return contractService.findContractAll(userId);
    }
}
