package com.youxinger.springbootcucumbergradle.entity.verifydata;

/**
 * @author mengwei
 * 2020/6/2 15:34
 * @version 1.0
 */
public class ProductVerifyData {

    private String barcode; // 商品条码
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