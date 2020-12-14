package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.ProvinceOperationCenter;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProvinceOperationCenterVerifyData;
import com.youxinger.springbootcucumbergradle.service.ProvinceOperationCenterService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengwei
 * 2020/6/2 17:28
 * @version 1.0
 */
public class ProvinceOperationCenterVerify extends AbstractVerify<ProvinceOperationCenter, ProvinceOperationCenterVerifyData> {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceOperationCenterVerify.class);

    private ProvinceOperationCenterService provinceOperationCenterService = CustomManageObjUtil.getBean(ProvinceOperationCenterService.class);

    public ProvinceOperationCenterVerify(String name){
        verifyName = "province operation center:"+ name;
    }

    @Override
    protected void verifyDataSelf() {
        logger.debug("{} verifyDataSelf", verifyName);
        Assert.assertEquals("验证省运营中心业绩失败", expectedData.getSalesSum(), postVerifyData.getSalesSum() - preVerifyData.getSalesSum(), 2.0);
    }

    @Override
    public void updatePreVerifyDataSelf(ProvinceOperationCenter entity) {
        preVerifyData = provinceOperationCenterService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyDataSelf(ProvinceOperationCenter entity) {
        postVerifyData = provinceOperationCenterService.getVerifyData(entity);
    }
}
