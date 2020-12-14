package com.youxinger.springbootcucumbergradle.steps.defintions;

import com.youxinger.springbootcucumbergradle.bean.OrderInfo;
import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.service.LogisticsService;
import com.youxinger.springbootcucumbergradle.service.OrderService;
import com.youxinger.springbootcucumbergradle.service.PayService;
import com.youxinger.springbootcucumbergradle.steps.BaseSteps;
import cucumber.api.java.zh_cn.当;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mengwei
 * 2020/7/2 10:30
 * @version 1.0
 */
public class GeneralProductsSteps extends BaseSteps {

    private static final Logger logger = LoggerFactory.getLogger(GeneralProductsSteps.class);

    @Resource
    private OrderService orderService;

    @Resource
    private PayService payService;

    @Resource
    private LogisticsService logisticsService;

    @当("^客户下单买入商品M116A227B01_A65， 总仓5个，门店原始仓5个，pos支付方式$")
    public void customerBuy() throws Throwable {
        cachePreVerifyData();
        for (String customerName : dataManager.getCustomerMap().keySet()) {
            customerBuy(customerName);
        }
        cachePostVerifyData();
    }

    @当("^客户([^\"]+)下单买入商品M116A227B01_A65， 总仓5个，门店原始仓5个，pos支付方式$")
    public void customerBuy(String customerName) throws Throwable {
        Customer customer = dataManager.getCustomerByName(customerName);
        Map<String , String> orderParams = new HashMap<>();
        orderParams.put("member_phone", customer.getPhone());
        orderParams.put("price", "1980000");
        orderParams.put("discount_money", "158400");
        orderParams.put("real_pay", "1821600");
        orderParams.put("referral_phone", "");
        orderParams.put("is_discount", "1");
        orderParams.put("beizhu", "");
        orderParams.put("goods_list[0][danjia]", "198000");
        orderParams.put("goods_list[0][sku_num]", "10");
        orderParams.put("goods_list[0][price]", "1980000");
        orderParams.put("goods_list[0][real_pay_price]", "1821600");
        orderParams.put("goods_list[0][discount_price]", "158400");
        orderParams.put("goods_list[0][tiaoma]", "M116A227B01_A65");
        orderParams.put("goods_list[0][repo_out_num]", "0");
        orderParams.put("goods_list[0][gif_out_num]", "5");
        orderParams.put("goods_list[0][com_out_num]", "5");
        orderParams.put("goods_list[0][type]", "1");
        orderParams.put("goods_list[0][zh_num]", "");
        orderParams.put("goods_list[0][zh_repo_out_num]", "");
        orderParams.put("goods_list[0][zh_gif_out_num]", "");
        orderParams.put("goods_list[0][zh_com_out_num]", "");
        orderParams.put("goods_list[0][zh_tiaoma]", "");
        orderParams.put("pay_type", "pos");
        orderParams.put("coupon_id", "");
        orderParams.put("coupon_discount_amount", "0");
        orderParams.put("coupon_discount_rate", "");
        orderParams.put("address_id", customer.getCustomerAddress().getId());
        OrderInfo orderInfo = orderService.posOrder(customer, orderParams);
        customer.setOrderInfo(orderInfo);
        payService.posOrderPay(orderInfo.getOrderId(), orderInfo.getRealPay());
        logisticsService.orderDeliver(orderInfo);
    }


    @当("^客户退货M216C237C0458商品2件$")
    public void customerRefund2() throws Throwable {
        cachePreVerifyData();
        for (String customerName : dataManager.getCustomerMap().keySet()) {
            customerRefund2(customerName);
        }
        cachePostVerifyData();
    }

    @当("^客户([^\"]+)退货M216C237C0458商品2件$")
    public void customerRefund2(String customerName) throws Throwable {
        Customer customer = dataManager.getCustomerByName(customerName);
        String refundOrderId = customer.getOrderInfo().getOrderId() + "_0";
        Map<String , String> refundOrderParams = new HashMap<>();
        refundOrderParams.put("main_order_id", customer.getOrderInfo().getOrderId());
        refundOrderParams.put("return_price", "3643.20");
        refundOrderParams.put("reason", "15天无理由退货");
        refundOrderParams.put("remarks", "");
        refundOrderParams.put("afterSales_info[0][order_id]", refundOrderId);
        refundOrderParams.put("afterSales_info[0][danjia]", "1980.00");
        refundOrderParams.put("afterSales_info[0][sku_name]", "无钢托短文胸");
        refundOrderParams.put("afterSales_info[0][sku_detail]", "黑色 65 A");
        refundOrderParams.put("afterSales_info[0][tiaoma]", "M116A227B01_A65");
        refundOrderParams.put("afterSales_info[0][kuanhao]", "M116A227");
        refundOrderParams.put("afterSales_info[0][sku_id]", "4542");
        refundOrderParams.put("afterSales_info[0][img]", " https://lchapp.oss-cn-beijing.aliyuncs.com/2019010596473181025.jpg");
        refundOrderParams.put("afterSales_info[0][aftersale_num]", "2");
        refundOrderParams.put("afterSales_info[0][aftersale_money]", "3643.20");
        refundOrderParams.put("afterSales_info[0][goods_type]", "1");
        refundOrderParams.put("is_confirm", "1");

        String afterSaleId = orderService.createRefundOrder(customer, refundOrderParams);
        orderService.afterSaleReview(customer, 1, afterSaleId);
    }

    @当("^客户退货M216C237C0458商品8件$")
    public void customerRefund8() throws Throwable {
        cachePreVerifyData();
        for (String customerName : dataManager.getCustomerMap().keySet()) {
            customerRefund8(customerName);
        }
        cachePostVerifyData();
    }

    @当("^客户([^\"]+)退货M216C237C0458商品8件$")
    public void customerRefund8(String customerName) throws Throwable {
        Customer customer = dataManager.getCustomerByName(customerName);
        String refundOrderId = customer.getOrderInfo().getOrderId() + "_0";
        Map<String , String> refundOrderParams = new HashMap<>();
        refundOrderParams.put("main_order_id", customer.getOrderInfo().getOrderId());
        refundOrderParams.put("return_price", "14572.80");
        refundOrderParams.put("reason", "15天无理由退货");
        refundOrderParams.put("remarks", "");
        refundOrderParams.put("afterSales_info[0][order_id]", refundOrderId);
        refundOrderParams.put("afterSales_info[0][danjia]", "1980.00");
        refundOrderParams.put("afterSales_info[0][sku_name]", "无钢托短文胸");
        refundOrderParams.put("afterSales_info[0][sku_detail]", "黑色 65 A");
        refundOrderParams.put("afterSales_info[0][tiaoma]", "M116A227B01_A65");
        refundOrderParams.put("afterSales_info[0][kuanhao]", "M116A227");
        refundOrderParams.put("afterSales_info[0][sku_id]", "4542");
        refundOrderParams.put("afterSales_info[0][img]", " https://lchapp.oss-cn-beijing.aliyuncs.com/2019010596473181025.jpg");
        refundOrderParams.put("afterSales_info[0][aftersale_num]", "8");
        refundOrderParams.put("afterSales_info[0][aftersale_money]", "14572.80");
        refundOrderParams.put("afterSales_info[0][goods_type]", "1");
        refundOrderParams.put("is_confirm", "1");

        String afterSaleId = orderService.createRefundOrder(customer, refundOrderParams);
        orderService.afterSaleReview(customer, 1, afterSaleId);
    }
}
