package com.youxinger.springbootcucumbergradle.refactoring.moveField;

/**
 * @author mengwei
 * 2020/6/9 17:14
 * @version 1.0
 */
public class AccountType {
    private double interestRate;

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double rate) {
        interestRate = rate;
    }
}
