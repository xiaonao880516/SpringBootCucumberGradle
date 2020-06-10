package com.youxinger.springbootcucumbergradle.refactoring.extractSubclass;

/**
 * @author mengwei
 * 2020/6/9 16:36
 * @version 1.0
 */
public class Employee {
    private int _rate;

    public Employee(int rate) {
        this._rate = rate;
    }

    public int getRate() {
        return this._rate;
    }
}
