package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.entity.verifydata.CustomerVerifyData;
import com.youxinger.springbootcucumbergradle.service.CustomerService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengwei
 * 2020/5/29 15:34
 * @version 1.0
 */
public class CustomerVerify extends AbstractVerify<Customer, CustomerVerifyData> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerVerify.class);

    private CustomerService customerService = CustomManageObjUtil.getBean(CustomerService.class);

    public CustomerVerify(String name) {
        verifyName = "customer:"+ name;
    }

    @Override
    public void updatePreVerifyDataSelf(Customer customer) {
        preVerifyData = customerService.getVerifyData(customer);
    }

    @Override
    public void updatePostVerifyDataSelf(Customer customer) {
        postVerifyData = customerService.getVerifyData(customer);
    }

    @Override
    protected void verifyDataSelf() {
        logger.debug("{} verifyDataSelf", verifyName);
        Assert.assertEquals("验证会员余额失败", expectedData.getBalance(), postVerifyData.getBalance() - preVerifyData.getBalance(), 2.0);
        Assert.assertEquals("验证会员消费额失败", expectedData.getConsumption(), postVerifyData.getConsumption() - preVerifyData.getConsumption(), 2.0);
        Assert.assertEquals("验证会员积分失败", expectedData.getSwapScore(), postVerifyData.getSwapScore() - preVerifyData.getSwapScore(), 2.0);
        Assert.assertEquals("验证会员卡级别失败", expectedData.getCardLevel(), postVerifyData.getCardLevel());
    }

}
