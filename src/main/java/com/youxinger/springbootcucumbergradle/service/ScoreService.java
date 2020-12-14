package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author : jwtan
 * @date : 2020/7/2
 */
@Service
public class ScoreService {

//    private static final Logger logger = LoggerFactory.getLogger(ScoreService.class);

    @Resource
    protected DataManager dataManager;

    /**
     * 积分调整
     * @param customerMemberNumber 会员编号
     * @param direction 增加/扣减，1加，0减
     * @param score 积分数
     */
    public void changeScore(String customerMemberNumber, int direction, int score){
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("member_number",customerMemberNumber);
        params.put("direction",direction);
        params.put("score",score);
        params.put("reason","自动化测试");
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/vip/vipinfo/change-score")
                .then()
                .statusCode(200)
                .body("msg", equalTo("操作成功"))
                .extract().response();
    }
}
