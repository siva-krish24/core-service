package com.moneycare.coreservice.model;

public class BasicUserEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;

    private String transcationId;

    public String getTranscationId() {
        return transcationId;
    }

    public void setTranscationId(String transcationId) {
        this.transcationId = transcationId;
    }

    public BasicUserEntity(String firstName, String lastName, String email, String mobileNo, String transcationId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.transcationId = transcationId;
    }

    public BasicUserEntity(String firstName, String lastName, String mobileNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
    }

    public BasicUserEntity(String firstName, String lastName, String email, String mobileNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public BasicUserEntity() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firtName) {
        this.firstName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
