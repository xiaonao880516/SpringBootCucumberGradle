package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.PlatformVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.PlatformVerifyData;

/**
 * @author mengwei
 * 2020/5/27 17:17
 * @version 1.0
 */
public class Platform extends BaseEntity{

    private int id;//平台id
    private String name;//平台名称
    private Store store;//平台所属门店
    private PlatformVerifyData preVerifyData;
    private PlatformVerifyData postVerifyData;
    private PlatformVerifyData expectedData;

    public Platform(int id, String name){
        this.id = id;
        this.name = name;
        this.verify = new PlatformVerify();
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

    public PlatformVerifyData getPreVerifyData() {
        return preVerifyData;
    }

    public void setPreVerifyData(PlatformVerifyData preVerifyData) {
        this.preVerifyData = preVerifyData;
    }

    public PlatformVerifyData getPostVerifyData() {
        return postVerifyData;
    }

    public void setPostVerifyData(PlatformVerifyData postVerifyData) {
        this.postVerifyData = postVerifyData;
    }

    public PlatformVerifyData getExpectedData() {
        return expectedData;
    }

    public void setExpectedData(PlatformVerifyData expectedData) {
        this.expectedData = expectedData;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", store=" + store +
                ", preVerifyData=" + preVerifyData +
                ", postVerifyData=" + postVerifyData +
                ", expectedData=" + expectedData +
                '}';
    }
}
