package com.example.userinformation;

import java.util.ArrayList;

//import javax.jws.soap.SOAPBinding;

public class UserActivitiesArray {
    private ArrayList<UserActivities> arrayList;

    public UserActivitiesArray() {
        this.arrayList = new ArrayList<UserActivities>();
    }
    public void AddToArray(UserActivities userActivities){
        arrayList.add(userActivities);
    }


    public void setArrayList(ArrayList<UserActivities> arrayList) {
        this.arrayList = arrayList;
    }


    public ArrayList<UserActivities> getArrayList() {
        return arrayList;
    }

    @Override
    public String toString() {
        return "UserActivitiesArray{" +
                "arrayList=" + arrayList +
                '}';
    }
}
