package com.han.seong.travelhelper.vo;

public class Person {
    private String firstName;
    private String lastName;
    private double ownBudget;
    private double balance;


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
}
