package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Repository;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.RepositoryVerifyData;
import com.youxinger.springbootcucumbergradle.service.RepositoryService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;

/**
 * @author mengwei
 * 2020/6/3 9:23
 * @version 1.0
 */
public class RepositoryVerify extends AbstractVerify<Repository, RepositoryVerifyData> {

    private RepositoryService repositoryService = CustomManageObjUtil.getBean(RepositoryService.class);

    @Override
    protected void verifyDataSelf() {
        if (expectedData != null && expectedData.getProductVerifyDataMap() != null) {
            for (String barcode : expectedData.getProductVerifyDataMap().keySet()) {
                ProductVerifyData expected = expectedData.getProductVerifyDataMap().get(barcode);
                ProductVerifyData pre = preVerifyData.getProductVerifyDataMap().get(barcode);
                ProductVerifyData post = postVerifyData.getProductVerifyDataMap().get(barcode);
                Assert.assertEquals("验证库存失败", expected.getQuantity(), post.getQuantity() - pre.getQuantity(), 0);
            }
        }
    }

    @Override
    public void updatePreVerifyData(Repository entity) {
        preVerifyData = repositoryService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyData(Repository entity) {
        postVerifyData = repositoryService.getVerifyData(entity);
    }
}