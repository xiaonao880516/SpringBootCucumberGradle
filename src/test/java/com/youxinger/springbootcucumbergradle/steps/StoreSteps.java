package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.entity.Store;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.StoreVerifyData;
import cucumber.api.java.zh_cn.那么;

/**
 * @author mengwei
 * 2020/6/4 11:10
 * @version 1.0
 */
public class StoreSteps extends BaseSteps {

    @那么("^预期门店销售额增加(0|[1-9][0-9]*|-[1-9][0-9]*)元")
    public void storeVerifyStep(int salesSum) throws Throwable {
        for (Store store : dataManager.getAllStore()) {
            StoreVerifyData storeVerifyData = new StoreVerifyData();
            storeVerifyData.setSalesSum(salesSum);
            store.setExpectedData(storeVerifyData);
        }
    }

    @那么("^预期商品([^\"]+)门店原始仓库存增加(0|[1-9][0-9]*|-[1-9][0-9]*)$")
    public void storeOriginalRepositoryVerifyStep(String barcode, int quantity) throws Throwable {
        for (Store store : dataManager.getAllStore()) {
            ProductVerifyData productVerifyData = new ProductVerifyData(barcode);
            productVerifyData.setQuantity(quantity);
            store.getOriginalRepository().addProductVerifyData(productVerifyData);
        }
    }

    @那么("^预期商品([^\"]+)门店标准仓库存增加(0|[1-9][0-9]*|-[1-9][0-9]*)$")
    public void storeStandardRepositoryVerifyStep(String barcode, int quantity) throws Throwable {
        for (Store store : dataManager.getAllStore()) {
            ProductVerifyData productVerifyData = new ProductVerifyData(barcode);
            productVerifyData.setQuantity(quantity);
            store.getStandardRepository().addProductVerifyData(productVerifyData);
        }
    }
}
