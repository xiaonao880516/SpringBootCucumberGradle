package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.IVerify;

/**
 * @author mengwei
 * 2020/5/29 17:27
 * @version 1.0
 */
public class BaseEntity<Data> {

    protected IVerify<BaseEntity, Data> verify;

    /**
     * 更新操作之前值
     */
    public void updatePreVerifyData() {
        if (verify != null) {
            verify.updatePreVerifyData(this);
        }
        childUpdatePreVerifyData();
    }

    /**
     * 更新子节点操作之前值
     */
    protected void childUpdatePreVerifyData() {

    }

    /**
     * 更新操作之后值
     */
    public void updatePostVerifyData() {
        if (verify != null) {
            verify.updatePostVerifyData(this);
        }
        childUpdatePostVerifyData();
    }

    /**
     * 更新子节点操作之后值
     */
    protected void childUpdatePostVerifyData() {

    }

    /**
     * 设置预期值
     * @param expectedData
     */
    public void setExpectedData(Data expectedData) {
        if (verify != null) {
            verify.setExpectedData(expectedData);
        }
    }

    /**
     * 验证预期值
     */
    public void verifyData() {
        if (verify != null) {
            verify.verifyData();
        }
        childVerifyData();
    }

    /**
     * 验证子节点预期值
     */
    protected void childVerifyData() {

    }
}
