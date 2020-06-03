package com.youxinger.springbootcucumbergradle.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:16
 * @version 1.0
 */
public class ProvinceOperationCenter extends BaseEntity{

    private String name;  // 省运营中心名称
    private String number;  //省运营中心编号
    private List<OperationCenter> operationCenterList = new ArrayList<>();

    public ProvinceOperationCenter(String name, String number){
        this.name = name;
        this.number = number;
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

    public List<OperationCenter> getOperationCenterList() {
        return operationCenterList;
    }

    public void setOperationCenterList(List<OperationCenter> operationCenterList) {
        this.operationCenterList = operationCenterList;
    }

    @Override
    public String toString() {
        return "ProvinceOperationCenter{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", operationCenterList=" + operationCenterList +
                '}';
    }
}
