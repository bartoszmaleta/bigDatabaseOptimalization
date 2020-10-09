package com.company.controller;

import com.company.service.BankService;
import com.company.service.InputTaker;
import com.company.view.menu.*;
import com.company.view.TerminalView;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    public void init() throws FileNotFoundException, SQLException {
        boolean isRunning = true;
        TerminalView.displayWelcomeScreen();

        while (isRunning) {
            TerminalView.clearScreen();
            MainMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
                    displayViewsMenu();
                    break;
                case "2":
                    displayIndexesMenu();
                    break;
                case "3":
                    displayTriggersMenu();
                    break;
                case "4":
                    displayFunctionsMenu();
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    // ==========================================
    // VIEWS MENU
    private void displayViewsMenu() {
        boolean isRunning = true;
        while (isRunning) {
            TerminalView.clearScreen();
            ViewsMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
                    displayFiveCustomersWithBiggestBalance();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private void displayFiveCustomersWithBiggestBalance() {
        System.out.println("displayFive");
//        bankService.showFirstFiveRichest();
    }

    // ==========================================
    // INDEXES MENU
    private void displayIndexesMenu() {
        boolean isRunning = true;
        while (isRunning) {
            TerminalView.clearScreen();
            IndexesMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
//                    displayTimeOfSelectingCustomerByBalanceWhereBalanceHasIndex();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    // ==========================================
    // TRIGGERS MENU
    private void displayTriggersMenu() {
        boolean isRunning = true;
        while (isRunning) {
            TerminalView.clearScreen();
            TriggersMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    // ==========================================
    // FUNCTIONS MENU
    private void displayFunctionsMenu() throws SQLException {
        boolean isRunning = true;
        while (isRunning) {
            TerminalView.clearScreen();
            FunctionsMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
                    displayNumberOfCustomers();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private void displayNumberOfCustomers() throws SQLException {
        System.out.println("displayNumberOfCustomers");
        bankService.showNumberOfCustomers();
    }
}
