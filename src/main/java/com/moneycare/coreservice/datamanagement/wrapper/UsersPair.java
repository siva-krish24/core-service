package com.moneycare.coreservice.datamanagement.wrapper;

import com.moneycare.coreservice.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class UsersPair {
    @Id
    private String key;
    private User value;

    public UsersPair(String key, User value) {
        this.key = key;
        this.value = value;
    }

    public UsersPair() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getValue() {
        return value;
    }

    public void setValue(User value) {
        this.value = value;
    }
}
