package com.youxinger.springbootcucumbergradle.refactoring.moveMethod;

/**
 * @author mengwei
 * 2020/6/9 16:56
 * @version 1.0
 * 帐户类,不同的账户可能有不同的透支金额计算规则
 */
public class Account {
    private int daysOverDrawn;//透支天数
    private AccountType type;

    //不同的账户可能有不同的透支金额计算规则,这时就要把该函数移动到AccountType
    double overdraftCharge() {//透支金计算,它与type关联比较密切
        if (type.isPremium()) {
            double result = 10;
            if (daysOverDrawn > 7)
                result += (daysOverDrawn - 7) * 0.85;
            return result;
        } else {
            return daysOverDrawn * 1.75;
        }
    }

    //银行收费计算
    double bankCharge() {
        double result = 4.5;
        if (daysOverDrawn > 0) result += overdraftCharge();
        return result;
    }
}
