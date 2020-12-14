package com.youxinger.springbootcucumbergradle.steps;


import com.youxinger.springbootcucumbergradle.entity.Customer;
import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.entity.Platform;
import com.youxinger.springbootcucumbergradle.entity.verifydata.CustomerVerifyData;
import com.youxinger.springbootcucumbergradle.service.CustomerService;
import com.youxinger.springbootcucumbergradle.service.OrderService;
import com.youxinger.springbootcucumbergradle.service.PayService;
import com.youxinger.springbootcucumbergradle.utils.CardUtil;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import com.youxinger.springbootcucumbergradle.utils.RandomValue;
import cucumber.api.java.zh_cn.假设;
import cucumber.api.java.zh_cn.并且;
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
    private OrderService orderService;

    @Resource
    private PayService payService;

    @Resource
    private CustomerService customerService;

    @假设("随机创建姓名为([^\"]+)的客户")
    public void customerCreateByName(String name) {
        logger.debug("customerCreate, name={}", name);
        Customer customer = new Customer(name, RandomValue.getTel());

        Employee employee = dataManager.getEmployeeById(Constants.EMPLOYEE_ID);
        Platform platform = dataManager.getPlatformById(Constants.PLATFORM_ID);
        customer.setEmployee(employee);
        customer.setPlatform(platform);
        customerService.customerRegister(customer);
        dataManager.putCustomer(customer);
    }

    @当("^客户充值(\\d+)元$")
    public void customerRecharge(int money) throws Throwable {
        logger.debug("customerCreate, money={}", money);
        for (Customer customer : dataManager.getCustomerMap().values()) {
            customerRecharge(customer, money);
        }
    }

    @当("^客户([^\"]+)充值(\\d+)元$")
    public void customerRechargeByCustomerName(String customerName, int money) throws Throwable {
        Customer customer = dataManager.getCustomerByName(customerName);
        customerRecharge(customer, money);
    }

    @当("^客户充值(\\d+)元并缓存数据$")
    public void customerRechargeAndCacheData(int money) throws Throwable {
        logger.debug("customerCreate, money={}", money);
        cachePreVerifyData();
        for (Customer customer : dataManager.getCustomerMap().values()) {
            customerRecharge(customer, money);
        }
        cachePostVerifyData();
    }

    @当("^客户([^\"]+)充值(\\d+)元并缓存数据$")
    public void customerRechargeByCustomerNameAndCacheData(String customerName, int money) throws Throwable {
        cachePreVerifyData();
        Customer customer = dataManager.getCustomerByName(customerName);
        customerRecharge(customer, money);
        cachePostVerifyData();
    }

    private void customerRecharge(Customer customer, int money){
        String rechargeOrderId = customerService.customerRecharge(money, customer);
        String amount = String.valueOf(money) + "00";
        payService.posOrderPay(rechargeOrderId, amount);
    }

    @那么("^预期客户的卡级别为([^\"]+),积分增加(0|[1-9][0-9]*|-[1-9][0-9]*),消费额增加(0|[1-9][0-9]*|-[1-9][0-9]*)元,余额增加(0|[1-9][0-9]*|-[1-9][0-9]*)元")
    public void customerDataVerify(String cardLevel, int swapScore, int consumption, int balance) throws Throwable {
        for (String customerName : dataManager.getCustomerMap().keySet()) {
            customerDataVerifyByCustomerName(customerName, cardLevel, swapScore, consumption, balance);
        }
    }

    @那么("^预期客户([^\"]+)的卡级别为([^\"]+),积分增加(0|[1-9][0-9]*|-[1-9][0-9]*),消费额增加(0|[1-9][0-9]*|-[1-9][0-9]*)元,余额增加(0|[1-9][0-9]*|-[1-9][0-9]*)元")
    public void customerDataVerifyByCustomerName(String customerName, String cardLevel, int swapScore, int consumption, int balance) throws Throwable {
        CustomerVerifyData expectedData = new CustomerVerifyData(consumption, swapScore, CardUtil.getCardLevel(cardLevel), balance);
        Customer customer = dataManager.getCustomerByName(customerName);
        customer.setExpectedData(expectedData);
    }

    @并且("^客户取消订单$")
    public void cancelRechargeOrder() {
        for (Customer customer : dataManager.getCustomerMap().values()) {
            if (customer.getOrderInfo() != null) {
                orderService.cancelOrder(customer, customer.getOrderInfo().getOrderId());
            }
        }
    }

    @并且("^客户取消订单并缓存数据$")
    public void cancelRechargeOrderAndCacheData() throws Throwable {
        cachePreVerifyData();
        for (Customer customer : dataManager.getCustomerMap().values()) {
            if (customer.getOrderInfo() != null) {
                orderService.cancelOrder(customer, customer.getOrderInfo().getOrderId());
            }
        }
        cachePostVerifyData();
    }
}
