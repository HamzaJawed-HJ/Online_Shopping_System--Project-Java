package com.comapny;

public class Customer extends User {

    private String customerName;
    private Double phoneNumber;

    public Customer(){
    }

    public Customer(String userId,String password) {
        super.userId = userId;
        super.password = password;
    }

    public Customer(String name, Double phoneNumber){
        setCustomerName(name);
        setPhoneNumber(phoneNumber);
    }

    public void setCustomerName(String customerName) {
            this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }


    public void setPhoneNumber(Double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Double getPhoneNumber() {
        return phoneNumber;
    }

}
