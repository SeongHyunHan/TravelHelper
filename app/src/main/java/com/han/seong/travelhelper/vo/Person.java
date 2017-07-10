package com.han.seong.travelhelper.vo;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject{
    private String firstName;
    private String lastName;
    private double ownBudget;
    private double balance;
    private double spent;
    private RealmList<PaymentInfo> paymentInfo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getOwnBudget() {
        return ownBudget;
    }

    public void setOwnBudget(double ownBudget) {
        this.ownBudget = ownBudget;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }

    public RealmList<PaymentInfo> getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(RealmList<PaymentInfo> paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
