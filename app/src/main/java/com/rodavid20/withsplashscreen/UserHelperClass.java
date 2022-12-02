package com.rodavid20.withsplashscreen;

public class UserHelperClass {

    String name,email,aadhar,mobile,blood,freq,address,orgid;
    public UserHelperClass() {

    }

    public UserHelperClass(String name, String email, String aadhar, String mobile, String blood, String freq, String address, String orgid) {
        this.name = name;
        this.email = email;
        this.aadhar = aadhar;
        this.mobile = mobile;
        this.blood = blood;
        this.freq = freq;
        this.address = address;
        this.orgid = orgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
}
