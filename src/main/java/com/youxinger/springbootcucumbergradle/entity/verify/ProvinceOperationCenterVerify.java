package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.OperationCenter;
import com.youxinger.springbootcucumbergradle.entity.ProvinceOperationCenter;
import com.youxinger.springbootcucumbergradle.entity.verifydata.OperationCenterVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProvinceOperationCenterVerifyData;
import com.youxinger.springbootcucumbergradle.service.OperationCenterService;
import com.youxinger.springbootcucumbergradle.service.ProvinceOperationCenterService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;

/**
 * @author mengwei
 * 2020/6/2 17:28
 * @version 1.0
 */
public class ProvinceOperationCenterVerify extends AbstractVerify<ProvinceOperationCenter, ProvinceOperationCenterVerifyData> {

    private ProvinceOperationCenterService provinceOperationCenterService = CustomManageObjUtil.getBean(ProvinceOperationCenterService.class);

    @Override
    protected void verifyDataSelf(ProvinceOperationCenterVerifyData expectedData) {
        Assert.assertEquals("验证省运营中心业绩失败", expectedData.getSalesSum(), postVerifyData.getSalesSum() - preVerifyData.getSalesSum(), 2.0);
    }

    @Override
    public void updatePreVerifyData(ProvinceOperationCenter entity) {
        preVerifyData = provinceOperationCenterService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyData(ProvinceOperationCenter entity) {
        postVerifyData = provinceOperationCenterService.getVerifyData(entity);
    }
}
