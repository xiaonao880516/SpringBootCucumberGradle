package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.IVerify;
import com.youxinger.springbootcucumbergradle.entity.verify.OperationCenterVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.OperationCenterVerifyData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:16
 * @version 1.0
 */
public class OperationCenter extends BaseEntity<OperationCenterVerifyData>{

    private String name;  // 运营中心名称
    private String number;  //运营中心编号
    private List<Store> storeList = new ArrayList<>();

    public OperationCenter(String name, String number){
        this.name = name;
        this.number = number;
        OperationCenterVerify operationCenterVerify = new OperationCenterVerify(name);
        this.verify = (IVerify)operationCenterVerify;
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

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    @Override
    public String toString() {
        return "OperationCenter{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", storeList=" + storeList +
                '}';
    }

    @Override
    protected void childUpdatePreVerifyData() {
        for(Store store : storeList){
            store.updatePreVerifyData();
        }
    }

    @Override
    protected void childUpdatePostVerifyData() {
        for(Store store : storeList){
            store.updatePostVerifyData();
        }
    }

    @Override
    protected void childVerifyData() {
        for(Store store : storeList){
            store.verifyData();
        }
    }
}
