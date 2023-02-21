package com.comapny;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class AdminPanel extends Product{

    ArrayList<Product> Productlist;  // ArrayList is a dynamic  data structure
    public File file;

    public AdminPanel(){

        this.Productlist=new ArrayList<Product>();
        file=new File("Product.txt");
    }
    public AdminPanel(String productId, String productName, double price){
        super(productId,productName,price);

    }

    public void Admin() throws Exception{
        Scanner sc=new Scanner(System.in);

        ListIterator list=null;

        FileExists(file);
//        Sort sort=new Sort();
//        Productlist=sort.insertionSort(Productlist);
        int choice=-1;
        do {
            System.out.println("-------------------------");
            System.out.println("1-) Insert");
            System.out.println("2-) Display");
            System.out.println("3-) Search");
            System.out.println("4-) Delete");
            System.out.println("5-) Update");
            System.out.println("0-) Logout");
            System.out.println("-------------------------");
            System.out.println("Enter choice: ");
            choice= Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    Productlist=createProduct(file,Productlist);
                    break;
                case 2:
                    if (FileExists(file)) {

                        displayProduct(list);
                    }
                    else {
                        System.out.println("List Does Not Exists");
                    }
                    break;
                case 3:
                    if (FileExists(file)) {
                        searchProduct(list,Productlist);
                    }
                    else {
                        System.out.println("List Does Not Exists");
                    }
                    break;
                case 4:
                    if (FileExists(file)) {
                        Productlist=deleteProduct(list,file,Productlist);
                    }
                    else {
                        System.out.println("List Does Not Exists");
                    }
                    break;
                case 5:
                    if (FileExists(file)) {
                        Productlist=updateProduct(list,file,Productlist);
                    }
                    else {
                        System.out.println("List Does Not Exists");
                    }
                    break;
            }
        }while (choice!=0);
    }

    public boolean FileExists(File file)throws Exception{
        if (file.isFile()){
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
//            Sort sort=new Sort();
//            Productlist=sort.insertionSort(Productlist);
            Productlist=(ArrayList<Product>)ois.readObject();
            ois.close();
            return true;
        }
        return false;
    }

    public ArrayList createProduct(File file,ArrayList arrayList) throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter HOW MANY Product you want to add");
        int n= Integer.parseInt(sc.nextLine());
        for (int i=1;i<=n;i++) {
            System.out.println("-------------------------------");
            System.out.println("-------------Product "+i+" Data------------");
            System.out.println("Enter Product Name: ");
            String productName = sc.nextLine();
            System.out.println("Enter Price: ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.println("ENTER Product ID: ");
            String productId = sc.nextLine();
            productId = productId + productName.substring(0, 3);
            System.out.println("---------------------------------------");

            AdminPanel product = new AdminPanel(productId, productName, price);
            arrayList.add(product);
        }
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(arrayList);
        oos.close();

        return arrayList;
    }

    public void displayProduct(ListIterator li)throws Exception{
        System.out.println("-------------------------");
        System.out.println("*******RECORDS************");
        System.out.println("-------------------------");


        li=Productlist.listIterator();
        while (li.hasNext())
            System.out.println(li.next());
        System.out.println("--------------------------");
    }
    public void searchProduct(ListIterator li,ArrayList arrayList)throws Exception{
        Scanner sc= new Scanner(System.in);
        boolean found =false;
        System.out.println("Enter Product Name: ");
        String name= sc.nextLine();
        System.out.println("-------------------------");
        li=arrayList.listIterator();
        while (li.hasNext()) {
            Product p = (Product)li.next();
            if (p.getProductName().equals(name)) {
                System.out.println("*******Record Found******");
                System.out.println("-------------------------");
                System.out.println(p);
                System.out.println("-------------------------");
                found=true;
            }
        }
        System.out.println("--------------------------");
        if (!found){
            System.out.println("Record Not Found....");
        }

    }

    public ArrayList deleteProduct(ListIterator li,File file,ArrayList arrayList)throws Exception{

        displayProduct(li);
        Scanner sc= new Scanner(System.in);
        boolean found =false;
        System.out.println("Enter Product ID to Delete: ");
        String id= sc.nextLine();
        li=arrayList.listIterator();
        while (li.hasNext()) {
            Product p = (Product)li.next();
            if (p.getProductId().equals(id)) {
                li.remove();
                found=true;
            }
        }
        if (found){
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(arrayList);
            oos.close();
            System.out.println("---------------------------------------");
            System.out.println("*******Record Deleted Sucessfully****");
            System.out.println("---------------------------------------");
        }
        else {
            System.out.println("Record Not Found....!!!!!");
            System.out.println("-------------------------");
        }
        return arrayList;
    }


    public ArrayList updateProduct(ListIterator li,File file,ArrayList<Product> arrayList)throws Exception{
        displayProduct(li);
        Scanner sc= new Scanner(System.in);
        boolean found =false;
        System.out.println("Enter Product ID to Update: ");
        String id= sc.nextLine();
        li=arrayList.listIterator();
        while (li.hasNext()) {
            Product p = (Product)li.next();  //create an object of product class and file is typeCasted by an objecttreverse to the file
            if (p.getProductId().equals(id)) {
                        System.out.println("Enter New Price: ");
                        double price = Double.parseDouble(sc.nextLine());
                        AdminPanel a = new AdminPanel(p.getProductId(),p.getProductName(), price);
                        li.set(a);
                        found=true;
            }
        }
        if (found){
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(arrayList);
            oos.close();
            System.out.println("---------------------------------------");
            System.out.println("*******Record Updated Sucessfully******");
            System.out.println("---------------------------------------");
        }
        else {
            System.out.println("Record Not Found....!!!!!");
            System.out.println("-------------------------");
        }
        return arrayList;
    }
}
