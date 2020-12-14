package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.service.DataManager;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author mengwei
 * 2020/5/28 16:23
 * @version 1.0
 */
public class BaseSteps {

    @Resource
    protected DataManager dataManager;

    /**
     * 缓存操作前数据
     *
     */
    protected void cachePreVerifyData() {
        dataManager.getGlobal().updatePreVerifyData();
    }

    /**
     * 缓存操作后数据
     *
     */
    protected void cachePostVerifyData() throws Throwable {
        //等待10秒队列执行时间，获取操作数据过快会导致队列没有执行完成数据不正确
        TimeUnit.SECONDS.sleep(10);
        dataManager.getGlobal().updatePostVerifyData();
    }

}
