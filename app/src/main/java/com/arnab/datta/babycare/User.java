package com.arnab.datta.babycare;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
        public   String babyname ;
        public   String mothername ;
        public   String fathername ;
        public   String username ;
        public   String password ;
        public   String email ;
        public   String mobilenumber ;



        public User(){

        }

        public User(String babyname, String mothername, String fathername, String username, String password, String email, String mobilenumber) {
            this.babyname = babyname;
            this.mothername = mothername;
            this.fathername = fathername;
            this.username = username;
            this.password = password;
            this.email = email;
            this.mobilenumber = mobilenumber;
        }

        public String getBabyname() {
            return babyname;
        }

        public String getMothername() {
            return mothername;
        }

        public String getFathername() {
            return fathername;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public void setBabyname(String babyname) {
            this.babyname = babyname;
        }

        public void setMothername(String mothername) {
            this.mothername = mothername;
        }

        public void setFathername(String fathername) {
            this.fathername = fathername;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setMobilenumber(String mobilenumber) {
            this.mobilenumber = mobilenumber;
        }

        public String getMobilenumber() {
            return mobilenumber;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}


