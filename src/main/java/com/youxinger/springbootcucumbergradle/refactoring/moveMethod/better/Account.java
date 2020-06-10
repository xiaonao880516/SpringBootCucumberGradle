package com.youxinger.springbootcucumbergradle.refactoring.moveMethod.better;

/**
 * @author mengwei
 * 2020/6/9 17:05
 * @version 1.0
 * 动机：“函数搬移”是重构理论的支柱。如果一个class有太多行为，或如果一个class与另一个class有太多合作而形成高度耦合（highly coupled），就要运用搬移函数。
 * 通过这种手段，我可以使系统中的classes更简单，这些classes最终也将更干净利落的实现系统交付的任务。
 * 你的程序中,有个函数与其所驻的class之外的另一个class进行更多交流;调用后者,或被后者调用.
 */
public class Account {

    public int daysOverDrawn;
    private AccountType type;

    //*银行收费计算
    double bankCharge() {
        double result = 4.5;
        if (daysOverDrawn > 0) result += type.overdraftCharge(daysOverDrawn);
        return result;
    }
}
