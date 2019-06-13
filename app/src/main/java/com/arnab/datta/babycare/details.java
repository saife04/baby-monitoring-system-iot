package com.arnab.datta.babycare;

import java.time.LocalDate;

public class details {
    String userName;
    String dadtebirth ;
    String placebirth ;
    int height ;
    int weight ;


    public  details(){

    }

    public details(String dadtebirth, String placebirth, int height, int weight,String name) {
        this.dadtebirth = dadtebirth;
        this.placebirth = placebirth;
        this.height = height;
        this.weight = weight;
        this.userName=name;

    }

    public String getDadtebirth() {
        return dadtebirth;
    }

    public String getPlacebirth() {
        return placebirth;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
