package com.youxinger.springbootcucumbergradle.steps;


import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.entity.Platform;
import com.youxinger.springbootcucumbergradle.entity.verifydata.CustomerVerifyData;
import com.youxinger.springbootcucumbergradle.service.CustomerService;
import com.youxinger.springbootcucumbergradle.utils.CardUtil;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import com.youxinger.springbootcucumbergradle.utils.RandomValue;
import cucumber.api.java.zh_cn.假设;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author mengwei
 * 2020/5/22 13:35
 * @version 1.0
 */
public class CustomerSteps extends BaseSteps {
    private static final Logger logger = LoggerFactory.getLogger(CustomerSteps.class);

    @Resource
    private CustomerService customerService;


    @假设("随机创建姓名为([^\"]+)的客户")
    public void customerCreateByName(String name) {
        logger.debug("customerCreate, name={}", name);
        Customer customer = new Customer(name, RandomValue.getTel());

        Employee employee = dataManager.getEmployeeById(Constants.EMPLOYEE_ID);
        Platform platform = dataManager.getPlatformById(Constants.PLATFORM_ID);
        customerService.customerRegister(customer, employee, platform);
        dataManager.getCustomerMap().put(name, customer);
        customer.updatePreVerifyData();
    }

    @当("^客户充值(\\d+)元$")
    public void customerRecharge(int money) throws Throwable {
        logger.debug("customerCreate, money={}", money);
        for (String customerName : dataManager.getCustomerMap().keySet()) {
            customerRechargeByCustomerName(customerName, money);
        }
    }

    @当("^客户([^\"]+)充值(\\d+)元$")
    public void customerRechargeByCustomerName(String customerName, int money) throws Throwable {
        Customer customer = dataManager.getCustomerByName(customerName);
        Employee employee = dataManager.getEmployeeById(Constants.EMPLOYEE_ID);
        customerService.customerRecharge(money, customer, employee);
        customer.updatePostVerifyData();
    }

    @那么("^预期客户的卡级别为([^\"]+),积分增加(\\d+),消费额增加(\\d+)元,余额增加(\\d+)元")
    public void customerDataVerify(String cardLevel, int swapScore, int consumption, int balance) throws Throwable {
        for (String customerName : dataManager.getCustomerMap().keySet()) {
            customerDataVerifyByCustomerName(customerName, cardLevel, swapScore, consumption, balance);
        }
    }

    @那么("^预期客户([^\"]+)的卡级别为([^\"]+),积分增加(\\d+),消费额增加(\\d+)元,余额增加(\\d+)元")
    public void customerDataVerifyByCustomerName(String customerName, String cardLevel, int swapScore, int consumption, int balance) throws Throwable {
        CustomerVerifyData expectedData = new CustomerVerifyData(consumption, swapScore, CardUtil.getCardLevel(cardLevel), balance);
        Customer customer = dataManager.getCustomerByName(customerName);
        customer.setExpectedData(expectedData);
    }

    @当("^客户下单买入商品([^\"]+)， 总仓(\\d+)个，门店(\\d+)个，([^\"]*)支付方式$")
    public void customerBuy(String barcode, int quantity, int storeQuantity, String payType) throws Throwable {
        logger.debug("buy, barcode={}, quantity={}, storeQuantity={}, payType={}", barcode, quantity, storeQuantity, payType);
        for (String customerName : dataManager.getCustomerMap().keySet()) {
            customerBuy(customerName, barcode, quantity, storeQuantity, payType);
        }
        //TODO
    }

    @当("^客户([^\"]+)下单买入商品([^\"]+)， 总仓(\\d+)个，门店(\\d+)个，([^\"]*)支付方式$")
    public void customerBuy(String customerName, String barcode, int quantity, int storeQuantity, String payType) throws Throwable {
        logger.debug("buy, barcode={}, quantity={}, storeQuantity={}, payType={}", barcode, quantity, storeQuantity, payType);
        Customer customer = dataManager.getCustomerByName(customerName);
        //TODO 下单具体实现
        customer.updatePostVerifyData();
        dataManager.getGlobal().updatePostVerifyData();

    }
}
