package com.comapny;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product>{
    private String productId;
    private String productName;
    private double price;


    public Product(){
    }

    public Product(String productId, String productName, double price) {
        this.setProductId(productId);
        this.setProductName(productName);
        this.setPrice(price);
    }

    public void setProductId(String productId){
        this.productId=productId;

    }
    public void setProductName(String name) {
        if (!name.isEmpty())
            this.productName = name;
        else
            throw new IllegalArgumentException("Fill in the name");
    }
    public void setPrice(double p) {
        this.price = p;
    }
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "\nProduct ID: "+getProductId()+"\nProduct Name: "+getProductName()+"\nPrice: "+getPrice()+" PKR\n";
    }

    public int compareTo(Product current) {

        return getProductName().compareTo(current.getProductName());
    }

}
