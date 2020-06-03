package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.GlobalVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.GlobalVerifyData;
import com.youxinger.springbootcucumbergradle.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 17:18
 * @version 1.0
 */
public class Global extends BaseEntity{

    //所有省运营中心
    private List<ProvinceOperationCenter> provinceOperationCenterList = new ArrayList<>();
    //总仓
    private Repository repository;
    private GlobalVerifyData preVerifyData;
    private GlobalVerifyData postVerifyData;
    private GlobalVerifyData expectedData;

    public Global(){
        this.repository = new Repository("总仓", Constants.PRODUCTS_BARCODE);
        this.verify = new GlobalVerify();
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

    public GlobalVerifyData getPreVerifyData() {
        return preVerifyData;
    }

    public void setPreVerifyData(GlobalVerifyData preVerifyData) {
        this.preVerifyData = preVerifyData;
    }

    public GlobalVerifyData getPostVerifyData() {
        return postVerifyData;
    }

    public void setPostVerifyData(GlobalVerifyData postVerifyData) {
        this.postVerifyData = postVerifyData;
    }

    public GlobalVerifyData getExpectedData() {
        return expectedData;
    }

    public void setExpectedData(GlobalVerifyData expectedData) {
        this.expectedData = expectedData;
    }

    @Override
    public String toString() {
        return "Global{" +
                "provinceOperationCenterList=" + provinceOperationCenterList +
                ", repository=" + repository +
                ", preVerifyData=" + preVerifyData +
                ", postVerifyData=" + postVerifyData +
                ", expectedData=" + expectedData +
                '}';
    }
}
