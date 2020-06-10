package com.youxinger.springbootcucumbergradle.refactoring.decomposeConditional;

import java.util.Date;

/**
 * @author mengwei
 * 2020/6/9 16:22
 * @version 1.0
 * Decompose Conditional(分解条件式)
 */
public class DecomposeConditionalBetter {
    private Date date;
    private static final Date SUMMER_START = new Date();
    private static final Date SUMMER_END = new Date();
    int quantity = 0;
    int _winterRate = 1;
    int _summerRate = 2;
    int _winterServiceCharge = 0;

    //复杂条件逻辑是最常导致复杂度上升的地点之一,并且使代码更难以阅读
    //突出条件逻辑,更清楚的表明每个分支的作用,并且突出每个分支的原因.
    public void charge() {
        double charge;
        if (notSummer(date)) {
            charge = winterCharge(quantity);
        } else {
            charge = summerCharge(quantity);
        }
    }

    //判断日期是否在夏天
    private boolean notSummer(Date date) {
        return date.before(SUMMER_START) || date.after(SUMMER_END);
    }

    // 夏天的计费
    private double summerCharge(int quantity) {
        return quantity * _summerRate;
    }

    //冬天的计费
    private double winterCharge(int quantity) {
        return quantity * _winterRate + _winterServiceCharge;
    }
}
