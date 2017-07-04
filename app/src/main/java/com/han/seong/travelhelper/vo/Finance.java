package com.han.seong.travelhelper.vo;

import java.util.Date;

public class Finance {
    private String paymentTitle;
    private double price;
    private Date date;

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
}
