package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:17
 * @version 1.0
 */
public class Store extends BaseEntity{

    private String name;//门店名称
    private String number;//门店编号
    private Repository repository;//门店仓库
    private List<Platform> platformList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    public Store(String name, String number){
        this.name = name;
        this.number = number;
        this.repository = new Repository(name, Constants.PRODUCTS_BARCODE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", repository=" + repository +
                ", platformList=" + platformList +
                ", employeeList=" + employeeList +
                '}';
    }
}
