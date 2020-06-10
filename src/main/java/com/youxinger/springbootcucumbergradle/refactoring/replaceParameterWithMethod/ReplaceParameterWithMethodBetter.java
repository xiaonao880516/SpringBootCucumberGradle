package com.youxinger.springbootcucumbergradle.refactoring.replaceParameterWithMethod;

/**
 * @author mengwei
 * 2020/6/9 16:47
 * @version 1.0
 */
public class ReplaceParameterWithMethodBetter {
    private int quantity;
    private int itemPrice;

    //如果函数可以通过其它途径（而非参数列）获得参数，那么它就不应该通过参数取得该值。
    //过长的参数列会增加程序阅读者的理解难度，因此我们应该尽可能缩短参数列长度。
    public double getPrice() {
        if (getDiscountLevel() == 2) {
            return getBasePrice() * 0.1;
        } else {
            return getBasePrice() * 0.05;
        }
    }

    private double getBasePrice() {
        return this.itemPrice * this.quantity;
    }

    private int getDiscountLevel() {
        if (this.quantity > 100) return 2;
        else return 1;
    }
}
