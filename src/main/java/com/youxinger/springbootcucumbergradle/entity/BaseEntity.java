package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.IVerify;

/**
 * @author mengwei
 * 2020/5/29 17:27
 * @version 1.0
 */
public class BaseEntity<Data> {

    protected IVerify<BaseEntity, Data> verify;

    public IVerify getVerify() {
        return verify;
    }

    public void setVerify(IVerify<BaseEntity, Data> verify) {
        this.verify = verify;
    }

    public void updatePreVerifyData() {
        if (verify != null) {
            verify.updatePreVerifyData(this);
        }
        childUpdatePreVerifyData();
    }

    protected void childUpdatePreVerifyData() {

    }

    public void updatePostVerifyData() {
        if (verify != null) {
            verify.updatePostVerifyData(this);
        }
        childUpdatePostVerifyData();
    }

    protected void childUpdatePostVerifyData() {

    }

    public void setExpectedData(Data expectedData) {
        if (verify != null) {
            verify.setExpectedData(expectedData);
        }
    }

    public void verifyData() {
        if (verify != null) {
            verify.verifyData();
        }
        childVerifyData();
    }

    protected void childVerifyData() {

    }

    public String getEntityName(){
        return "BaseEntity";
    }
}
