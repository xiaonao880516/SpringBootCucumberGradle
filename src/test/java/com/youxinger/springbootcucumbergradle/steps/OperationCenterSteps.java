package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.entity.verifydata.OperationCenterVerifyData;
import cucumber.api.java.zh_cn.那么;

/**
 * @author mengwei
 * 2020/6/4 11:02
 * @version 1.0
 */
public class OperationCenterSteps extends BaseSteps{

    @那么("^预期运营中心销售额增加(\\d+)元")
    public void operationCenterVerifyStep(int salesSum) throws Throwable {
        OperationCenterVerifyData operationCenterVerifyData= new OperationCenterVerifyData();
        operationCenterVerifyData.setSalesSum(salesSum);
        dataManager.getGlobal().getProvinceOperationCenterList().get(0).getOperationCenterList().get(0).setExpectedData(operationCenterVerifyData);
    }
}
