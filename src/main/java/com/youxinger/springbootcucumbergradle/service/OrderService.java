package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.bean.OrderInfo;
import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import io.restassured.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author mengwei
 * 2020/6/4 15:21
 * @version 1.0
 */
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    protected DataManager dataManager;

    /**
     * 创建pos支付订单
     * @param customer 客户
     * @param params 支付订单参数
     * @return
     */
    public OrderInfo posOrder(Customer customer, Map<String , String> params){
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
        logger.debug("posOrder, response={}", response.prettyPrint());

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(response.path("data.order_id"));
        String realPay = response.path("data.real_pay");
        orderInfo.setRealPay(realPay.replace(".", ""));
        return orderInfo;
    }

    /**
     * 创建余额支付订单
     * @param customer
     * @param params
     * @return
     */
    public OrderInfo rechargeOrder(Customer customer , Map<String , String> params){
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
        logger.debug("rechargeOrder, response={}", response.prettyPrint());

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(response.path("data.order_id"));
        String realPay = response.path("data.real_pay");
        orderInfo.setRealPay(realPay.replace(".", ""));
        return orderInfo;
    }

    /**
     * 创建积分商城订单
     * @param customer 客户
     * @param params 支付订单参数
     * @return
     */
    public OrderInfo pointMallOrder(Customer customer, Map<String , String> params){
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", customer.getEmployee().getTid())
                .post(Constants.DOMAIN + "/frontStage/pointsmall/order/new-orders")
                .then()
                .statusCode(200)
                .body("msg", equalTo("下单成功"))
                .extract().response();
        logger.debug("pointMallOrder, response={}", response.prettyPrint());

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(response.path("data.order_id"));
        return orderInfo;
    }

    /**
     * 积分商城纯积分订单支付
     * @param orderInfo
     */
    public void pointMallOrderPay(OrderInfo orderInfo){

        Response response = given()
                .formParam("order_id",orderInfo.getOrderId())
                .request()
                .post(Constants.DOMAIN + "/frontStage/pointsmall/order/score-exchange")
                .then()
                .statusCode(200)
                .body("msg", equalTo("兑换成功"))
                .extract().response();

        logger.debug("rechargeOrderPay, response={}", response.prettyPrint());
    }


    /**
     * 订单余额支付
     * @param orderInfo
     */
    public void rechargeOrderPay(OrderInfo orderInfo){

        Response response = given()
                .formParam("order_id",orderInfo.getOrderId())
                .request()
                .post(Constants.WX_DOMAIN + "/api/lchmpFrontStage/recharge/pay-ceshi")
                .then()
                .statusCode(200)
                .body("msg", equalTo("余额支付成功"))
                .extract().response();

        logger.debug("rechargeOrderPay, response={}", response.prettyPrint());
    }

//    /**
//     * 订单pos支付
//     * @param orderInfo
//     */
//    public void posOrderPay(OrderInfo orderInfo){
//        String pay_key = "b2FzZGblubLmraPkuovvvIzor7fkuI3opoHov53ms5UlQCMmKlFlMWhmMQ";
//        String mernum = "898110256914031";
//        String termid = "77347852";
//        String trandate = "20180821";
//        String trantime = "163828";
//        String referno = "154140244647";
//        String pan = "481699******4413";
//        String val = DigestUtils.md5Hex(mernum + termid + trandate + trantime + referno + pan + orderInfo.getRealPay() + pay_key);
//        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><trandate>"+trandate+"</trandate><mernum>"+mernum+"</mernum><termid>"+termid+"</termid><referno>"+referno+"</referno><trantype>01</trantype><trantime>"+trantime+"</trantime><pan>"+pan+"</pan><sign>"+val+"</sign><amt>"+orderInfo.getRealPay()+"</amt><batchno>000003</batchno><ext1>1</ext1><ext2></ext2><paytype>1</paytype><channel>11</channel><orderId>"+orderInfo.getOrderId()+"</orderId><serialno>002137</serialno></request>";
//
//        Response response = given()
//                .body(body)
//                .post(Constants.DOMAIN + "/frontStage/wfjpay/order-done")
//                .then()
//                .statusCode(200)
////                .body("msg", containsStringIgnoringCase("成功"))
//                .extract().response();
//
//        logger.debug("posOrderPay, response={}", response.prettyPrint());
//    }


    /**
     * 取消订单
     * @param customer
     * @param orderId
     */
    public void cancelOrder(Customer customer,String orderId){
        HashMap<String, String> params = new HashMap<>(16);
        params.put("order_id",orderId);
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", customer.getEmployee().getTid())
                .post(Constants.DOMAIN + "/frontStage/orders/cancel-orders")
                .then()
                .statusCode(200)
                .body("msg", equalTo("操作成功"))
                .extract().response();

        logger.debug("cancelOrder, response={}", response.prettyPrint());
    }

    /**
     * 创建退货申请订单
     * @param customer
     * @param refundParams
     * @return
     */
    public String createRefundOrder(Customer customer, Map<String, String> refundParams){
        Response response = given()
                .params(refundParams)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", customer.getEmployee().getTid())
                .post(Constants.DOMAIN + "/frontStage/aftersale/apply-return")
                .then()
                .statusCode(200)
                .body("msg", equalTo("申请退货成功"))
                .extract().response();

        logger.debug("createRefundOrder, response={}", response.prettyPrint());
        return response.path("data.aftersale_id");
    }


    /**
     * 创建换货订单
     * @param customer
     * @param changeParams
     * @return
     */
    public String createChangeOrder(Customer customer, Map<String, String> changeParams){
        Response response = given()
                .params(changeParams)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", customer.getEmployee().getTid())
                .post(Constants.DOMAIN + "/frontStage/aftersale/apply")
                .then()
                .statusCode(200)
                .body("msg", equalTo("数据更新成功"))
                .extract().response();

        logger.debug("createChangeOrder, response={}", response.prettyPrint());
        return response.path("data.aftersale_id");
    }

    /**
     * 售后审核
     * @param customer
     * @param reviewType 1：退货; 2：换货
     * @param afterSaleId
     */
    public void afterSaleReview(Customer customer, int reviewType, String afterSaleId) throws Throwable{
        //等待10妙，解决审核过快系统无法处理的问题
        TimeUnit.SECONDS.sleep(10);

        Map<String, String> params = new HashMap<>();
        params.put("aftersale_id", afterSaleId);
        params.put("bank_name", "");
        params.put("card_no", "");
        params.put("status", "1");//审核通过
        params.put("refunder", "");
        params.put("reason", "自动化测试审核通过");
        params.put("type", String.valueOf(reviewType));
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", customer.getEmployee().getTid())
                .post(Constants.DOMAIN + "/frontStage/aftersale/review")
                .then()
                .statusCode(200)
//                .body("msg", equalTo("成功"))
                .extract().response();

        logger.debug("afterSaleReview, response={}", response.prettyPrint());
    }


    /**
     * 积分商城订单后台取消兑换
     * @param customer
     * @param pointMallOrderId
     * @return
     */
    public void pointMallBackGroundRefundOrder(Customer customer, String pointMallOrderId){
        Map<String, String> params = new HashMap<>();
        params.put("order_id", pointMallOrderId);
        params.put("type", "2");
        params.put("beizhu", "自动化积分商城订单后台退货");
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/pointsmall/aftersale/refund")
                .then()
                .statusCode(200)
                .body("msg", equalTo("操作成功"))
                .extract().response();

        logger.debug("pointMallBackGroundRefundOrder, response={}", response.prettyPrint());
    }

    /**
     * 关键字 查询 售后订单订单号
     * @param customer
     * @param keywords 要查询的关键字
     */
    public String getAfterSaleOrderIdByKeywords(Customer customer, String keywords) {
        Map<String, String> params = new HashMap<>();
        params.put("order_id", keywords);
        params.put("order_status", "0");
        params.put("search_type", "store");
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", customer.getEmployee().getTid())
                .get(Constants.DOMAIN + "/frontStage/orders/search-orders")
                .then()
                .statusCode(200)
                .body("msg", equalTo(""))
                .extract().response();

        logger.debug("getAfterSaleOrderIdByKeywords, response={}", response.prettyPrint());
        String afterSaleOrderId = null;
        try {
            afterSaleOrderId = response.path("data.all_goods[0].aftersale_orders_info[0].order_id");
        }catch (Exception e){
            logger.warn("没有查到关键字: {} 的售后订单", keywords);
        }
        return afterSaleOrderId;
    }


}
