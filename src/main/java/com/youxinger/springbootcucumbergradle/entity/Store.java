package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.IVerify;
import com.youxinger.springbootcucumbergradle.entity.verify.StoreVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.StoreVerifyData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:17
 * @version 1.0
 */
public class Store extends BaseEntity<StoreVerifyData> {

    private String name;//门店名称
    private String number;//门店编号
    private Repository originalRepository;//门店原始仓库
    private Repository standardRepository;//门店标准仓库
    private List<Platform> platformList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    public Store(String name, String number, String originalRepoId, String standardRepoId) {
        this.name = name;
        this.number = number;
        this.originalRepository = new Repository(originalRepoId, name+"原始仓");
        this.standardRepository = new Repository(standardRepoId, name+"标准仓");
        StoreVerify storeVerify = new StoreVerify(name);
        this.verify = (IVerify)storeVerify;
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

    public Repository getOriginalRepository() {
        return originalRepository;
    }

    public void setOriginalRepository(Repository originalRepository) {
        this.originalRepository = originalRepository;
    }

    public Repository getStandardRepository() {
        return standardRepository;
    }

    public void setStandardRepository(Repository standardRepository) {
        this.standardRepository = standardRepository;
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
                ", originalRepository=" + originalRepository +
                ", standardRepository=" + standardRepository +
                ", platformList=" + platformList +
                ", employeeList=" + employeeList +
                '}';
    }

    @Override
    protected void childUpdatePreVerifyData() {
        for (Platform platform : platformList) {
            platform.updatePreVerifyData();
        }
        for (Employee employee : employeeList) {
            employee.updatePreVerifyData();
        }
        originalRepository.updatePreVerifyData();
        standardRepository.updatePreVerifyData();
    }

    @Override
    protected void childUpdatePostVerifyData() {
        for (Platform platform : platformList) {
            platform.updatePostVerifyData();
        }
        for (Employee employee : employeeList) {
            employee.updatePostVerifyData();
        }
        originalRepository.updatePostVerifyData();
        standardRepository.updatePostVerifyData();
    }

    @Override
    protected void childVerifyData() {
        for (Platform platform : platformList) {
            platform.verifyData();
        }
        for (Employee employee : employeeList) {
            employee.verifyData();
        }
        originalRepository.verifyData();
        standardRepository.verifyData();
    }
}
