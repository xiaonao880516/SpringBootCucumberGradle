package com.youxinger.springbootcucumbergradle.refactoring.extractClass;

/**
 * @author mengwei
 * 2020/6/9 15:04
 * @version 1.0
 */
class Person {
    private String name;
    private String officeAreaCode;
    private String officeNumber;

    public String getName() {
        return name;
    }

    public String getTelephoneNumber() {
        return ("(" + officeAreaCode + ")" + officeNumber);
    }

    String getOfficeAreaCode() {
        return officeAreaCode;
    }

    void setOfficeAreaCode(String arg) {
        this.officeAreaCode = arg;
    }

    String getOfficeNumber() {
        return this.officeNumber;
    }

    void setOfficeNumber(String arg) {
        this.officeNumber = arg;
    }
}