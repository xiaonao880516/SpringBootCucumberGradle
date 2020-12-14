package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.ProvinceOperationCenter;
import com.youxinger.springbootcucumbergradle.entity.verifydata.CustomerVerifyData;
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
 * 2020/6/2 16:59
 * @version 1.0
 */
@Service
public class ProvinceOperationCenterService {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceOperationCenterService.class);

    @Resource
    protected DataManager dataManager;

    public ProvinceOperationCenterVerifyData getVerifyData(ProvinceOperationCenter provinceOperationCenter) {
        logger.debug("getVerifyData provinceOperationCenter={}", provinceOperationCenter);
        Map<String ,String> params = new HashMap<>();
        params.put("page_size","15");
        params.put("page_num","1");
        params.put("store_id","");
        params.put("center_id","");
        params.put("area_id",provinceOperationCenter.getNumber());
        params.put("employee","");
        params.put("start_time", TimeUtil.getMonthStartTime());
        params.put("end_time",TimeUtil.getMonthEndTime());

        Response response = given()
                .params(params)
                .request()
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/achieve/query-back");

        String salesSum = response.path("data.tot");
        ProvinceOperationCenterVerifyData provinceOperationCenterVerifyData = new ProvinceOperationCenterVerifyData();
        provinceOperationCenterVerifyData.setSalesSum(Float.parseFloat(salesSum));
        logger.debug("getVerifyData provinceOperationCenterVerifyData={}", provinceOperationCenterVerifyData);
        return provinceOperationCenterVerifyData;
    }
}
