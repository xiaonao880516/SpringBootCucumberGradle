package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 18:26
 * @version 1.0
 */
public class Repository extends BaseEntity{

    private String name;//仓库名称
    private List<ProductVerifyData> productVerifyDataList = new ArrayList<>();

    public Repository(String name, String[] productsBarcode) {
        this.name = name;
        if (productsBarcode != null && productsBarcode.length > 0) {
            for (String barcode : productsBarcode) {
                productVerifyDataList.add(new ProductVerifyData(barcode));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductVerifyData> getProductVerifyDataList() {
        return productVerifyDataList;
    }

    public void setProductVerifyDataList(List<ProductVerifyData> productVerifyDataList) {
        this.productVerifyDataList = productVerifyDataList;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", productVerifyDataList=" + productVerifyDataList +
                '}';
    }
}
