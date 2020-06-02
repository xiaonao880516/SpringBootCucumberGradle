package com.youxinger.springbootcucumbergradle.entity;

/**
 * 前台员工实体类
 */
public class Employee {

    private int id;
    private String name;
    private String phone;
    private String password;
    private boolean entered = false;
    private String tid; //前台登录tid
    private Store store = null;//员工所属门店

    public Employee(){

    }

    public Employee(int id, String name, String phone, String password){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", entered=" + entered +
                ", tid='" + tid + '\'' +
                ", store=" + store +
                '}';
    }
}

class EmployeeVerifyData {
    private float performance = 0.0f;  //员工业绩

    public float getPerformance() {
        return performance;
    }

    public void setPerformance(float performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "EmployeeVerifyData{" +
                "performance=" + performance +
                '}';
    }
}
