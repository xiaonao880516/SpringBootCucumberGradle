package com.youxinger.springbootcucumbergradle.refactoring.decomposeConditional;

import java.util.Date;

/**
 * @author mengwei
 * 2020/6/9 16:22
 * @version 1.0
 * Decompose Conditional(分解条件式)
 */
public class DecomposeConditional {
    private Date date;
    private static final Date SUMMER_START = new Date();
    private static final Date SUMMER_END = new Date();
    int quantity = 0;
    int _winterRate = 1;
    int _summerRate = 2;
    int _winterServiceCharge = 0;

    //在带有复杂条件逻辑的函数中，代码会告诉你发生的事，但常常让你弄不清楚为什么会发生这样的事，这就说明代码的可读性的确大大降低了。
    public void charge() {
        int charge;
        if (date.before(SUMMER_START) || date.after(SUMMER_END)) {
            charge = quantity * _winterRate + _winterServiceCharge;
        } else {
            charge = quantity * _summerRate;
        }
    }
}
