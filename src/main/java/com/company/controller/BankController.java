package com.company.controller;

import com.company.service.BankService;
import com.company.service.InputTaker;
import com.company.view.LoginMenu;
import com.company.view.TerminalView;

import java.io.FileNotFoundException;

public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    public void init() throws FileNotFoundException {
        boolean isRunning = true;
        TerminalView.displayWelcomeScreen();

        while (isRunning) {
            TerminalView.clearScreen();
            LoginMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
                    displayNumberOfCustomers();
                    break;
                case "2":
//                    displayFiveCustomersWithBiggestBalance();
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private void displayNumberOfCustomers() {
        bankService.showNumberOfCustomers();
    }
}
