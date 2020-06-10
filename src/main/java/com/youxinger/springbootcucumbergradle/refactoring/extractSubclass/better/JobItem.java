package com.youxinger.springbootcucumbergradle.refactoring.extractSubclass.better;

/**
 * @author mengwei
 * 2020/6/9 16:43
 * @version 1.0
 */
public class JobItem {
    private int _unitPrice;
    protected int _quantity;

    JobItem(int unitPrice,int quantity){
        _unitPrice=unitPrice; _quantity=quantity;
    }

    public int getUnitPrice(){
        return _unitPrice;
    }

    public int getTotalPrice(){
        return getUnitPrice() * _quantity;
    }
}
