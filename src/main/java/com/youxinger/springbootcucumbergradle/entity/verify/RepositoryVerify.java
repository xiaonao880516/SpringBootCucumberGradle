package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Repository;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.RepositoryVerifyData;
import com.youxinger.springbootcucumbergradle.service.RepositoryService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengwei
 * 2020/6/3 9:23
 * @version 1.0
 */
public class RepositoryVerify extends AbstractVerify<Repository, RepositoryVerifyData> {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryVerify.class);

    private RepositoryService repositoryService = CustomManageObjUtil.getBean(RepositoryService.class);

    public RepositoryVerify(String name){
        verifyName = "repository:"+ name;
    }

    @Override
    protected void verifyDataSelf() {
        logger.info("{} verifyDataSelf", verifyName);
        if (expectedData != null && expectedData.getProductVerifyDataMap() != null) {
            for (String barcode : expectedData.getProductVerifyDataMap().keySet()) {
                ProductVerifyData expected = expectedData.getProductVerifyDataMap().get(barcode);
                int preQuantity = 0;
                ProductVerifyData pre = preVerifyData.getProductVerifyDataMap().get(barcode);
                if(pre != null){
                    preQuantity = pre.getQuantity();
                }
                int postQuantity = 0;
                ProductVerifyData post = postVerifyData.getProductVerifyDataMap().get(barcode);
                if(post != null){
                    postQuantity = post.getQuantity();
                }
                logger.info("postQuantity={}, preQuantity={}", postQuantity, preQuantity);
                Assert.assertEquals("repository:"+ verifyName+"， 验证库存失败", expected.getQuantity(), postQuantity - preQuantity, 0);
            }
        }
    }

    @Override
    public void updatePreVerifyDataSelf(Repository entity) {
        preVerifyData = repositoryService.getVerifyData(entity);
    }

    @Override
    public void updatePostVerifyDataSelf(Repository entity) {
        postVerifyData = repositoryService.getVerifyData(entity);
    }
}