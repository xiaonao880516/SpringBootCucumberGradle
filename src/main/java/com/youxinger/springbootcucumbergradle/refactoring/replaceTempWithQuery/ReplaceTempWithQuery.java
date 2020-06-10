package com.youxinger.springbootcucumbergradle.refactoring.replaceTempWithQuery;

/**
 * @author mengwei
 * 2020/6/9 15:29
 * @version 1.0
 * 方便其他函数调用,使你能够为这个class编写更清晰的代码.
 */
public class ReplaceTempWithQuery {


    private double quantity;
    private double itemPrice;


    double testReplace() {
        double basePrice = quantity * itemPrice;
        if (basePrice > 1000)
            return basePrice * 0.95;
        else
            return basePrice * 0.98;
    }

    double testReplaceNew() {
        if (basePrice() > 1000)
            return basePrice() * 0.95;
        else
            return basePrice() * 0.98;
    }


    double basePrice() {
        return quantity * itemPrice;
    }


}
