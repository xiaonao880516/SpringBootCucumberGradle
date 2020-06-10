package com.youxinger.springbootcucumbergradle.refactoring.moveField;

/**
 * @author mengwei
 * 2020/6/9 17:13
 * @version 1.0
 * 你的程序中，某个field被其所驻class之外的另一个class更多的用到，就要考虑搬移这个值域，使用可能是通过（getting/setting）函数间接进行。
 * 在classes之间移动状态和行为，是重构过程中必须可少的措施。随着系统的发展，你会发现自己需要新的classes，并需要将原本的工作责任托到新的class中。
 * 这星期中合理而正确的设计决策，到下个星期可能不再正确。这没问题：如果你从来没遇到这种情况，那才有问题。
 */
public class Account {

    private AccountType type;
    private double interestRate;

    double interestForAmountDays(double amount, int days) {
        return interestRate * amount * days / 365;
    }

    double interestForAmountDaysNew(double amount, int days) {
        return type.getInterestRate() * amount * days / 365;
    }
}
