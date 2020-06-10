package com.youxinger.springbootcucumbergradle.refactoring.hideDelegate;

/**
 * @author mengwei
 * 2020/6/9 17:23
 * @version 1.0
 */
public class Department {

    private Person manger;

    public Department(Person manger) {
        this.manger = manger;
    }

    public Person getManger() {
        return manger;
    }
}
