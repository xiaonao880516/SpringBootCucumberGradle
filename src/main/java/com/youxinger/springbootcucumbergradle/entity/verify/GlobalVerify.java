package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Global;
import com.youxinger.springbootcucumbergradle.entity.verifydata.GlobalVerifyData;
import com.youxinger.springbootcucumbergradle.service.GlobalService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;

/**
 * @author mengwei
 * 2020/6/2 17:13
 * @version 1.0
 */
public class GlobalVerify extends AbstractVerify<Global, GlobalVerifyData>{

    private GlobalService globalService = CustomManageObjUtil.getBean(GlobalService.class);

    @Override
    protected void verifyDataSelf(GlobalVerifyData expectedData) {
        Assert.assertEquals("验证总览业绩失败", expectedData.getSalesSum(), postVerifyData.getSalesSum() - preVerifyData.getSalesSum(), 2.0);
    }

    @Override
    public void updatePreVerifyData(Global entity) {
        preVerifyData = globalService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyData(Global entity) {
        postVerifyData = globalService.getVerifyData(entity);
    }
}
