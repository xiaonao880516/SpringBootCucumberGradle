package com.youxinger.springbootcucumbergradle.refactoring.extractSubclass.old;

import com.youxinger.springbootcucumbergradle.refactoring.extractSubclass.Employee;

/**
 * @author mengwei
 * 2020/6/9 16:35
 * @version 1.0
 */
public class JobItem {

    private int _unitPrice;//单价
    private int _quantity;//数量
    private Employee _employee;

    //标示是否为正式工
    private boolean _isLabor;

    JobItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
        _unitPrice = unitPrice;
        _quantity = quantity;
        _isLabor = isLabor;
        _employee = employee;
    }

    public int getTotalPrice() {
        return getUnitPrice() * _quantity;
    }

    public int getQuantity() {
        return _quantity;
    }

    public int getUnitPrice() {
        return (this._isLabor) ? this._employee.getRate() : this._unitPrice;
    }
}
