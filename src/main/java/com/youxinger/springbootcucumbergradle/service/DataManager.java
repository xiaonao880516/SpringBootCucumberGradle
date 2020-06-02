package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mengwei
 * 2020/5/28 15:18
 * @version 1.0
 */
@Service("dataManager")
public class DataManager {

    private Global global;

    private SystemUser systemUser;

    private Map<String, Customer> customerMap = new HashMap<>();

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public Map<String, Customer> getCustomerMap() {
        return customerMap;
    }

    public Customer getCustomerByName(String name) {
        return customerMap.get(name);
    }

    public Platform getPlatformById(int id) {
        if (global != null) {
            for (ProvinceOperationCenter provinceOperationCenter : global.getProvinceOperationCenterList()) {
                for (OperationCenter operationCenter : provinceOperationCenter.getOperationCenterList()) {
                    for (Store store : operationCenter.getStoreList()) {
                        for (Platform platform : store.getPlatformList()) {
                            if (platform.getId() == id) {
                                return platform;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public Employee getEmployeeById(int id) {
        if (global != null) {
            for (ProvinceOperationCenter provinceOperationCenter : global.getProvinceOperationCenterList()) {
                for (OperationCenter operationCenter : provinceOperationCenter.getOperationCenterList()) {
                    for (Store store : operationCenter.getStoreList()) {
                        for (Employee employee : store.getEmployeeList()) {
                            if (employee.getId() == id) {
                                return employee;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        if (global != null) {
            for (ProvinceOperationCenter provinceOperationCenter : global.getProvinceOperationCenterList()) {
                for (OperationCenter operationCenter : provinceOperationCenter.getOperationCenterList()) {
                    for (Store store : operationCenter.getStoreList()) {
                        employeeList.addAll(store.getEmployeeList());
                    }
                }
            }
        }
        return employeeList;
    }
}
