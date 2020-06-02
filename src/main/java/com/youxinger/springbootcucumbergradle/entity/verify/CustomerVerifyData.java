package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.utils.CardUtil;

/**
 * @author mengwei
 * 2020/5/29 15:57
 * @version 1.0
 */
public class CustomerVerifyData {
    private float consumption; //会员消费额
    private int swapScore;  //会员积分
    private int cardLevel;  //会员卡等级
    private float balance;  // 会员余额

    public CustomerVerifyData(float consumption, int swapScore, int cardLevel, float balance) {
        this.consumption = consumption;
        this.swapScore = swapScore;
        this.cardLevel = cardLevel;
        this.balance = balance;
    }

    public CustomerVerifyData(float consumption, int swapScore, String cardLevelName, float balance) {
        this.consumption = consumption;
        this.swapScore = swapScore;
        this.cardLevel = CardUtil.getCardLevel(cardLevelName);
        this.balance = balance;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getSwapScore() {
        return swapScore;
    }

    public void setSwapScore(int swapScore) {
        this.swapScore = swapScore;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(int cardLevel) {
        this.cardLevel = cardLevel;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CustomerVerifyData{" +
                "consumption=" + consumption +
                ", swapScore=" + swapScore +
                ", cardLevel=" + cardLevel +
                ", balance=" + balance +
                '}';
    }
}