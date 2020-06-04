package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.CustomerVerify;

/**
 * @author mengwei
 * 2020/5/22 15:44
 * @version 1.0
 */
public class Customer extends BaseEntity{

    private String member_number;  // 会员编号
    private String name;  //姓名
    private String phone; //手机号
    private String sex;//性别
    private String birthday;  //生日
    private String openid; //微信openid
    private String address;  //收货地址
    private String area; //收货地区
    private String city;//收货城市
    private String province;  //收货省
    private String consignee;  //收货人

    private Employee employee = null;  //会员专属顾问
    private Platform platform = null;  //会员所属平台
    private Store store = null;//会员所属门店

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.sex = "F";
        this.birthday = "1996-01-10";
        this.openid = "customer" + phone;
        this.address = "林河大街17号";
        this.area = "顺义区";
        this.city = "北京";
        this.province = "北京市";
        this.consignee = name;
        this.verify = new CustomerVerify(name);
    }


    public String getMember_number() {
        return member_number;
    }

    public void setMember_number(String member_number) {
        this.member_number = member_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "member_number='" + member_number + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", openid='" + openid + '\'' +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", consignee='" + consignee + '\'' +
                ", employee=" + employee +
                ", platform=" + platform +
                ", store=" + store +
                '}';
    }
}
