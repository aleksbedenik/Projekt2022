package com.example.userinformation;

import java.util.ArrayList;

public class UserInfoArray {
    ArrayList<UserInfo> userInfos;

    public UserInfoArray(ArrayList<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public ArrayList<UserInfo> getUserInfos() {
        return userInfos;
    }

    @Override
    public String toString() {
        return "UserInfoArray{" +
                "userInfos=" + userInfos +
                '}';
    }
}
