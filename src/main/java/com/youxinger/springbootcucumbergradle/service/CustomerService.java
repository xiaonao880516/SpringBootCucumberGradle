package com.youxinger.springbootcucumbergradle.service;

import com.alibaba.fastjson.JSONObject;
import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.entity.verifydata.CustomerVerifyData;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import io.restassured.response.Response;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

/**
 * @author mengwei
 * 2020/5/28 14:37
 * @version 1.0
 */
@Service("customerService")
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Resource
    protected DataManager dataManager;

    public void customerRegister(Customer customer) {
        if (customer == null) {
            fail("无效的客户,注册失败");
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("name", customer.getName());
            params.put("sex", customer.getSex());
            params.put("birthday", customer.getBirthday());
            params.put("phone", customer.getPhone());
            params.put("wx_headimgurl", "www.baidu.com");
            params.put("province", customer.getProvince());
            params.put("city", customer.getCity());
            params.put("nickname", customer.getName());
            params.put("openid", customer.getOpenid());
            if (customer.getEmployee() != null) {
                params.put("employee_number", customer.getEmployee().getId());
            }
            if (customer.getPlatform() != null) {
                params.put("platform_number", customer.getPlatform().getId());
            }
            Response response = given()
                    .contentType("application/json")
                    .body(JSONObject.toJSONString(params))
                    .post(Constants.WX_DOMAIN + "/api/lchmpFrontStage/reg")
                    .then()
                    .statusCode(200)
                    .body("msg", equalTo("注册成功!"))
                    .extract()
                    .response();
            String memberNumber = response.path("data");
            customer.setMember_number(memberNumber);
        }
    }

    public void customerRecharge(int amount, Customer customer, Employee employee) {
        if (customer == null || TextUtils.isEmpty(customer.getMember_number())) {
            fail("无效的客户");
        } else if (employee == null) {
            fail("无效的员工,无法充值");
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", amount);
            params.put("channel", "pos");
            params.put("member_number", customer.getMember_number());
            Response response = given()
                    .params(params)
                    .request()
                    .header("Accept", "application/json, text/plain, */*")
                    .header("tid", employee.getTid())
                    .post(Constants.DOMAIN + "/frontStage/recharge/generate-order")
                    .then()
                    .statusCode(200)
                    .body("msg", equalTo("充值订单创建成功"))
                    .extract().response();

            String rechargeOrderId = response.path("data");
            rechargeOrderPay(rechargeOrderId);
        }
    }

    /**
     * 充值订单付款
     *
     * @param rechargeOrderId 充值订单号
     */
    private void rechargeOrderPay(String rechargeOrderId) {
        given()
                .param("order_id", rechargeOrderId)
                .request()
                .header("Accept", "application/json, text/plain, */*")
                .post(Constants.DOMAIN + "/frontStage/recharge/ceshi")
                .then()
                .statusCode(200)
                .body("msg", equalTo("OK"));
    }

    public void customerDelete(Customer customer) {
        logger.debug("customerDelete");
        //TODO
    }

    public void getCustomerByPhone(String phone, Employee employee) {
        Response response = given()
                .param("phone", phone)
                .request()
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", employee.getTid())
                .post(Constants.DOMAIN + "/frontStage/vip/search-byphone");
        response.getBody();
    }

    public CustomerVerifyData getVerifyData(Customer customer) {
        logger.debug("getVerifyData Customer={}", customer);
        Response response = given()
                .param("member_number", customer.getMember_number())
                .request()
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/vip/vipmanage/get-info");

        String cardTypeName = response.path("data.cardtype_name");
        String swapScore = response.path("data.swap_score");
        Object balance = response.path("data.remainder");
        String consumption = response.path("data.total_consume");
        CustomerVerifyData customerVerifyData = new CustomerVerifyData(Float.parseFloat(consumption), Integer.parseInt(swapScore), cardTypeName, Float.parseFloat(balance.toString()));
        logger.debug("getVerifyData customerVerifyData={}", customerVerifyData);
        return customerVerifyData;
    }
}
