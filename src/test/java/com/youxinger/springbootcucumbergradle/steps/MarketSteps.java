package com.youxinger.springbootcucumbergradle.steps;

import cucumber.api.java.zh_cn.当;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengwei
 * 2020/6/2 16:14
 * @version 1.0
 */
public class MarketSteps extends BaseSteps{

    private static final Logger logger = LoggerFactory.getLogger(MarketSteps.class);

    @当("^该客户下单买入商品([^\"]*)， 总仓(\\d+)个，门店(\\d+)个，([^\"]*)支付方式$")
    public void buy(String barcode, int quantity, int storeQuantity, String payType) throws Throwable {
        logger.debug("buy, barcode={}, quantity={}, storeQuantity={}, payType={}", barcode, quantity, storeQuantity, payType);
        //TODO
    }
}
