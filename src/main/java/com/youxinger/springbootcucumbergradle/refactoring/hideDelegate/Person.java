package com.youxinger.springbootcucumbergradle.refactoring.hideDelegate;

/**
 * @author mengwei
 * 2020/6/9 17:23
 * @version 1.0
 */
public class Person {

    private Department department;

    Person() {
    }

    public Department getDepartment() {
        return department;
    }

    public static void main(String[] args) {
        Person John = new Person();
        Person manger = John.getDepartment().getManger();
    }
}
