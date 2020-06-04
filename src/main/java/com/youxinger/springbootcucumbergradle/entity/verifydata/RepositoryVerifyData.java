package com.youxinger.springbootcucumbergradle.entity.verifydata;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mengwei
 * 2020/6/3 9:21
 * @version 1.0
 */
public class RepositoryVerifyData {
    private Map<String, ProductVerifyData> productVerifyDataMap = new HashMap<>();

    public Map<String, ProductVerifyData> getProductVerifyDataMap() {
        return productVerifyDataMap;
    }

    public void setProductVerifyDataMap(Map<String, ProductVerifyData> productVerifyDataMap) {
        this.productVerifyDataMap = productVerifyDataMap;
    }

    @Override
    public String toString() {
        return "RepositoryVerifyData{" +
                "productVerifyDataMap=" + productVerifyDataMap +
                '}';
    }
}
