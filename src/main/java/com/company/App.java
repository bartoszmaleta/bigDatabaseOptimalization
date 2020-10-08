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
        String ENVIRONMENT_PATH = "src/main/resources/environment2.json";

//         FILLERS
//        DatabaseFiller dbFiller = new DatabaseFiller(new JSONService().readEnvironment2(ENVIRONMENT_PATH));
//
//        dbFiller.fillAccountsTypes();
//        dbFiller.fillCardsTypes();
//        dbFiller.fillCreditsTypes();
//        dbFiller.fillTransactionsTypes();
//        dbFiller.fillTestTypes();

//        dbFiller.fillCustomers();
//        dbFiller.fillCustomersAddresses();
//        dbFiller.fillCards();
//        dbFiller.fillAccounts();
//        dbFiller.fillCustomersAccounts();
//        dbFiller.fillAccountsCards();
//        dbFiller.fillCredits();
//        dbFiller.fillTransactions();


//         CONTROL
        BankController bankController = new BankController(
                new BankService(ENVIRONMENT_PATH));
        bankController.init();
    }
}
