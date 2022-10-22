package com.moneycare.coreservice.datamanagement.wrapper;

import com.moneycare.coreservice.model.ApprovalWithdrawRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pendingwithdrwalrequest")
public class PendingWithdrawlRequestPair {
    @Id
    private String key;
    private ApprovalWithdrawRequest value;

    public PendingWithdrawlRequestPair(String key, ApprovalWithdrawRequest value) {
        this.key = key;
        this.value = value;
    }

    public PendingWithdrawlRequestPair() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ApprovalWithdrawRequest getValue() {
        return value;
    }

    public void setValue(ApprovalWithdrawRequest value) {
        this.value = value;
    }
}
