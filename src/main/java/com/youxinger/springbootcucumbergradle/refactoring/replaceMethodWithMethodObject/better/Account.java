package com.youxinger.springbootcucumbergradle.refactoring.replaceMethodWithMethodObject.better;

/**
 * @author mengwei
 * 2020/6/9 16:06
 * @version 1.0
 * Replace Method with Method Object(用新建的方法对象替换方法)
 */
public class Account {

    //* 重构后的函数
    int gamma(int inputVal, int quantity, int yearToDate) {
        return new Gamma(this, inputVal, quantity, yearToDate).comput();
    }

    int delta() {
        return 1;
    }
}

//*新创建的函数对象
class Gamma {
    private final Account _account;
    private int inputVal;
    private int quantity;
    private int yearToDate;
    private int importantValue1;
    private int importantValue2;
    private int importantValue3;

    Gamma(Account source, int inputValArg, int quantityArg, int yearToDateArg) {
        this._account = source;
        this.inputVal = inputValArg;
        this.quantity = quantityArg;
        this.yearToDate = yearToDateArg;
    }

    int comput() {
        importantValue1 = (inputVal * quantity) + _account.delta();
        importantValue2 = (inputVal * yearToDate) + 100;
        importantThing();
        importantValue3 = importantValue2 * 7;
        //and so on.
        return importantValue3 - 2 * importantValue1;
    }

    void importantThing() {
        if ((yearToDate - importantValue1) > 100) {
            importantValue2 -= 20;
        }
    }
}