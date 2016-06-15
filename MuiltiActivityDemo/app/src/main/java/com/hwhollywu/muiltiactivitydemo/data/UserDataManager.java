package com.hwhollywu.muiltiactivitydemo.data;


public class UserDataManager {

    private static UserDataManager instance = null;

    public static UserDataManager getInstance() {
        if (instance == null) {
            instance = new UserDataManager();
        }

        return instance;
    }

    private User mainUser;

    private UserDataManager() {
    }

    public User getMainUser() {
        return mainUser;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }
}