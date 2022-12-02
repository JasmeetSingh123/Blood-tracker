package com.rodavid20.withsplashscreen;

public class UserHelperClass2 {
    String RegID,Name,Date_of_Registration,Type,Ngo_URL,Address,State,District,Pan_number,CSR_Number,Nitiaayog,Annual_Report;

    public UserHelperClass2() {
    }

    public UserHelperClass2(String regID, String name, String date_of_Registration, String type, String ngo_URL, String address, String state, String district, String pan_number, String CSR_Number, String nitiaayog, String annual_Report) {
        RegID = regID;
        Name = name;
        Date_of_Registration = date_of_Registration;
        Type = type;
        Ngo_URL = ngo_URL;
        Address = address;
        State = state;
        District = district;
        Pan_number = pan_number;
        this.CSR_Number = CSR_Number;
        Nitiaayog = nitiaayog;
        Annual_Report = annual_Report;
    }

    public String getRegID() {
        return RegID;
    }

    public void setRegID(String regID) {
        RegID = regID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate_of_Registration() {
        return Date_of_Registration;
    }

    public void setDate_of_Registration(String date_of_Registration) {
        Date_of_Registration = date_of_Registration;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNgo_URL() {
        return Ngo_URL;
    }

    public void setNgo_URL(String ngo_URL) {
        Ngo_URL = ngo_URL;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getPan_number() {
        return Pan_number;
    }

    public void setPan_number(String pan_number) {
        Pan_number = pan_number;
    }

    public String getCSR_Number() {
        return CSR_Number;
    }

    public void setCSR_Number(String CSR_Number) {
        this.CSR_Number = CSR_Number;
    }

    public String getNitiaayog() {
        return Nitiaayog;
    }

    public void setNitiaayog(String nitiaayog) {
        Nitiaayog = nitiaayog;
    }

    public String getAnnual_Report() {
        return Annual_Report;
    }

    public void setAnnual_Report(String annual_Report) {
        Annual_Report = annual_Report;
    }
}
