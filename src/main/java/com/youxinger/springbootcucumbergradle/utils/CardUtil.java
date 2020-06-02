package com.youxinger.springbootcucumbergradle.utils;

/**
 * @author mengwei
 * 2020/5/29 16:22
 * @version 1.0
 */
public class CardUtil {

    /**
     * 普卡:0
     * 银卡:1
     * 金卡:2
     * 钻卡:3
     * 黑卡:4
     *
     * @param cardLevel
     * @return
     */
    public static int getCardLevel(String cardLevel) {
        switch (cardLevel) {
            case "普卡":
                return 0;
            case "银卡":
                return 1;
            case "金卡":
                return 2;
            case "钻卡":
                return 3;
            case "黑卡":
                return 4;
            default:
                return -1;
        }
    }
}
