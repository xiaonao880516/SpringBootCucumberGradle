package com.youxinger.springbootcucumbergradle.entity;

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

    public Global(){
        this.repository = new Repository("总仓", Constants.PRODUCTS_BARCODE);
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
    public String toString() {
        return "Global{" +
                "provinceOperationCenterList=" + provinceOperationCenterList +
                ", repository=" + repository +
                '}';
    }
}
