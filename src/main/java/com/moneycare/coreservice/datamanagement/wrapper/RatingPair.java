package com.moneycare.coreservice.datamanagement.wrapper;

import com.moneycare.coreservice.model.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ratings")
public class RatingPair {
    @Id
    private String key;
    private Rating value;

    public RatingPair(String key, Rating value) {
        this.key = key;
        this.value = value;
    }

    public RatingPair() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Rating getValue() {
        return value;
    }

    public void setValue(Rating value) {
        this.value = value;
    }
}
