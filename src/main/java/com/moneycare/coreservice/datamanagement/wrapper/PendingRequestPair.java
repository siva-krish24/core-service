package com.moneycare.coreservice.datamanagement.wrapper;

import com.moneycare.coreservice.model.ApprovalRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pendingrequests")
public class PendingRequestPair {
    @Id
    private String key;
    private ApprovalRequest value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ApprovalRequest getValue() {
        return value;
    }

    public void setValue(ApprovalRequest value) {
        this.value = value;
    }

    public PendingRequestPair() {
    }

    public PendingRequestPair(String key, ApprovalRequest value) {
        this.key = key;
        this.value = value;
    }
}
