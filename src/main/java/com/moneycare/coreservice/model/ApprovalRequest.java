package com.moneycare.coreservice.model;

import org.springframework.context.annotation.Bean;

public class ApprovalRequest {
    public UserAuthEntity srcUser;
    public BasicUserEntity targetUser;

    public ApprovalRequest(UserAuthEntity srcUser, BasicUserEntity targetUser) {
        this.srcUser = srcUser;
        this.targetUser = targetUser;
    }

    public ApprovalRequest() {
    }
}
