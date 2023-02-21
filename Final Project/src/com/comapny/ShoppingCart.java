package com.comapny;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ShoppingCart extends Customer {
    Scanner sc= new Scanner(System.in);
    double Total;
    ArrayList<ItemToPurchase> cart;
    ArrayList<Product> showData;
    File newfile =new File("Product.txt");
    File file=new File("Cart.txt");

    public ShoppingCart(String customerName) {
        super.setCustomerName(customerName);

        this.cart = new ArrayList<ItemToPurchase>();
        this.showData = new ArrayList<Product>();
//        this.newfile=new File("Product.txt");

    }

    public ArrayList displayFile(File file,ArrayList arrayList){
        try {

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            arrayList= (ArrayList<Product>) objectIn.readObject();  //save data into another arraylist
            objectIn.close();
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return arrayList;
    }

    public ArrayList displayList(ArrayList<Product> show) {
        //print product list which is inserted by admin
        System.out.println("-------------------------");
        System.out.println("*******ITEMS************");
        System.out.println("-------------------------");
        for (int i=0;i<show.size();i++) {
            System.out.println( "Product id: " + show.get(i).getProductId() + "\nProduct Name: " + show.get(i).getProductName() +"\n"+"Price: " + show.get(i).getPrice());
            System.out.println("-------------------------");
        }
        return show;
    }




    public void customerPanel(){
        int choice=-1;
        do {
            System.out.println("1-) Add item to cart");
            System.out.println("2-) Remove item from cart");
            System.out.println("3-) Change item quantity");
            System.out.println("4-) Output shopping cart");
            System.out.println("0-) Logout");
            System.out.println("Choose an option: ");
            choice= Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    showData = displayFile(newfile, showData);
                    //data sorted in
                    Sort sort= new Sort();
                    showData=sort.insertionSort(showData);

                    displayList(showData);
                    cart=addCart(showData,cart);

                    break;
                case 2:
                    removeItem(cart);

                    break;
                case 3:
                    cart=chaneQuantity(cart);

                    break;
                case 4:
                    //data sorted in descending order then print bill
                    Sort sort1=new Sort();
                    cart=sort1.insertionSort_Customer(cart);

                    Total=printBill(cart,file);
                    break;
            }
        }while (choice!=0);

    }

    public ArrayList addCart(ArrayList<Product> show,ArrayList<ItemToPurchase> arrayList) {
        //add to cart function
        if (show.size()!=0) {
            boolean op = true;
            while (op) {
                System.out.println("Enter Product Id: ");
                String productId = sc.nextLine();
                for (int i = 0; i < show.size(); i++) {
                    if (productId.equals(show.get(i).getProductId())) {
                        System.out.println("Enter Quantity: ");
                        int quantity = Integer.parseInt(sc.nextLine());
                        ItemToPurchase purchase = new ItemToPurchase(show.get(i).getProductId(), show.get(i).getProductName(), show.get(i).getPrice(), quantity);
                        arrayList.add(purchase);
                        System.out.println("ITEM ADDED to Cart SUCESSFULLY");
                    }
                }
                System.out.println("DO YOU WANT YO ADD MORE items 1-) yes 0-) NO");
                int op2 = Integer.parseInt(sc.nextLine());
                if (op2 == 0) {
                    op = false;
                }
            }
        }
        else {
            System.out.println("NO items Available");
        }
        return arrayList;
    }

    public void removeItem(ArrayList<ItemToPurchase> show){
        if (show.size()!=0) {
            boolean op = true;
            while (op) {
                System.out.println("Enter Product Id: ");
                String productId = sc.nextLine();
                for (int i = 0; i < show.size(); i++) {
                    if (productId.equals(show.get(i).getProductId())) {
                        System.out.println("-----------------------");
                        System.out.println("DELETED ITEMS");
                        System.out.println("-----------------------");
                        show.get(i).printItemCost();
//                        cartTotal=0;
                        show.remove(i);
                        System.out.println("ITEM Remove to Cart SUCESSFULLY");
                    }
                }
                System.out.println("DO YOU WANT YO ADD Remove items 1-) yes 0-) NO");
                int op2 = Integer.parseInt(sc.nextLine());
                if (op2 == 0) {
                    op = false;
                }
            }
        }
        else {
            System.out.println("Cart Empty !!!!!!");
        }
    }



    public ArrayList<ItemToPurchase> chaneQuantity(ArrayList<ItemToPurchase> show){
        if (show.size()!=0) {
            boolean op = true;
            while (op) {
                System.out.println("Enter Product NAme: ");
                String productName = sc.nextLine();
                for (int i = 0; i < show.size(); i++) {
                    if (productName.equals(show.get(i).getProductName())) {
                        System.out.println("-----------------------");
                        System.out.println("**** ITEM  **** ");
                        System.out.println("-----------------------");
                        show.get(i).printItemCost();
//                        cahnge item Quantity
                        System.out.println("Enter Quantity");
                        int quantity= Integer.parseInt(sc.nextLine());
                        if (quantity>0) {
                            show.get(i).setQuantity(quantity);  //here we Change the qantity in the cart
                            System.out.println("Quantity Changed to Cart SUCESSFULLY");
                        }
                        else {
                            System.out.println("Quantity should be greater than 0 ");
                        }
                    }
                }
                System.out.println("DO YOU WANT YO Change MORE items Quantity 1-) yes 0-) NO");
                int op2 = Integer.parseInt(sc.nextLine());
                if (op2 == 0) {
                    op = false;
                }
            }

        }
        else {
            System.out.println("Cart Empty !!!!!!");
        }
        return show;
    }



    public double printBill(ArrayList<ItemToPurchase> show,File file){
        double cartTotal = 0;
        if (show.size()!=0) {
            System.out.println("        ****************** 'PRINT BILL' ************");
            System.out.println("--------------------------------------------------------------");
            System.out.println("QUANTITY   |    ITEM NAME        |   PRICE     |   TOTAL       ");
            System.out.println("---------------------------------------------------------------");
            for (int i = 0; i < show.size(); i++) {
//                        System.out.println("Product Name: "+cart.get(i).getProductName()+"\nProduct Price: "+cart.get(i).getPrice()+""+cart.get(i).getQuantity());
                cartTotal = cartTotal + show.get(i).printItemCost();
            }
            System.out.println("--------------------------------------------------------------");
            System.out.println("| NO OF ITEMS: " + show.size() + " |" + "\t\t\t\t    | Total Amount: " + cartTotal + "/= |");
            System.out.println("--------------------------------------------------------------");

            try {
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(show);
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("-------------------------------------");
            System.out.println("NO items In Cart!!!!!! ");
            System.out.println("-------------------------------------");
        }


    return cartTotal;}





}
