package com.youxinger.springbootcucumbergradle.refactoring.moveMethod.better;

/**
 * @author mengwei
 * 2020/6/9 17:04
 * @version 1.0
 */
public class AccountType {

    public boolean isPremium() {
        return true;
    }

    double overdraftCharge(int daysOverDrawn) {
        if (isPremium()) {
            double result = 10;
            if (daysOverDrawn > 7)
                result += (daysOverDrawn - 7) * 0.85;
            return result;
        } else return daysOverDrawn * 1.75;
    }

    //如果该方法调用了AccountNew的更多值域,我必须将source object传给函数
    double overdraftChargeNew(Account account) {
        if (isPremium()) {
            double result = 10;
            if (account.daysOverDrawn > 7)
                result += (account.daysOverDrawn - 7) * 0.85;
            return result;
        } else return account.daysOverDrawn * 1.75;
    }
}
