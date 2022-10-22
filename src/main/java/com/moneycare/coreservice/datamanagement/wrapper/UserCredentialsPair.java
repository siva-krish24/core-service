package com.moneycare.coreservice.datamanagement.wrapper;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usercredentials")
public class UserCredentialsPair {
    @Id
    private String key;
    private String value;

    public UserCredentialsPair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public UserCredentialsPair() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
