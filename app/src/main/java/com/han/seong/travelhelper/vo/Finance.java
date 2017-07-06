package com.han.seong.travelhelper.vo;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Finance extends RealmObject {
    private String paymentTitle;
    private double price;
    private Date date;
    private RealmList<PaymentInfo> paymentInfo;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentTitle(){
        return paymentTitle;
    }

    public void setPaymentTitle(String paymentTitle){
        this.paymentTitle = paymentTitle;
    }

    public RealmList<PaymentInfo> getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(RealmList<PaymentInfo> paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
