package com.salmon.spicysalmon.models;

import com.salmon.spicysalmon.controllers.ApplicationController;
import com.salmon.spicysalmon.controllers.CustomerController;
import com.salmon.spicysalmon.models.Customer;

public class BankAccountApplication extends Application{
    private final Customer requestee;
    private String accountName;
    private Boolean isApproved;

    public BankAccountApplication(Customer customer, String accountName){
        this.requestee = customer;
        this.accountName = accountName;
        this.isApproved = null;
    }
    public String getApprovalStatus(){
        if (this.isApproved == null)
            return "Application for account: " + this.accountName + " is pending.";
        if (this.isApproved)
            return "Application for account: " + this.accountName + " has been approved.";
        else
            return "Application for account: " + this.accountName + " has been denied.";
    }
    public void approveApplication() {
        this.isApproved = true;
    }
    public void denyApplication(){
        this.isApproved = false;
    }
    public Customer getRequestee(){
        return this.requestee;
    }
    public String getAccountName(){
        return this.accountName;
    }
    public String toString(){
        return  this.requestee.getFirstName() + this.requestee.getLastName() + "\n"
                + this.requestee.getSocialSecurityNumber() + "\n"
                + this.accountName + "\n"
                + getApprovalStatus();
    }
}

