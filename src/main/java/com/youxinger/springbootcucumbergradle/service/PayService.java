package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.utils.Constants;
import io.restassured.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

/**
 * @author mengwei
 * 2020/12/1 16:14
 * @version 1.0
 */
@Service
public class PayService {

    private static final Logger logger = LoggerFactory.getLogger(PayService.class);

    /**
     * 订单pos支付
     * @param orderId 订单号
     * @param realPay 金额
     */
    public void posOrderPay(String orderId, String realPay){
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
//                .body("msg", containsStringIgnoringCase("成功"))
                .extract().response();

        logger.debug("posOrderPay, response={}", response.prettyPrint());
    }
}
