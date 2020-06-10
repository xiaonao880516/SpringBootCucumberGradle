package com.youxinger.springbootcucumbergradle.refactoring.extractMethod;

/**
 * @author mengwei
 * 2020/6/9 11:13
 * @version 1.0
 */


public class ExtractMethod {

//  Extract Method：
//            1、函数的粒度都很小（finely grained），那么函数之间彼此复用机会就会大。
//            2、使高层函数码读起来就像一系列注释。
//            3、函数覆写（overrride）会更容易。
    private String name;


    void printCustomer(double amount) {
        printBanner();
        //print details
        System.out.println("name:" + name);
        System.out.println("amount" + amount);
    }

    void printCustomerNew(double amount) {
        printBanner();
        printDetails(amount);
    }

    void printDetails(double amount) {
        System.out.println("name:" + name);
        System.out.println("amount" + amount);
    }

    void printBanner(){}
}
