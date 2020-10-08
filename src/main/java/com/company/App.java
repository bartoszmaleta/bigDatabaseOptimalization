package com.company;

import com.company.connector.DatabaseCredentials;
import com.company.connector.JSONService;
import com.company.controller.BankController;
import com.company.fillers.DatabaseFiller;
import com.company.service.BankService;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws IOException, ParseException, SQLException {

        DatabaseFiller dbFiller = new DatabaseFiller(new JSONService().readEnvironment2("src/main/resources/environment2.json"));

//        dbFiller.fillAccountsTypes();
//        dbFiller.fillCardsTypes();
//        dbFiller.fillCreditsTypes();
//        dbFiller.fillTransactionsTypes();
//        dbFiller.fillTestTypes();
//
//        dbFiller.fillCustomers();
//        dbFiller.fillCustomersAddresses();
//        dbFiller.fillCards();
//        dbFiller.fillAccounts();
//        dbFiller.fillCustomersAccounts();
//        dbFiller.fillAccountsCards();
//        dbFiller.fillCredits();
//        dbFiller.fillTransactions();


        BankController bankController = new BankController(
                new BankService("src/main/resources/environment2.json"));
    }
}
