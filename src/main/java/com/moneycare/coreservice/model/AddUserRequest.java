package com.moneycare.coreservice.model;

public class AddUserRequest {
    public UserAuthEntity srcUser;
    public BasicUserEntity targetUser;

    public AddUserRequest() {
    }

    public AddUserRequest(UserAuthEntity srcUser, BasicUserEntity targetUser) {
        this.srcUser = srcUser;
        this.targetUser = targetUser;
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
                "srcUser=" + srcUser.toString() +
                ", targetUser=" + targetUser.toString() +
                '}';
    }
}
