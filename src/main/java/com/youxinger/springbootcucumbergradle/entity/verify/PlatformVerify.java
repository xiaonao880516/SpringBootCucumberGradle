package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Platform;
import com.youxinger.springbootcucumbergradle.entity.verifydata.PlatformVerifyData;
import com.youxinger.springbootcucumbergradle.service.PlatformService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;

/**
 * @author mengwei
 * 2020/6/2 17:22
 * @version 1.0
 */
public class PlatformVerify extends AbstractVerify<Platform, PlatformVerifyData> {

    private PlatformService platformService = CustomManageObjUtil.getBean(PlatformService.class);

    @Override
    protected void verifyDataSelf(PlatformVerifyData expectedData) {
        Assert.assertEquals("验证平台业绩失败", expectedData.getPerformance(), postVerifyData.getPerformance() - preVerifyData.getPerformance(), 2.0);
    }

    @Override
    public void updatePreVerifyData(Platform entity) {
        preVerifyData = platformService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyData(Platform entity) {
        postVerifyData = platformService.getVerifyData(entity);
    }
}
