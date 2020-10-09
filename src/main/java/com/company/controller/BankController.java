package com.company.controller;

import com.company.service.BankService;
import com.company.service.InputTaker;
import com.company.view.FunctionsView;
import com.company.view.IndexesView;
import com.company.view.ViewsView;
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
    private void displayViewsMenu() throws SQLException {
        boolean isRunning = true;
        while (isRunning) {
            TerminalView.clearScreen();
            ViewsMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
                    displayFiveCustomersInfo();
                    break;
                case "2":
                    displayCardTypes();
                    break;
                case "3":
                    displayLastFiveTransactionsInfo();
                    break;
                case "4":
                    displayFiveYoungestCustomers();
                    break;
                case "5":
                    displayFiveOldestCustomers();
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private void displayLastFiveTransactionsInfo() throws SQLException {
        String transactions = bankService.getFiveLastTransactions();
        ViewsView.printLastFiveTransactions(transactions);
    }

    private void displayFiveYoungestCustomers() throws SQLException {
        String customers = bankService.getFiveYoungestCustomers();
        ViewsView.printFiveYoungestCustomers(customers);
    }

    private void displayFiveOldestCustomers() throws SQLException {
        String customers = bankService.getFiveOldestCustomers();
        ViewsView.printFiveOldestCustomers(customers);
    }

    private void displayCardTypes() throws SQLException {
        String cardsTypes = bankService.getCardsTypes();
        ViewsView.printCardsTypes(cardsTypes);
    }

    private void displayFiveCustomersInfo() throws SQLException {
        String customersInfo = bankService.getFirstFiveCustomersInfo();
        ViewsView.printCustsInfo(customersInfo);
    }

    // ==========================================
    // INDEXES MENU
    private void displayIndexesMenu() throws SQLException {
        boolean isRunning = true;
        while (isRunning) {
            TerminalView.clearScreen();
            IndexesMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
                    displayTimeOfSelectingCustomerByFirstNameWithAndWithoutIndex();
                    break;
                case "2":
                    displayTimeOfSelectingTransactionByValueWithAndWithoutIndex();
                    break;
                case "3":
                    displayTimeOfSelectingCardByExpirationDateWithAndWithoutIndex();
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

    private void displayTimeOfSelectingCardByExpirationDateWithAndWithoutIndex() throws SQLException {
        String selectCardInfo = bankService.getCardExplainInfo();
        IndexesView.printSelectInfos(selectCardInfo);
    }

    private void displayTimeOfSelectingTransactionByValueWithAndWithoutIndex() throws SQLException {
        String selectsInfos = bankService.getTransactionsExplainInfo();
        IndexesView.printSelectInfos(selectsInfos);
    }

    private void displayTimeOfSelectingCustomerByFirstNameWithAndWithoutIndex() throws SQLException {
        String selectsInfos = bankService.getSelectsInfo();
        IndexesView.printSelectInfos(selectsInfos);
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
                    displayCountOfTransactionsWhereValueGreaterThanProvidedValue();
                    break;
                case "3":
                    displayAverageBalance();
                    break;
                case "4":
                    displayMaximumAndMinimumBalance();
                    break;
                case "5":
                    displayAverageTransactionValue();
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private void displayCountOfTransactionsWhereValueGreaterThanProvidedValue() throws SQLException {
        int valueProvided = InputTaker.takeIntInputWithMessage("Enter value: ");
        int count = bankService.getCountOfTransactionsWhereValueGreaterThanProvidedValue(valueProvided);
        FunctionsView.printCount(count);
    }

    private void displayAverageTransactionValue() throws SQLException {
        int value = bankService.getAverageTransactionValue();
        FunctionsView.printValue(value);
    }

    private void displayMaximumAndMinimumBalance() throws SQLException {
        String balances = bankService.getMaxAndMinBalances();
        FunctionsView.printBalances(balances);
    }

    private void displayAverageBalance() throws SQLException {
        int averageBalance = bankService.getAverageBalance();
        FunctionsView.printBalance(averageBalance);
    }

    // TODO:
    private void displaySumOfTopNumberProviedeRichestCustomersBalances() throws SQLException {
        int numberOfCustomersToSumBalances = InputTaker.takeIntInputWithMessage("How many customers balances You want to sum?");
        int result = bankService.showSumOfProvidedNumberCustomersBalances(numberOfCustomersToSumBalances);
        FunctionsView.printSumOfTopRichestCustomersBalances(result);
    }

    private void displayNumberOfCustomers() throws SQLException {
        bankService.showNumberOfCustomers();
    }
}
