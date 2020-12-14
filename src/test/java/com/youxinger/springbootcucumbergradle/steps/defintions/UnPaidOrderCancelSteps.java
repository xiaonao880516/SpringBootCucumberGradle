package com.youxinger.springbootcucumbergradle.steps.defintions;

import com.youxinger.springbootcucumbergradle.bean.OrderInfo;
import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.service.OrderService;
import com.youxinger.springbootcucumbergradle.steps.BaseSteps;
import cucumber.api.java.zh_cn.当;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jwtan
 * @date : 2020/6/30
 */
public class UnPaidOrderCancelSteps extends BaseSteps {
    @Resource
    private OrderService orderService;

    private OrderInfo orderInfo;

    @当("^客户pos下单门店发货组合商品惠享金秋$")
    public void newRechargeOrder() throws Throwable {
        cachePreVerifyData();
        for (Customer customer : dataManager.getCustomerMap().values()) {
            Map<String , String> orderParams = new HashMap<>();
            orderParams.put("price", "1042000");
            orderParams.put("discount_money", "41680");
            orderParams.put("real_pay", "1000320");
            orderParams.put("referral_phone", "");
            orderParams.put("is_discount", "1");
            orderParams.put("beizhu", "");
            orderParams.put("member_phone", customer.getPhone());
            orderParams.put("pay_type", "pos");
            orderParams.put("coupon_id", "");
            orderParams.put("coupon_discount_amount", "0");
            orderParams.put("coupon_discount_rate", "");
            orderParams.put("address_id", customer.getCustomerAddress().getId());

            orderParams.put("goods_list[0][danjia]", "264000");
            orderParams.put("goods_list[0][sku_num]", "1");
            orderParams.put("goods_list[0][price]", "264000");
            orderParams.put("goods_list[0][real_pay_price]", "253440");
            orderParams.put("goods_list[0][discount_price]", "10560");
            orderParams.put("goods_list[0][tiaoma]", "M216C239C0458");
            orderParams.put("goods_list[0][repo_out_num]", "0");
            orderParams.put("goods_list[0][gif_out_num]", "1");
            orderParams.put("goods_list[0][com_out_num]", "0");
            orderParams.put("goods_list[0][type]", "2");
            orderParams.put("goods_list[0][zh_num]", "1");
            orderParams.put("goods_list[0][zh_repo_out_num]", "0");
            orderParams.put("goods_list[0][zh_gif_out_num]", "1");
            orderParams.put("goods_list[0][zh_com_out_num]", "0");
            orderParams.put("goods_list[0][zh_tiaoma]", "ZH02B134136T619892");
            orderParams.put("goods_list[0][zh_mark]", "1");
            orderParams.put("goods_list[0][position]", "1");
            orderParams.put("goods_list[0][sub_kuanhao_id]", "styleNun3767");


            orderParams.put("goods_list[1][danjia]", "257000");
            orderParams.put("goods_list[1][sku_num]", "1");
            orderParams.put("goods_list[1][price]", "257000");
            orderParams.put("goods_list[1][real_pay_price]", "246720");
            orderParams.put("goods_list[1][discount_price]", "10280");
            orderParams.put("goods_list[1][tiaoma]", "M216D243C0458");
            orderParams.put("goods_list[1][repo_out_num]", "0");
            orderParams.put("goods_list[1][gif_out_num]", "1");
            orderParams.put("goods_list[1][com_out_num]", "0");
            orderParams.put("goods_list[1][type]", "2");
            orderParams.put("goods_list[1][zh_num]", "1");
            orderParams.put("goods_list[1][zh_repo_out_num]", "0");
            orderParams.put("goods_list[1][zh_gif_out_num]", "1");
            orderParams.put("goods_list[1][zh_com_out_num]", "0");
            orderParams.put("goods_list[1][zh_tiaoma]", "ZH02B134136T619892");
            orderParams.put("goods_list[1][zh_mark]", "1");
            orderParams.put("goods_list[1][position]", "2");
            orderParams.put("goods_list[1][sub_kuanhao_id]", "styleNun6001");


            orderParams.put("goods_list[2][danjia]", "264000");
            orderParams.put("goods_list[2][sku_num]", "1");
            orderParams.put("goods_list[2][price]", "264000");
            orderParams.put("goods_list[2][real_pay_price]", "253440");
            orderParams.put("goods_list[2][discount_price]", "10560");
            orderParams.put("goods_list[2][tiaoma]", "M216C239C0458");
            orderParams.put("goods_list[2][repo_out_num]", "0");
            orderParams.put("goods_list[2][gif_out_num]", "1");
            orderParams.put("goods_list[2][com_out_num]", "0");
            orderParams.put("goods_list[2][type]", "2");
            orderParams.put("goods_list[2][zh_num]", "1");
            orderParams.put("goods_list[2][zh_repo_out_num]", "0");
            orderParams.put("goods_list[2][zh_gif_out_num]", "1");
            orderParams.put("goods_list[2][zh_com_out_num]", "0");
            orderParams.put("goods_list[2][zh_tiaoma]", "ZH02B134136T619892");
            orderParams.put("goods_list[2][zh_mark]", "2");
            orderParams.put("goods_list[2][position]", "1");
            orderParams.put("goods_list[2][sub_kuanhao_id]", "styleNun3767");


            orderParams.put("goods_list[3][danjia]", "257000");
            orderParams.put("goods_list[3][sku_num]", "1");
            orderParams.put("goods_list[3][price]", "257000");
            orderParams.put("goods_list[3][real_pay_price]", "246720");
            orderParams.put("goods_list[3][discount_price]", "10280");
            orderParams.put("goods_list[3][tiaoma]", "M216D243C0458");
            orderParams.put("goods_list[3][repo_out_num]", "0");
            orderParams.put("goods_list[3][gif_out_num]", "1");
            orderParams.put("goods_list[3][com_out_num]", "0");
            orderParams.put("goods_list[3][type]", "2");
            orderParams.put("goods_list[3][zh_num]", "1");
            orderParams.put("goods_list[3][zh_repo_out_num]", "0");
            orderParams.put("goods_list[3][zh_gif_out_num]", "1");
            orderParams.put("goods_list[3][zh_com_out_num]", "0");
            orderParams.put("goods_list[3][zh_tiaoma]", "ZH02B134136T619892");
            orderParams.put("goods_list[3][zh_mark]", "2");
            orderParams.put("goods_list[3][position]", "2");
            orderParams.put("goods_list[3][sub_kuanhao_id]", "styleNun6001");


            orderInfo = orderService.posOrder(customer, orderParams);
            customer.setOrderInfo(orderInfo);
        }
        cachePostVerifyData();
    }

}
