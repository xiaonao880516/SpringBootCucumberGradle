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
public class RechargeService {

//    private static final Logger logger = LoggerFactory.getLogger(RechargeService.class);

    @Resource
    protected DataManager dataManager;

    /**
     * 余额转出
     * @param customerMemberNumber  会员编号
     * @param phone 转出至手机号
     * @param amount    转出金额
     */
    public void remainderRollOut(String customerMemberNumber, String phone ,int amount){
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("member_number",customerMemberNumber);
        params.put("amount",amount);
        params.put("phone",phone);
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/vip/vipcardmanage/remainder-roll-out")
                .then()
                .statusCode(200)
                .body("msg", equalTo("操作成功"))
                .extract().response();
    }

    /**
     * 余额提现
     * @param customerMemberNumber  会员编号
     * @param amount    提现金额
     * @param score 提现扣减积分数
     */
    public void withdrawal(String customerMemberNumber, int amount, int score){
        HashMap<String, Object> params = new HashMap<>(16);
        params.put("member_number",customerMemberNumber);
        params.put("amount",amount);
        params.put("score",score);
        params.put("minus_introducer_score","0");
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/vip/vipcardmanage/cash-out")
                .then()
                .statusCode(200)
                .body("msg", equalTo("操作成功"))
                .extract().response();
    }

}
