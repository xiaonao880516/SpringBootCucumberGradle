package com.youxinger.springbootcucumbergradle.steps.defintions;

import com.youxinger.springbootcucumbergradle.service.RechargeService;
import com.youxinger.springbootcucumbergradle.service.ScoreService;
import com.youxinger.springbootcucumbergradle.steps.BaseSteps;
import cucumber.api.java.zh_cn.并且;

import javax.annotation.Resource;

/**
 * @author : jwtan
 * @date : 2020/7/3
 */
public class CustomerRechargeSteps extends BaseSteps {

//    private static final Logger logger = LoggerFactory.getLogger(CustomerSteps.class);

    @Resource
    private RechargeService rechargeService;

    @Resource
    private ScoreService scoreService;


    @并且("^客户([^\"]+)余额转出(\\d+)元给([^\"]+)$")
    public void rollOutRemainder(String customerName, int amount, String toCustomerName) throws Throwable {
        cachePreVerifyData();
        String customerMemberNumber = dataManager.getCustomerByName(customerName).getMember_number();
        String toCustomerPhone = dataManager.getCustomerByName(toCustomerName).getPhone();
        rechargeService.remainderRollOut(customerMemberNumber, toCustomerPhone, amount);
    }

    @并且("^客户([^\"]+)余额提现(\\d+)元并扣减积分(\\d+)分$")
    public void withdrawalRemainder(String customerName, int amount, int score) {
        String customerMemberNumber = dataManager.getCustomerByName(customerName).getMember_number();
        rechargeService.withdrawal(customerMemberNumber, amount, score);
    }

    @并且("^客户([^\"]+)积分调整(0|[1-9][0-9]*|-[1-9][0-9]*)$")
    public void changeScore(String customerName, int score) throws Throwable {
        int direction = score >= 0 ? 1 : 2;
        String customerMemberNumber = dataManager.getCustomerByName(customerName).getMember_number();
        scoreService.changeScore(customerMemberNumber, direction, Math.abs(score));
        cachePostVerifyData();
    }

}
