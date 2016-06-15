package com.hwhollywu.muiltiactivitydemo.data;

import java.io.Serializable;

public class User implements Serializable{

    private String userName;
    private String address;

    // shortcut for get/set methods Command+N

    public User(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
