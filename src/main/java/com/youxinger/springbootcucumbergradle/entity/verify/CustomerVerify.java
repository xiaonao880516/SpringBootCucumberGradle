package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.service.CustomerService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;

/**
 * @author mengwei
 * 2020/5/29 15:34
 * @version 1.0
 */
public class CustomerVerify extends AbstractVerify<Customer, CustomerVerifyData> {

    private CustomerService customerService = CustomManageObjUtil.getBean(CustomerService.class);

    public CustomerVerify() {
    }

    @Override
    public void updatePreVerifyData(Customer customer) {
        preVerifyData = customerService.getVerifyData(customer);
    }

    @Override
    public void updatePostVerifyData(Customer customer) {
        postVerifyData = customerService.getVerifyData(customer);
    }

    @Override
    protected void verifyDataSelf(CustomerVerifyData expectedData) {
        Assert.assertEquals("验证会员余额失败", expectedData.getBalance(), postVerifyData.getBalance() - preVerifyData.getBalance(), 2.0);
        Assert.assertEquals("验证会员消费额失败", expectedData.getConsumption(), postVerifyData.getConsumption() - preVerifyData.getConsumption(), 2.0);
        Assert.assertEquals("验证会员积分失败", expectedData.getSwapScore(), postVerifyData.getSwapScore() - preVerifyData.getSwapScore(), 2.0);
        Assert.assertEquals("验证会员卡级别失败", expectedData.getCardLevel(), postVerifyData.getCardLevel());
    }

}
