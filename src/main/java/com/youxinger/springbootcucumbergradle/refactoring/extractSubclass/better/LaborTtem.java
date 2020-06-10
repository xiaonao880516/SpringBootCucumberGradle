package com.youxinger.springbootcucumbergradle.refactoring.extractSubclass.better;

import com.youxinger.springbootcucumbergradle.refactoring.extractSubclass.Employee;

/**
 * @author mengwei
 * 2020/6/9 16:44
 * @version 1.0
 * Class中的某些特性(features)只被某些(而非全部)实体(instances)用到，有时候这种行为上的差异通过type code区分
 */
public class LaborTtem extends JobItem {

    private Employee _employee;

    LaborTtem(int quantity, Employee employee) {
        super(0, quantity);
        super._quantity = quantity;
        _employee = employee;
    }

    public int getUnitPrice() {
        return _employee.getRate();
    }
}
