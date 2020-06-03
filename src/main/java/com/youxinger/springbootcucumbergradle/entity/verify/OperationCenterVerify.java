package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.OperationCenter;
import com.youxinger.springbootcucumbergradle.entity.verifydata.OperationCenterVerifyData;
import com.youxinger.springbootcucumbergradle.service.OperationCenterService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;

/**
 * @author mengwei
 * 2020/6/2 17:18
 * @version 1.0
 */
public class OperationCenterVerify extends AbstractVerify<OperationCenter, OperationCenterVerifyData> {

    private OperationCenterService operationCenterService = CustomManageObjUtil.getBean(OperationCenterService.class);

    @Override
    protected void verifyDataSelf() {
        Assert.assertEquals("验证运营中心业绩失败", expectedData.getSalesSum(), postVerifyData.getSalesSum() - preVerifyData.getSalesSum(), 2.0);
    }

    @Override
    public void updatePreVerifyData(OperationCenter entity) {
        preVerifyData = operationCenterService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyData(OperationCenter entity) {
        postVerifyData = operationCenterService.getVerifyData(entity);
    }
}
