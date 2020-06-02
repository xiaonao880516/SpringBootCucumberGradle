package com.youxinger.springbootcucumbergradle.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 18:26
 * @version 1.0
 */
public class Repository {

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

class ProductVerifyData {

    private String barcode = null; // 商品条码
    private int preQuantity = 0;  //操作之前库存值
    private int expectedQuantity = 0;  //期待库存变化值
    private int postQuantity = 0;  //操作之后库存值

    public ProductVerifyData(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getPreQuantity() {
        return preQuantity;
    }

    public void setPreQuantity(int preQuantity) {
        this.preQuantity = preQuantity;
    }

    public int getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(int expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public int getPostQuantity() {
        return postQuantity;
    }

    public void setPostQuantity(int postQuantity) {
        this.postQuantity = postQuantity;
    }

    @Override
    public String toString() {
        return "ProductVerifyData{" +
                "barcode='" + barcode + '\'' +
                ", preQuantity=" + preQuantity +
                ", expectedQuantity=" + expectedQuantity +
                ", postQuantity=" + postQuantity +
                '}';
    }
}
