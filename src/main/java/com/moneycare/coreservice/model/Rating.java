package com.moneycare.coreservice.model;

public class Rating {

    private String userId;
    private String ratingPoints;
    private String ratingMsg;


    public Rating() {
    }

    public Rating(String ratingPoints) {
        this.ratingPoints = ratingPoints;
    }

    public Rating(String ratingPoints, String ratingMsg) {
        this.ratingPoints = ratingPoints;
        this.ratingMsg = ratingMsg;
    }

    public Rating(String userId, String ratingPoints, String ratingMsg) {
        this.userId = userId;
        this.ratingPoints = ratingPoints;
        this.ratingMsg = ratingMsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRatingPoints() {
        return ratingPoints;
    }

    public void setRatingPoints(String ratingPoints) {
        this.ratingPoints = ratingPoints;
    }

    public String getRatingMsg() {
        return ratingMsg;
    }

    public void setRatingMsg(String ratingMsg) {
        this.ratingMsg = ratingMsg;
    }

}
