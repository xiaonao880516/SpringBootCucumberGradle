package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void putCustomer(Customer customer){
        global.getCustomerMap().put(customer.getName(), customer);
    }

    public Map<String, Customer> getCustomerMap() {
        return global.getCustomerMap();
    }

    public Customer getCustomerByName(String name) {
        return global.getCustomerMap().get(name);
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

    public List<Employee> getAllEmployees() {
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

    public List<Store> getAllStore() {
        List<Store> storeList = new ArrayList<>();
        if (global != null) {
            for (ProvinceOperationCenter provinceOperationCenter : global.getProvinceOperationCenterList()) {
                for (OperationCenter operationCenter : provinceOperationCenter.getOperationCenterList()) {
                    storeList.addAll(operationCenter.getStoreList());
                }
            }
        }
        return storeList;
    }

    public List<Platform> getAllPlatform() {
        List<Platform> platformList = new ArrayList<>();
        if (global != null) {
            for (ProvinceOperationCenter provinceOperationCenter : global.getProvinceOperationCenterList()) {
                for (OperationCenter operationCenter : provinceOperationCenter.getOperationCenterList()) {
                    for (Store store : operationCenter.getStoreList()) {
                        platformList.addAll(store.getPlatformList());
                    }
                }
            }
        }
        return platformList;
    }
}
