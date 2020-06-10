package com.youxinger.springbootcucumbergradle.refactoring.hideDelegate.better;

/**
 * @author mengwei
 * 2020/6/9 17:27
 * @version 1.0
 * 封装即使不是对象最关键特征，也是最关键特征之一，封装意味着每个对象都应该尽可能减少了解系统的其它部分。
 * 如此一来，一旦发生变化，需要了解这个变化的对象就会比较少，这会使变化比较容易进行。
 */
public class PersonNew {
    private DepartmentNew department;

    PersonNew() {
    }

    //委托给DepartmentNew实现
    public PersonNew getManger() {
        return department.getManger();
    }

    public static void main(String[] args) {
        PersonNew John = new PersonNew();
        PersonNew manger = John.getManger();
    }
}
