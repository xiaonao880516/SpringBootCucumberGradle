package com.youxinger.springbootcucumbergradle.refactoring.extractClass;

/**
 * @author mengwei
 * 2020/6/9 15:05
 * @version 1.0
 */
class NewPerson {
    private String name;
    private TelephoneNumber officeTelephone = new TelephoneNumber();

    public String getName() {
        return this.name;
    }

    public String getTelephoneNumber() {
        return this.officeTelephone.getTelephoneNumber();
    }

    TelephoneNumber getOfficeTelephone() {
        return this.officeTelephone;
    }
}