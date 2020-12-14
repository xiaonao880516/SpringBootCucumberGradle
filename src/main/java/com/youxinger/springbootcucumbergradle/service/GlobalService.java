package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Global;
import com.youxinger.springbootcucumbergradle.entity.verifydata.GlobalVerifyData;
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
 * 2020/5/28 14:55
 * @version 1.0
 */
@Service("globalService")
public class GlobalService {

    private static final Logger logger = LoggerFactory.getLogger(GlobalService.class);

    @Resource
    protected DataManager dataManager;

    public GlobalVerifyData getVerifyData(Global global) {
        logger.debug("getVerifyData global={}", global);

        Map<String ,String> params = new HashMap<>();
        params.put("page_size","15");
        params.put("page_num","1");
        params.put("store_name","");
        params.put("store_id","");
        params.put("center_name","");
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
        GlobalVerifyData globalVerifyData = new GlobalVerifyData();
        globalVerifyData.setSalesSum(Float.parseFloat(salesSum));
        return globalVerifyData;
    }
}
