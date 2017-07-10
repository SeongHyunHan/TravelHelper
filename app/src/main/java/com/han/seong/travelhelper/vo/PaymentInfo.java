package com.han.seong.travelhelper.vo;

import io.realm.RealmObject;

public class PaymentInfo extends RealmObject {
    private String paymentTitle;
    private String paymentType;
    private Double amountPaid;

    public String getPaymentTitle(){
        return paymentTitle;
    }

    public void setPaymentTitle(String paymentTitle){
        this.paymentTitle = paymentTitle;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
}
