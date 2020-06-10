package com.youxinger.springbootcucumbergradle.refactoring.hideDelegate.better;

/**
 * @author mengwei
 * 2020/6/9 17:26
 * @version 1.0
 */
public class DepartmentNew {

    private PersonNew manger;

    public DepartmentNew(PersonNew manger) {
        this.manger = manger;
    }

    public PersonNew getManger() {
        return manger;
    }
}
