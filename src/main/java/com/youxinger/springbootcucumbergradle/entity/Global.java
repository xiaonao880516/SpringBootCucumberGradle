package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.GlobalVerify;
import com.youxinger.springbootcucumbergradle.entity.verify.IVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.GlobalVerifyData;
import com.youxinger.springbootcucumbergradle.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:18
 * @version 1.0
 */
public class Global extends BaseEntity<GlobalVerifyData> {

    //所有省运营中心
    private List<ProvinceOperationCenter> provinceOperationCenterList = new ArrayList<>();
    //总仓
    private Repository repository;

    public Global() {
        this.repository = new Repository("GLOBAL","总仓", Constants.PRODUCTS_BARCODE);
        GlobalVerify globalVerify = new GlobalVerify();
        this.verify = (IVerify)globalVerify;
    }

    public List<ProvinceOperationCenter> getProvinceOperationCenterList() {
        return provinceOperationCenterList;
    }

    public void setProvinceOperationCenterList(List<ProvinceOperationCenter> provinceOperationCenterList) {
        this.provinceOperationCenterList = provinceOperationCenterList;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected void childUpdatePreVerifyData() {
        for (ProvinceOperationCenter provinceOperationCenter : provinceOperationCenterList) {
            provinceOperationCenter.updatePreVerifyData();
        }
        repository.updatePreVerifyData();
    }

    @Override
    protected void childUpdatePostVerifyData() {
        for (ProvinceOperationCenter provinceOperationCenter : provinceOperationCenterList) {
            provinceOperationCenter.updatePostVerifyData();
        }
        repository.updatePostVerifyData();
    }

    @Override
    protected void childVerifyData() {
        for (ProvinceOperationCenter provinceOperationCenter : provinceOperationCenterList) {
            provinceOperationCenter.verifyData();
        }
        repository.verifyData();
    }

    @Override
    public String toString() {
        return "Global{" +
                "provinceOperationCenterList=" + provinceOperationCenterList +
                ", repository=" + repository +
                '}';
    }
}
