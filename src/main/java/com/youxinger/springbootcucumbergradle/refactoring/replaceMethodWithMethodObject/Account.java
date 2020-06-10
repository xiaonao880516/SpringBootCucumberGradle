package com.youxinger.springbootcucumbergradle.refactoring.replaceMethodWithMethodObject;

/**
 * @author mengwei
 * 2020/6/9 16:06
 * @version 1.0
 */
public class Account {

    //一个拥有较多局部变量的函数
    //不用理解其真正含义
    int gamma(int inputVal,int quantity,int yearToDate){
        int importantValue1 = (inputVal * quantity) +delta();
        int importantValue2 = (inputVal*yearToDate) +100;
        if((yearToDate - importantValue1)>100){
            importantValue2 -=20;
        }
        int importantValue3 = importantValue2 * 7;
        //and so on.
        return importantValue3 -2 * importantValue1;
    }

    int delta(){
        return 1;
    }
}
