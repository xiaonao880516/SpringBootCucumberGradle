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

    @Override
    public void verifyData(Data expectedData) {
        if (preVerifyData == null) {
            Assert.fail("无操作前值");
        }
        if (postVerifyData == null) {
            Assert.fail("无操作后值");
        }
        if (expectedData == null) {
            Assert.fail("无期待值，无法验证");
        }
        verifyDataSelf(expectedData);
    }

    /**
     * 具体的验证方法
     */
    protected abstract void verifyDataSelf(Data expectedData);

}
