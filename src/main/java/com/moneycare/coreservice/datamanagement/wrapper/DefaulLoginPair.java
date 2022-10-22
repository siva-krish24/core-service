package com.moneycare.coreservice.datamanagement.wrapper;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("default-login-credentials")
public class DefaulLoginPair {
    @Id
    private String key;
    private String value;

    public DefaulLoginPair(String key, String value) {
        this.key = key;
        this.value = value;
    }

//    public DefaulLoginPair(String key, String value) {
//        this.key = key;
//        this.value = value;
//    }

    public DefaulLoginPair() {
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
