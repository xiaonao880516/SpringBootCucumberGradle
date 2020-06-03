package com.youxinger.springbootcucumbergradle.entity.verifydata;

/**
 * @author mengwei
 * 2020/6/2 15:33
 * @version 1.0
 */
public class PlatformVerifyData {
    private float performance = 0.0f;  //平台业绩

    public float getPerformance() {
        return performance;
    }

    public void setPerformance(float performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "EmployeeVerifyData{" +
                "performance=" + performance +
                '}';
    }
}