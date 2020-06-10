package com.youxinger.springbootcucumbergradle.refactoring.replaceParameterWithMethod;

/**
 * @author mengwei
 * 2020/6/9 16:47
 * @version 1.0
 */
public class ReplaceParameterWithMethod {
    private int quantity;
    private int itemPrice;

    public double getPrice() {
        int basePrice = this.quantity * this.itemPrice;
        int discountLevel;
        if (this.quantity > 100) {
            discountLevel = 2;
        } else {
            discountLevel = 1;
        }
        double finalPrice = discontedPrice(basePrice, discountLevel);
        return finalPrice;
    }

    private double discontedPrice(int basePrice, int discontLevel) {
        if (discontLevel == 2) return basePrice * 0.1;
        else return basePrice * 0.05;
    }
}
