package com.youxinger.springbootcucumbergradle.refactoring.extractClass;

/**
 * @author mengwei
 * 2020/6/9 15:05
 * @version 1.0
 */
class TelephoneNumber {
    private String number;
    private String areaCode;

    public String getTelephoneNumber() {
        return ("(" + this.areaCode + ")" + this.number);
    }

    String getAreaCode() {
        return this.areaCode;
    }

    void setAreaCode(String arg) {
        this.areaCode = arg;
    }

    String getNumber() {
        return this.number;
    }

    void setNumber(String arg) {
        this.number = arg;
    }
}