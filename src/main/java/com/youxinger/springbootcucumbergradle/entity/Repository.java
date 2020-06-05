package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.RepositoryVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mengwei
 * 2020/5/27 18:26
 * @version 1.0
 */
public class Repository extends BaseEntity{

    private String name;//仓库名称
    private String id;
    private String[] productsBarcode;//仓库的条码列表

    public Repository(String id, String name, String[] productsBarcode) {
        this.id = id;
        this.name = name;
        this.productsBarcode = productsBarcode;
        this.verify = new RepositoryVerify(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getProductsBarcode() {
        return productsBarcode;
    }

    public void setProductsBarcode(String[] productsBarcode) {
        this.productsBarcode = productsBarcode;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", productsBarcode=" + Arrays.toString(productsBarcode) +
                '}';
    }
}
