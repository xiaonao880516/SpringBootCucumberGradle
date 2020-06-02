package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.BaseEntity;

/**
 * @author mengwei
 * 2020/5/29 15:30
 * @version 1.0
 * 数据验证接口，所有类型的数据验证都要继承此接口
 */
public interface IVerify<Entity extends BaseEntity, Data> {

    /**
     * 更新操作之前的值
     *
     * @param entity 要验证的实体
     */
    void updatePreVerifyData(Entity entity);

    /**
     * 更新操作之后的值
     *
     * @param entity 要验证的实体
     */
    void updatePostVerifyData(Entity entity);


    /**
     * 数据验证的具体实现方法
     *
     * @param expectedData 期待值
     */
    void verifyData(Data expectedData);
}
