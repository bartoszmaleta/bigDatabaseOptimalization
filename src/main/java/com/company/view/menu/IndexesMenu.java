package com.company.view.menu;

public class IndexesMenu {
    public static void display() {
        System.out.println("\n\n");
        System.out.println("     ****************************************");
        System.out.println("     *            INDEXES  MENU             *");
        System.out.println("     ****************************************");
        System.out.println("     What do you want to do ?");
        System.out.println("     (1) Display difference between time of selecting customer by firstname when there is index on 'First_Name' column and when there isn't ");
        System.out.println("     (2) Display difference between time of select with and without index on 'Value' column in Transactions table");
        System.out.println("     (3) Display difference between time of selecting card by expiration date when there is index on 'Expiration_Date' column and when there isn't");
        System.out.println("     (0) Exit menu");
        System.out.println();
        System.out.println("Your choice : ");
    }
}
