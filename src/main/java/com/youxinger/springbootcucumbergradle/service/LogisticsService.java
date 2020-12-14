package com.youxinger.springbootcucumbergradle.service;

import com.alibaba.fastjson.JSONObject;
import com.youxinger.springbootcucumbergradle.bean.OrderInfo;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import com.youxinger.springbootcucumbergradle.utils.TimeUtil;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author mengwei
 * 2020/7/2 13:42
 * @version 1.0
 */
@Service("LogisticsService")
public class LogisticsService {
    private static final Logger logger = LoggerFactory.getLogger(LogisticsService.class);

    @Resource
    protected DataManager dataManager;

    /**
     * 总仓发货
     *
     * @param orderInfo 订单信息
     */
    public void orderDeliver(OrderInfo orderInfo) {

        if (orderInfo == null) {
            return;
        }

        //导出订单
        Map<String, String> exportParams = new HashMap<>();
        exportParams.put("start", TimeUtil.getTodayTime());
        exportParams.put("end", TimeUtil.getTomorrowTime());
        Response exportResponse = given()
                .params(exportParams)
                .request()
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/orders/not-export-orders");

        logger.debug("exportResponse.msg={}", exportResponse.prettyPrint());

        //更新物流信息
        Map<String, String> deliverParams = new HashMap<>();
        deliverParams.put("order_id", orderInfo.getOrderId());
        deliverParams.put("tracking_number", "123456");
        Response deliverResponse = given()
                .header("Accept", "application/json, text/plain, */*")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("tid", dataManager.getSystemUser().getTid())
                .contentType("application/json")
                .body(JSONObject.toJSONString(deliverParams))
                .post(Constants.DOMAIN + "/backStage/orders/update-orders");

        logger.debug("deliverResponse.msg={}", deliverResponse.prettyPrint());
    }
}
