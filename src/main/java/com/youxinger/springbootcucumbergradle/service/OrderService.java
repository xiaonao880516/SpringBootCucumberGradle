package com.youxinger.springbootcucumbergradle.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import io.restassured.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @author mengwei
 * 2020/6/4 15:21
 * @version 1.0
 */
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void posOrder(Customer customer){

        String paramsStr = "{\"price\": \"33800.00\", \"discount_money\": \"2704.00\", \"real_pay\": \"31096.00\", " +
                "\"discount_rate\": \"0.92\",\n" +
                "\"goods_list[0][danjia]\": \"3380.00\", \"goods_list[0][sku_num]\": \"10\", \"goods_list[0][sku_name]\": \"腰背夹\",\n" +
                "\"goods_list[0][price]\": \"33800.00\", \"goods_list[0][real_pay_price]\": \"31096.00\", \"goods_list[0][discount_price]\": \"2704.00\",\n" +
                "\"goods_list[0][sku_id]\": \"4878\", \"goods_list[0][tiaoma]\": \"M216C237C0458\", \"goods_list[0][kuanhao]\": \"M216C237\",\n" +
                "\"goods_list[0][sku_detail]\": \"深蓝色 58\", \"goods_list[0][img]\": \"https://lchapp.oss-cn-beijing.aliyuncs.com/2019010579241063815.jpg\",\n" +
                "\"goods_list[0][repo_out_num]\": \"5\", \"goods_list[0][com_out_num]\": \"5\", \"goods_list[0][no_discount]\": \"0\", \"goods_list[0][no_score]\": \"0\",\n" +
                "\"goods_list[0][is_active]\": \"0\", \"goods_list[0][type]\": \"1\", \"pay_type\": \"pos\", \"zip_code\": \"\", \"referral_phone\": \"\", \"beizhu\": \"\",\n" +
                "\"discount_id\": \"\", \"discount_description\": \"\", \"coupon_id\": \"\", \"coupon_discount_amount\": \"0.00\", \"coupon_discount_rate\": \"\"}";

        HashMap<String, String> params = JSON.parseObject(paramsStr, HashMap.class);
        params.put("receive_name", customer.getConsignee());
        params.put("receive_phone", customer.getPhone());
        params.put("receive_sheng", customer.getProvince());
        params.put("receive_shi", customer.getCity());
        params.put("receive_diqu", customer.getArea());
        params.put("receive_address", customer.getAddress());
        params.put("member_id", customer.getMember_number());
        params.put("member_name", customer.getName());
        params.put("member_phone", customer.getPhone());
        params.put("plateform_id", String.valueOf(customer.getPlatform().getId()));
        params.put("special_employee_id", String.valueOf(customer.getEmployee().getId()));

//        logger.debug("posOrder, params=()", params);

        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", customer.getEmployee().getTid())
                .post(Constants.DOMAIN + "/frontStage/orders/new-orders")
                .then()
                .statusCode(200)
                .body("msg", equalTo("下单成功"))
                .extract().response();

        String orderId = response.path("data.order_id");
        String realPay = response.path("data.real_pay");
        posOrderPay(orderId, realPay);
    }


    private void posOrderPay(String orderId, String realPay){
        realPay = realPay.replace(".", "");
        String pay_key = "b2FzZGblubLmraPkuovvvIzor7fkuI3opoHov53ms5UlQCMmKlFlMWhmMQ";
        String mernum = "898110256914031";
        String termid = "77347852";
        String trandate = "20180821";
        String trantime = "163828";
        String referno = "154140244647";
        String pan = "481699******4413";
        String val = DigestUtils.md5Hex(mernum + termid + trandate + trantime + referno + pan + realPay + pay_key);
        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><trandate>"+trandate+"</trandate><mernum>"+mernum+"</mernum><termid>"+termid+"</termid><referno>"+referno+"</referno><trantype>01</trantype><trantime>"+trantime+"</trantime><pan>"+pan+"</pan><sign>"+val+"</sign><amt>"+realPay+"</amt><batchno>000003</batchno><ext1>1</ext1><ext2></ext2><paytype>1</paytype><channel>11</channel><orderId>"+orderId+"</orderId><serialno>002137</serialno></request>";

        Response response = given()
                .body(body)
                .post(Constants.DOMAIN + "/frontStage/wfjpay/order-done")
                .then()
                .statusCode(200)
//                .body("msg", equalTo("成功"))
                .extract().response();

//        logger.error("posOrderPay, response={}", response.prettyPrint());
    }

}
