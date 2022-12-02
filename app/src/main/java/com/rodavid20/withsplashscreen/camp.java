package com.rodavid20.withsplashscreen;

public class camp {
    private String name;
    private String address;
    private String mobile;

    public camp(){}
        public camp(String name, String address, String mobile){
            this.name=name;
            this.address = address;
            this.mobile = mobile;
        }
    public String getName()
    {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}



