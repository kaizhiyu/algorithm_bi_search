package com.algorithm.demo.spring;

import com.algorithm.$5_json.JsonMapper;

import java.io.Serializable;

public class User implements Serializable{

    private String userName;
    private Address address;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return JsonMapper.toJson(this);
    }

}