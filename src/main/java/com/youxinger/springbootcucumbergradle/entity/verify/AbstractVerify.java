package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.BaseEntity;
import org.junit.Assert;

/**
 * @author mengwei
 * 2020/5/29 16:35
 * @version 1.0
 */
public abstract class AbstractVerify<Entity extends BaseEntity, Data> implements IVerify<Entity, Data> {

    /**
     * 操作之前的数据信息
     */
    protected Data preVerifyData;
    /**
     * 操作之后的数据信息
     */
    protected Data postVerifyData;
    /**
     * 期待值集合
     */
    protected Data expectedData;

    /**
     * 设置期待值
     * @param expectedData 期待值
     */
    public void setExpectedData(Data expectedData) {
        this.expectedData = expectedData;
    }

    /**
     * @return 期待值
     */
    public Data getExpectedData() {
        return expectedData;
    }

    @Override
    public void verifyData() {
        if (expectedData == null) {
            //无期待值，不需要验证
            return;
        }
        if (preVerifyData == null) {
            Assert.fail("无操作前值");
        }
        if (postVerifyData == null) {
            Assert.fail("无操作后值");
        }
        verifyDataSelf();
    }

    /**
     * 具体的验证方法
     */
    protected abstract void verifyDataSelf();

}
