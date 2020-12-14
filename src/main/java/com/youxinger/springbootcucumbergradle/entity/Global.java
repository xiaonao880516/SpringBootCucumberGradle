package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.GlobalVerify;
import com.youxinger.springbootcucumbergradle.entity.verify.IVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.GlobalVerifyData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mengwei
 * 2020/5/27 17:18
 * @version 1.0
 */
public class Global extends BaseEntity<GlobalVerifyData> {

    //所有省运营中心
    private List<ProvinceOperationCenter> provinceOperationCenterList = new ArrayList<>();
    //所有客户
    private Map<String, Customer> customerMap = new HashMap<>();
    //总仓
    private Repository repository;

    //积分商城总仓
    private Repository pointMallRepository;

    public Global() {
        this.repository = new Repository("GLOBAL","总仓");
        this.pointMallRepository = new Repository("GLOBAL_POINT_MALL","积分商城总仓");
        GlobalVerify globalVerify = new GlobalVerify();
        this.verify = (IVerify)globalVerify;
    }

    public List<ProvinceOperationCenter> getProvinceOperationCenterList() {
        return provinceOperationCenterList;
    }

    public void setProvinceOperationCenterList(List<ProvinceOperationCenter> provinceOperationCenterList) {
        this.provinceOperationCenterList = provinceOperationCenterList;
    }

    public Map<String, Customer> getCustomerMap() {
        return customerMap;
    }

    public Customer getCustomerByName(String name) {
        return customerMap.get(name);
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Repository getPointMallRepository() {
        return pointMallRepository;
    }

    public void setPointMallRepository(Repository pointMallRepository) {
        this.pointMallRepository = pointMallRepository;
    }

    @Override
    protected void childUpdatePreVerifyData() {
        for (ProvinceOperationCenter provinceOperationCenter : provinceOperationCenterList) {
            provinceOperationCenter.updatePreVerifyData();
        }
        for(Customer customer : customerMap.values()){
            customer.updatePreVerifyData();
        }
        repository.updatePreVerifyData();
        pointMallRepository.updatePreVerifyData();
    }

    @Override
    protected void childUpdatePostVerifyData() {
        for (ProvinceOperationCenter provinceOperationCenter : provinceOperationCenterList) {
            provinceOperationCenter.updatePostVerifyData();
        }
        for(Customer customer : customerMap.values()){
            customer.updatePostVerifyData();
        }
        repository.updatePostVerifyData();
        pointMallRepository.updatePostVerifyData();
    }

    @Override
    protected void childVerifyData() {
        for (ProvinceOperationCenter provinceOperationCenter : provinceOperationCenterList) {
            provinceOperationCenter.verifyData();
        }
        for(Customer customer : customerMap.values()){
            customer.verifyData();
        }
        repository.verifyData();
        pointMallRepository.verifyData();
    }

    @Override
    public String toString() {
        return "Global{" +
                "provinceOperationCenterList=" + provinceOperationCenterList +
                ", customerMap=" + customerMap +
                ", repository=" + repository +
                ", pointMallRepository=" + pointMallRepository +
                '}';
    }
}
