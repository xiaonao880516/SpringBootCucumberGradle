package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.entity.verifydata.ProvinceOperationCenterVerifyData;
import cucumber.api.java.zh_cn.那么;

/**
 * @author mengwei
 * 2020/6/4 11:02
 * @version 1.0
 */
public class ProvinceOperationCenterSteps extends BaseSteps{

    @那么("^预期省运营中心销售额增加(0|[1-9][0-9]*|-[1-9][0-9]*)元")
    public void provinceOperationCenterVerifyStep(int salesSum) throws Throwable {
        ProvinceOperationCenterVerifyData provinceOperationCenterVerifyData= new ProvinceOperationCenterVerifyData();
        provinceOperationCenterVerifyData.setSalesSum(salesSum);
        dataManager.getGlobal().getProvinceOperationCenterList().get(0).setExpectedData(provinceOperationCenterVerifyData);
    }
}
