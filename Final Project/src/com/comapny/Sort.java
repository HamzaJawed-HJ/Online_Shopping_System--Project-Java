package com.comapny;

import java.util.ArrayList;

public class Sort {

    public Sort(){

    }

    public ArrayList insertionSort(ArrayList<Product> list) {

        for (int i = 1; i < list.size(); i++) {
            Product current =list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(current) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
        return list;
    }

    public ArrayList insertionSort_Customer(ArrayList<ItemToPurchase> list) {

        for (int i = 1; i < list.size(); i++) {
            ItemToPurchase current =list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(current) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
        return list;
    }


}
