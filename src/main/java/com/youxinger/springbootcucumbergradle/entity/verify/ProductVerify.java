package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Repository;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.service.RepositoryService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;

/**
 * @author mengwei
 * 2020/6/2 17:25
 * @version 1.0
 */
public class ProductVerify extends AbstractVerify<Repository, ProductVerifyData>{

    private RepositoryService repositoryService = CustomManageObjUtil.getBean(RepositoryService.class);

    @Override
    protected void verifyDataSelf(ProductVerifyData expectedData) {

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
