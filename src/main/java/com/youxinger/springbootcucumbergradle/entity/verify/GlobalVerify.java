package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Global;
import com.youxinger.springbootcucumbergradle.entity.verifydata.GlobalVerifyData;
import com.youxinger.springbootcucumbergradle.service.GlobalService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengwei
 * 2020/6/2 17:13
 * @version 1.0
 */
public class GlobalVerify extends AbstractVerify<Global, GlobalVerifyData>{

    private static final Logger logger = LoggerFactory.getLogger(GlobalVerify.class);

    private GlobalService globalService = CustomManageObjUtil.getBean(GlobalService.class);

    public GlobalVerify(){
        verifyName = "global:兰超集团";
    }

    @Override
    protected void verifyDataSelf() {
        logger.debug("{} verifyDataSelf", verifyName);
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
