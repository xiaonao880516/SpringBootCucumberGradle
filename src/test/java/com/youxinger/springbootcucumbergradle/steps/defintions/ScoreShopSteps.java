package com.youxinger.springbootcucumbergradle.steps.defintions;

import com.youxinger.springbootcucumbergradle.bean.OrderInfo;
import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.service.OrderService;
import com.youxinger.springbootcucumbergradle.steps.BaseSteps;
import cucumber.api.java.zh_cn.当;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class ScoreShopSteps extends BaseSteps {

    @Resource
    private OrderService orderService;

    @当("^客户积分商城换购商品M619E355A0370,2个,纯积分方式$")
    public void createPointMallOrder() throws Throwable {
        cachePreVerifyData();
        for (Customer customer : dataManager.getCustomerMap().values()) {
            Map<String, String> orderParams = new HashMap<>();
            orderParams.put("exchange_type", "score");
            orderParams.put("total_score", "22400");
            orderParams.put("total_mix_score", "0");
            orderParams.put("total_mix_price", "0");
            orderParams.put("receive_name", customer.getCustomerAddress().getConsignee());
            orderParams.put("receive_phone", customer.getCustomerAddress().getConsignee_phone());
            orderParams.put("receive_sheng", customer.getCustomerAddress().getProvince());
            orderParams.put("receive_shi", customer.getCustomerAddress().getCity());
            orderParams.put("receive_diqu", customer.getCustomerAddress().getArea());
            orderParams.put("receive_address", customer.getCustomerAddress().getAddress());
            orderParams.put("beizhu", "");
            orderParams.put("member_id", customer.getMember_number());
            orderParams.put("member_name", customer.getName());
            orderParams.put("member_phone", customer.getPhone());
            orderParams.put("plateform_id", String.valueOf(customer.getPlatform().getId()));
            orderParams.put("special_employee_id", String.valueOf(customer.getEmployee().getId()));
            orderParams.put("pay_type", "");
            orderParams.put("goods_info[sku_num]", "2");
            orderParams.put("goods_info[sku_name]", "无痕高腰包臀内裤");
            orderParams.put("goods_info[sku_id]", "503");
            orderParams.put("goods_info[tiaoma]", "M619E355A0370");
            orderParams.put("goods_info[kuanhao]", "M619E355");
            orderParams.put("goods_info[sku_detail]", "浅金 70");
            orderParams.put("goods_info[img]", "https://lchapp.oss-cn-beijing.aliyuncs.com/2019120617456810293.jpg");
            orderParams.put("goods_info[type]", "1");
            orderParams.put("goods_info[sub_goods]", "");


            OrderInfo orderInfo = orderService.pointMallOrder(customer, orderParams);
            customer.setOrderInfo(orderInfo);
            orderService.pointMallOrderPay(orderInfo);
            cachePostVerifyData();
        }
    }

    @当("^客户积分商城换购商品M619E355A0370后台退货$")
    public void customerRefund2() throws Throwable {
        cachePreVerifyData();
        for (String customerName : dataManager.getCustomerMap().keySet()) {
            Customer customer = dataManager.getCustomerByName(customerName);
            orderService.pointMallBackGroundRefundOrder(customer, customer.getOrderInfo().getOrderId());
        }
        cachePostVerifyData();
    }


}
