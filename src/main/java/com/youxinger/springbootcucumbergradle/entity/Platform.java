package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.IVerify;
import com.youxinger.springbootcucumbergradle.entity.verify.PlatformVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.PlatformVerifyData;

/**
 * @author mengwei
 * 2020/5/27 17:17
 * @version 1.0
 */
public class Platform extends BaseEntity<PlatformVerifyData>{

    private int id;//平台id
    private String name;//平台名称
    private Store store;//平台所属门店

    public Platform(int id, String name){
        this.id = id;
        this.name = name;
        PlatformVerify platformVerify = new PlatformVerify(name);
        this.verify = (IVerify)platformVerify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", store=" + store +
                '}';
    }
}
