package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.ProvinceOperationCenterVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProvinceOperationCenterVerifyData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:16
 * @version 1.0
 */
public class ProvinceOperationCenter extends BaseEntity{

    private String name = null;  // 省运营中心名称
    private String number = null;  //省运营中心编号
    private List<OperationCenter> operationCenterList = new ArrayList<>();
    private ProvinceOperationCenterVerifyData preVerifyData;
    private ProvinceOperationCenterVerifyData postVerifyData;
    private ProvinceOperationCenterVerifyData expectedData;

    public ProvinceOperationCenter(String name, String number){
        this.name = name;
        this.number = number;
        this.verify = new ProvinceOperationCenterVerify();
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

    public ProvinceOperationCenterVerifyData getPreVerifyData() {
        return preVerifyData;
    }

    public void setPreVerifyData(ProvinceOperationCenterVerifyData preVerifyData) {
        this.preVerifyData = preVerifyData;
    }

    public ProvinceOperationCenterVerifyData getPostVerifyData() {
        return postVerifyData;
    }

    public void setPostVerifyData(ProvinceOperationCenterVerifyData postVerifyData) {
        this.postVerifyData = postVerifyData;
    }

    public ProvinceOperationCenterVerifyData getExpectedData() {
        return expectedData;
    }

    public void setExpectedData(ProvinceOperationCenterVerifyData expectedData) {
        this.expectedData = expectedData;
    }

    @Override
    public String toString() {
        return "ProvinceOperationCenter{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", operationCenterList=" + operationCenterList +
                ", preVerifyData=" + preVerifyData +
                ", postVerifyData=" + postVerifyData +
                ", expectedData=" + expectedData +
                '}';
    }
}
