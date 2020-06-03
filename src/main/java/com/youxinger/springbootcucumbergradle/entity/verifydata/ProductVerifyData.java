package com.youxinger.springbootcucumbergradle.entity.verifydata;

/**
 * @author mengwei
 * 2020/6/2 15:34
 * @version 1.0
 */
public class ProductVerifyData {

    private String barcode; // 商品条码
    private int quantity = 0;  //库存值

    public ProductVerifyData(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductVerifyData{" +
                "barcode='" + barcode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}