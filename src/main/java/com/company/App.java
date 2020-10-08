package com.company;

import com.company.connector.DatabaseCredentials;
import com.company.connector.JSONService;
import com.company.fillers.DatabaseFiller;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws IOException, ParseException, SQLException {

        DatabaseCredentials databaseCredentials = new JSONService().readEnvironment2("src/main/resources/environment2.json");
        DatabaseFiller dbFiller = new DatabaseFiller(databaseCredentials);

//        dbFiller.fillAccountsTypes();
//        dbFiller.fillCardsTypes();
//        dbFiller.fillCreditsTypes();
//        dbFiller.fillTransactionsTypes();
        dbFiller.fillTestTypes();
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
