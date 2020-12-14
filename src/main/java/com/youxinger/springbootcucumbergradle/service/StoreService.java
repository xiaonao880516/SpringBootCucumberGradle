package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Store;
import com.youxinger.springbootcucumbergradle.entity.verifydata.OperationCenterVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.StoreVerifyData;
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
 * 2020/6/2 17:04
 * @version 1.0
 */
@Service
public class StoreService {

    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);


    @Resource
    protected DataManager dataManager;

    public StoreVerifyData getVerifyData(Store store) {
        logger.debug("getVerifyData store={}", store);
        Map<String ,String> params = new HashMap<>();
        params.put("page_size","15");
        params.put("page_num","1");
        params.put("store_id",store.getNumber());
        params.put("center_id","");
        params.put("employee","");
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
        StoreVerifyData storeVerifyData = new StoreVerifyData();
        storeVerifyData.setSalesSum(Float.parseFloat(salesSum));
        return storeVerifyData;
    }
}
