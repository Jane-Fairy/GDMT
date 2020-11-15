package com.entity_personal;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


/**
 * @author AngelTianJin
 * @version none
 * @date 2020/11/5 17:17
 */
@Data
public class GoodsState {

    private String goodsType;
    private Integer goodsNumber;
    private Double totalSell;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contractTime;
}
