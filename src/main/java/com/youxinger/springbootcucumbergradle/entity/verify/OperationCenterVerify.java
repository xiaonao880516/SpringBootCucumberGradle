package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.OperationCenter;
import com.youxinger.springbootcucumbergradle.entity.verifydata.OperationCenterVerifyData;
import com.youxinger.springbootcucumbergradle.service.OperationCenterService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengwei
 * 2020/6/2 17:18
 * @version 1.0
 */
public class OperationCenterVerify extends AbstractVerify<OperationCenter, OperationCenterVerifyData> {

    private static final Logger logger = LoggerFactory.getLogger(OperationCenterVerify.class);

    private OperationCenterService operationCenterService = CustomManageObjUtil.getBean(OperationCenterService.class);

    public OperationCenterVerify(String name){
        verifyName = "operation center:"+ name;
    }

    @Override
    protected void verifyDataSelf() {
        logger.debug("{} verifyDataSelf", verifyName);
        Assert.assertEquals("验证运营中心业绩失败", expectedData.getSalesSum(), postVerifyData.getSalesSum() - preVerifyData.getSalesSum(), 2.0);
    }

    @Override
    public void updatePreVerifyDataSelf(OperationCenter entity) {
        preVerifyData = operationCenterService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyDataSelf(OperationCenter entity) {
        postVerifyData = operationCenterService.getVerifyData(entity);
    }
}
