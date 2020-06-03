package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.StoreVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.StoreVerifyData;
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
    private StoreVerifyData preVerifyData;
    private StoreVerifyData postVerifyData;
    private StoreVerifyData expectedData;

    public Store(String name, String number){
        this.name = name;
        this.number = number;
        this.repository = new Repository(name, Constants.PRODUCTS_BARCODE);
        this.verify = new StoreVerify();
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

    public StoreVerifyData getPreVerifyData() {
        return preVerifyData;
    }

    public void setPreVerifyData(StoreVerifyData preVerifyData) {
        this.preVerifyData = preVerifyData;
    }

    public StoreVerifyData getPostVerifyData() {
        return postVerifyData;
    }

    public void setPostVerifyData(StoreVerifyData postVerifyData) {
        this.postVerifyData = postVerifyData;
    }

    public StoreVerifyData getExpectedData() {
        return expectedData;
    }

    public void setExpectedData(StoreVerifyData expectedData) {
        this.expectedData = expectedData;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", repository=" + repository +
                ", platformList=" + platformList +
                ", employeeList=" + employeeList +
                ", preVerifyData=" + preVerifyData +
                ", postVerifyData=" + postVerifyData +
                ", expectedData=" + expectedData +
                '}';
    }
}
