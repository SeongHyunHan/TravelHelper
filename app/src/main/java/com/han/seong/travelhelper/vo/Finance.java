package com.han.seong.travelhelper.vo;

import java.util.Date;

import io.realm.RealmObject;

public class Finance extends RealmObject {
    private String paymentTitle;
    private Date date;
    private double price;

    public String getPaymentTitle() {
        return paymentTitle;
    }

    public void setPaymentTitle(String paymentTitle) {
        this.paymentTitle = paymentTitle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
