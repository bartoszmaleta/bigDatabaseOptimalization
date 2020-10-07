package com.company;

import com.company.fillers.DatabaseFiller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        System.out.println("Hello World!");
        DatabaseFiller dbFiller = new DatabaseFiller();

//        dbFiller.fillAccountsTypes();
//        dbFiller.fillCardsTypes();
//        dbFiller.fillCreditsTypes();
//        dbFiller.fillTransactionsTypes();
//
//        dbFiller.fillCustomers();
//        dbFiller.fillCustomersAddresses();
//        dbFiller.fillCards();
//        dbFiller.fillAccounts();
//        dbFiller.fillCustomersAccounts();
//        dbFiller.fillAccountsCards();
//        dbFiller.fillCredits();
//        dbFiller.fillTransactions();

    }
}
