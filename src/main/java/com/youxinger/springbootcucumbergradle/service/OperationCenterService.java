package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.OperationCenter;
import com.youxinger.springbootcucumbergradle.entity.verifydata.OperationCenterVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProvinceOperationCenterVerifyData;
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
 * 2020/6/2 16:56
 * @version 1.0
 */
@Service
public class OperationCenterService {


    private static final Logger logger = LoggerFactory.getLogger(OperationCenterService.class);

    @Resource
    protected DataManager dataManager;

    public OperationCenterVerifyData getVerifyData(OperationCenter operationCenter) {
        logger.debug("getVerifyData operationCenter={}", operationCenter);
        Map<String ,String> params = new HashMap<>();
        params.put("page_size","15");
        params.put("page_num","1");
        params.put("store_name","");
        params.put("center_name",operationCenter.getName());
        params.put("area_name","");
        params.put("platform_name","");
        params.put("area_id","");
        params.put("start_time", TimeUtil.getMonthStartTime());
        params.put("end_time",TimeUtil.getMonthEndTime());

        Response response = given()
                .params(params)
                .request()
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/achieve/query-back");

        String salesSum = response.path("data.tot");
        OperationCenterVerifyData operationCenterVerifyData = new OperationCenterVerifyData();
        operationCenterVerifyData.setSalesSum(Float.parseFloat(salesSum));
        return operationCenterVerifyData;
    }
}
