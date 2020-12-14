package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Platform;
import com.youxinger.springbootcucumbergradle.entity.verifydata.PlatformVerifyData;
import com.youxinger.springbootcucumbergradle.service.PlatformService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengwei
 * 2020/6/2 17:22
 * @version 1.0
 */
public class PlatformVerify extends AbstractVerify<Platform, PlatformVerifyData> {

    private static final Logger logger = LoggerFactory.getLogger(PlatformVerify.class);

    private PlatformService platformService = CustomManageObjUtil.getBean(PlatformService.class);

    public PlatformVerify(String name){
        verifyName = "platform:"+ name;
    }

    @Override
    protected void verifyDataSelf() {
        logger.debug("{} verifyDataSelf", verifyName);
        Assert.assertEquals("验证平台业绩失败", expectedData.getPerformance(), postVerifyData.getPerformance() - preVerifyData.getPerformance(), 2.0);
    }

    @Override
    public void updatePreVerifyDataSelf(Platform entity) {
        preVerifyData = platformService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyDataSelf(Platform entity) {
        postVerifyData = platformService.getVerifyData(entity);
    }
}
