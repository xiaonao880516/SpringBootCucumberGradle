package com.youxinger.springbootcucumbergradle.service;

import com.alibaba.fastjson.JSONObject;
import com.youxinger.springbootcucumbergradle.bean.CustomerAddress;
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
            createCustomerAddress(customer);
        }
    }

    /**
     * 客户充值
     * @param amount
     * @param customer
     */
    public String customerRecharge(int amount, Customer customer) {
        if (customer == null || TextUtils.isEmpty(customer.getMember_number())) {
            fail("无效的客户");
        } else if (customer.getEmployee() == null) {
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
                    .header("tid", customer.getEmployee().getTid())
                    .post(Constants.DOMAIN + "/frontStage/recharge/generate-order")
                    .then()
                    .statusCode(200)
                    .body("msg", equalTo("充值订单创建成功"))
                    .extract().response();

            return response.path("data");
        }
        return null;
    }

    /**
     * 创建客户收回信息
     * @param customer
     */
    private void createCustomerAddress(Customer customer){
        Map<String, String> addressParams = new HashMap<>();
        addressParams.put("address",customer.getAddress());
        addressParams.put("area",customer.getArea());
        addressParams.put("city",customer.getCity());
        addressParams.put("consignee",customer.getConsignee());
        addressParams.put("consignee_phone",customer.getPhone());
        addressParams.put("is_default","1");
        addressParams.put("member_number",customer.getMember_number());
        addressParams.put("province",customer.getProvince());

        Response response = given()
                .header("Accept", "application/json, text/plain, */*")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("tid", customer.getEmployee().getTid())
                .contentType("application/json")
                .body(JSONObject.toJSONString(addressParams))
                .post(Constants.DOMAIN + "/frontStage/vip/address/create-address")
                .then()
                .statusCode(200)
                .body("msg", equalTo("操作成功"))
                .extract().response();

        int addressId = response.path("data");
        String addressIdStr = String.valueOf(addressId);
        if(!TextUtils.isEmpty(addressIdStr)){
            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.setId(addressIdStr);
            customerAddress.setAddress(customer.getAddress());
            customerAddress.setArea(customer.getArea());
            customerAddress.setCity(customer.getCity());
            customerAddress.setConsignee(customer.getConsignee());
            customerAddress.setConsignee_phone(customer.getPhone());
            customerAddress.setMember_number(customer.getMember_number());
            customerAddress.setProvince(customer.getProvince());
            customer.setCustomerAddress(customerAddress);
        }

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
