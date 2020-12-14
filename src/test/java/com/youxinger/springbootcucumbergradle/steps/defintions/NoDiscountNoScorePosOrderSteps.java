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

public class NoDiscountNoScorePosOrderSteps extends BaseSteps {

    private static final Logger logger = LoggerFactory.getLogger(NoDiscountNoScorePosOrderSteps.class);

    @Resource
    private OrderService orderService;

    @Resource
    private PayService payService;

    @Resource
    private LogisticsService logisticsService;

    @当("^客户下单买入商品M316J247B0176 总仓2个，pos支付方式$")
    public void customerBuy() throws Throwable {
        cachePreVerifyData();
        for (Customer customer : dataManager.getCustomerMap().values()) {
            Map<String, String> orderParams = new HashMap<>();
            orderParams.put("price", "832000");
            orderParams.put("discount_money", "0");
            orderParams.put("real_pay", "832000");
            orderParams.put("referral_phone", "");
            orderParams.put("is_discount", "1");
            orderParams.put("beizhu", "");
            orderParams.put("member_phone", customer.getPhone());
            orderParams.put("pay_type", "pos");
            orderParams.put("coupon_id", "");
            orderParams.put("coupon_discount_amount", "0");
            orderParams.put("coupon_discount_rate", "");
            orderParams.put("address_id", customer.getCustomerAddress().getId());
            orderParams.put("goods_list[0][danjia]", "416000");
            orderParams.put("goods_list[0][sku_num]", "2");
            orderParams.put("goods_list[0][price]", "832000");
            orderParams.put("goods_list[0][real_pay_price]", "832000");
            orderParams.put("goods_list[0][discount_price]", "0");
            orderParams.put("goods_list[0][tiaoma]", "M316J247B0176");
            orderParams.put("goods_list[0][repo_out_num]", "0");
            orderParams.put("goods_list[0][gif_out_num]", "0");
            orderParams.put("goods_list[0][com_out_num]", "2");
            orderParams.put("goods_list[0][type]", "1");
            orderParams.put("goods_list[0][zh_num]", "");
            orderParams.put("goods_list[0][zh_repo_out_num]", "");
            orderParams.put("goods_list[0][zh_gif_out_num]", "");
            orderParams.put("goods_list[0][zh_com_out_num]", "");
            orderParams.put("goods_list[0][zh_tiaoma]", "");

            OrderInfo orderInfo = orderService.posOrder(customer, orderParams);
            customer.setOrderInfo(orderInfo);
            payService.posOrderPay(orderInfo.getOrderId(), orderInfo.getRealPay());
            logisticsService.orderDeliver(orderInfo);
        }
        cachePostVerifyData();
    }

    @当("^客户更换M316J247B0176商品为M316J247B0182，1件$")
    public void customerChange() throws Throwable {
        cachePreVerifyData();
        for (Customer customer : dataManager.getCustomerMap().values()) {
            Map<String, String> orderParams = new HashMap<>();
            String changeOrderId = customer.getOrderInfo().getOrderId() + "_0";
            orderParams.put("sub_order_id", changeOrderId);
            orderParams.put("goods_num", "1");
            orderParams.put("goods_total_price", "4160.00");
            orderParams.put("reason", "尺码不合适");
            orderParams.put("remarks", "");
            orderParams.put("type", "2");
            orderParams.put("goods_type", "1");
            orderParams.put("goods_list", "[{\"num\":1,\"sku_id\":\"5004\",\"sku_name\":\"男士长塑裤\",\"sku_detail\":\"黑色 82\",\"tiaoma\":\"M316J247B0182\",\"kuanhao\":\"M316J247\",\"danjia\":\"4160.00\",\"img\":\"https://lchapp.oss-cn-beijing.aliyuncs.com/2019010546738521091.jpg\"}]");
            orderParams.put("sub_goods_list", "");


            String afterSaleId = orderService.createChangeOrder(customer, orderParams);
            orderService.afterSaleReview(customer, 2, afterSaleId);
        }
        cachePostVerifyData();
    }

    @当("^客户退货M316J247B0182商品1件$")
    public void customerRefund() throws Throwable {
        cachePreVerifyData();
        for (Customer customer : dataManager.getCustomerMap().values()) {
            //如果存在换货订单时 重新获取订单号
            String changeOrderId = orderService.getAfterSaleOrderIdByKeywords(customer, customer.getOrderInfo().getOrderId());

            String refundOrderId = changeOrderId + "_0";
            Map<String, String> refundOrderParams = new HashMap<>();
            refundOrderParams.put("main_order_id", changeOrderId);
            refundOrderParams.put("return_price", "0.00");
            refundOrderParams.put("reason", "15天无理由退货");
            refundOrderParams.put("remarks", "");
            refundOrderParams.put("afterSales_info[0][order_id]", refundOrderId);
            refundOrderParams.put("afterSales_info[0][danjia]", "4160.00");
            refundOrderParams.put("afterSales_info[0][sku_name]", "男士长塑裤");
            refundOrderParams.put("afterSales_info[0][sku_detail]", "黑色 82");
            refundOrderParams.put("afterSales_info[0][tiaoma]", "M316J247B0182");
            refundOrderParams.put("afterSales_info[0][kuanhao]", "M316J247");
            refundOrderParams.put("afterSales_info[0][sku_id]", "5004");
            refundOrderParams.put("afterSales_info[0][img]", "https://lchapp.oss-cn-beijing.aliyuncs.com/2019010546738521091.jpg");
            refundOrderParams.put("afterSales_info[0][aftersale_num]", "1");
            refundOrderParams.put("afterSales_info[0][aftersale_money]", "0.00");
            refundOrderParams.put("afterSales_info[0][goods_type]", "1");
            refundOrderParams.put("is_confirm", "1");


            logger.debug("createRefundOrderParam, param={}", refundOrderParams);
            String afterSaleId = orderService.createRefundOrder(customer, refundOrderParams);
            orderService.afterSaleReview(customer, 1, afterSaleId);
        }
        cachePostVerifyData();
    }

    @当("^客户退货M316J247B0176商品，1件$")
    public void customerRefundTwo() throws Throwable {
        cachePreVerifyData();
        for (Customer customer : dataManager.getCustomerMap().values()) {

            String refundOrderId = customer.getOrderInfo().getOrderId() + "_0";
            Map<String, String> refundOrderParams = new HashMap<>();
            refundOrderParams.put("main_order_id", customer.getOrderInfo().getOrderId());
            refundOrderParams.put("return_price", "4160.00");
            refundOrderParams.put("reason", "15天无理由退货");
            refundOrderParams.put("remarks", "郑晓龙测试退款");
            refundOrderParams.put("afterSales_info[0][order_id]", refundOrderId);
            refundOrderParams.put("afterSales_info[0][danjia]", "4160.00");
            refundOrderParams.put("afterSales_info[0][sku_name]", "男士长塑裤");
            refundOrderParams.put("afterSales_info[0][sku_detail]", "黑色 76");
            refundOrderParams.put("afterSales_info[0][tiaoma]", "M316J247B0176");
            refundOrderParams.put("afterSales_info[0][kuanhao]", "M316J247");
            refundOrderParams.put("afterSales_info[0][sku_id]", "5003");
            refundOrderParams.put("afterSales_info[0][img]", "https://lchapp.oss-cn-beijing.aliyuncs.com/2019010546738521091.jpg");
            refundOrderParams.put("afterSales_info[0][aftersale_num]", "1");
            refundOrderParams.put("afterSales_info[0][aftersale_money]", "4160.00");
            refundOrderParams.put("afterSales_info[0][goods_type]", "1");
            refundOrderParams.put("is_confirm", "1");

            logger.debug("createRefundOrderParamTwo, param={}", refundOrderParams);
            String afterSaleId = orderService.createRefundOrder(customer, refundOrderParams);
            orderService.afterSaleReview(customer, 1, afterSaleId);
        }
        cachePostVerifyData();
    }
}
