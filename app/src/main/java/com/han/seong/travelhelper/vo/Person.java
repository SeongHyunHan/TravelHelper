package com.han.seong.travelhelper.vo;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject{

    private int personNo;

    private String firstName;
    private String lastName;
    private double ownBudget;
    private double balance;
    private RealmList<Finance> finance;

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

    public RealmList<Finance> getFinance() {
        return finance;
    }

    public void setFinance(RealmList<Finance> finance) {
        this.finance = finance;
    }

    public int getPersonNo() {
        return personNo;
    }

    public void setPersonNo(int personNo) {
        this.personNo = personNo;
    }
}
