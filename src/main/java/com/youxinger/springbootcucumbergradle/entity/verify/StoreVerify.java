package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Store;
import com.youxinger.springbootcucumbergradle.entity.verifydata.StoreVerifyData;
import com.youxinger.springbootcucumbergradle.service.StoreService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengwei
 * 2020/6/2 17:30
 * @version 1.0
 */
public class StoreVerify extends AbstractVerify<Store, StoreVerifyData> {

    private static final Logger logger = LoggerFactory.getLogger(StoreVerify.class);

    private StoreService storeService = CustomManageObjUtil.getBean(StoreService.class);

    public StoreVerify(String name){
        verifyName = "store:"+ name;
    }

    @Override
    protected void verifyDataSelf() {
        logger.debug("{} verifyDataSelf", verifyName);
        Assert.assertEquals("验证门店业绩失败", expectedData.getSalesSum(), postVerifyData.getSalesSum() - preVerifyData.getSalesSum(), 2.0);
    }

    @Override
    public void updatePreVerifyData(Store entity) {
        preVerifyData = storeService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyData(Store entity) {
        postVerifyData = storeService.getVerifyData(entity);
    }
}
