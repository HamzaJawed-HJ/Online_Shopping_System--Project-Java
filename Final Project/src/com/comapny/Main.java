package com.comapny;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("**************** Welcome *********************");
        System.out.println("************ Online Cart System ************");
        int op1;
        do {


            System.out.println("1-)admin panel\n2-)customer");

            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    Admin admin = new Admin("hamza", "123");
                    System.out.println("Enter User Id: ");
                    String us=sc.nextLine();
                    System.out.println("Enter Password: ");
                    String pas=sc.nextLine();

                    if (admin.userId.equals(us) && admin.password.equals(pas)) {
                        System.out.println("Welcome to Admin Panel");
                        AdminPanel add = new AdminPanel();
                        try {
                            add.Admin();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        System.out.println("\nINVALID USER ID AND PASSWORD");
                    }
                    break;

                case 2:
                    Customer customer = new Customer("anas", "1234");

                    System.out.println("Enter User Id: ");
                    String us2=sc.nextLine();
                    System.out.println("Enter Password: ");
                    String pas2=sc.nextLine();

                    if (customer.userId.equals(us2) && customer.password.equals(pas2)) {
                        System.out.println(" WELCOME TO Store ");
                        ShoppingCart sp= new ShoppingCart(customer.getCustomerName());
                        sp.customerPanel();
                    }
                    else {
                        System.out.println("INVALID USER ID AND PASSWORD");
                    }
            }

            System.out.println("1 -) Continue  0-) Exit ");
            op1= Integer.parseInt(sc.nextLine());
        }while (op1==1);
    }



}
