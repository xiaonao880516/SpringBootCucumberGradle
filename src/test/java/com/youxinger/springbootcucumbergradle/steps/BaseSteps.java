package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.service.DataManager;

import javax.annotation.Resource;

/**
 * @author mengwei
 * 2020/5/28 16:23
 * @version 1.0
 */
public class BaseSteps {

    @Resource
    protected DataManager dataManager;
}
