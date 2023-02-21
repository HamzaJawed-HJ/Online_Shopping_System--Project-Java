package com.comapny;

public class ItemToPurchase extends Product{

    private int quantity;

    public ItemToPurchase(String productId, String productName, double price,int quantity){
        super(productId,productName,price);
        this.setQuantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double printItemCost(){
        double quantityCost = (getPrice() * getQuantity());
        System.out.println("--------------------------------------------------------------");
        System.out.println(getQuantity()+"              "+getProductName()+"              "+getPrice()+"          "+quantityCost);
        System.out.println("--------------------------------------------------------------");
        return quantityCost;
    }
    public int compareTo(Product current) {

        return (int) (getPrice()-current.getPrice());
    }


}
