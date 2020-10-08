package com.company.fillers;

import com.company.connector.DatabaseCredentials;
import com.company.connector.JSONService;
import com.company.connector.PostgreSQLJDBC;
import com.company.loader.LoaderCsv;
import com.company.loader.LoaderTxt;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DatabaseFiller {
    private final PostgreSQLJDBC database;
    private Connection c;

    public DatabaseFiller(DatabaseCredentials databaseCredentials) {
        this.database = new PostgreSQLJDBC(databaseCredentials);
    }

    public void fillAccountsTypes() throws SQLException {
        c = database.getConnection2();

        insertAccountType(c, "Basic Account");
        insertAccountType(c, "Savings Account");
        insertAccountType(c, "Interest-Bearing Checking Account");
        insertAccountType(c, "Money Market Account");

        database.disconnect();
    }

    public void fillCardsTypes() throws SQLException {
        c = database.getConnection2();

        insertCardType(c, "Credit card");
        insertCardType(c, "Debit card");
        insertCardType(c, "Charge card");
        insertCardType(c, "ATM card");

        database.disconnect();
    }

    public void fillCreditsTypes() throws SQLException {
        c = database.getConnection2();

        insertCreditType(c, "Installment credit");
        insertCreditType(c, "Revolving card");
        insertCreditType(c, "Open card");

        database.disconnect();

    }

    //===================================================
    public void fillTestTypes() throws SQLException {
        c = database.getConnection2();

        insertTestType(c, "in");
        insertTestType(c, "out");

        database.disconnect();
    }

    private void insertTestType(Connection c, String type) {
        final String INSERT_SQL = "INSERT INTO \"Test_Types\" (\"Transaction_Type_Name\") VALUES(?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setString(1, type);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //===================================================

    public void fillTransactionsTypes() throws SQLException {
        c = database.getConnection2();

        insertTransactionType(c, "in");
        insertTransactionType(c, "out");

        database.disconnect();
    }

    public void fillCustomers() throws SQLException {
        LoaderTxt loaderTxt = new LoaderTxt();

        List<String[]> namesList, surnamesList;
        int login;
        String password, firstName, secondName, surname;
        Date birthday;

        loaderTxt.setPath("src/main/resources/first_names.txt");
        namesList = loaderTxt.load();
        loaderTxt.setPath("src/main/resources/last_names.txt");
        surnamesList = loaderTxt.load();

        c = database.getConnection2();

        for (int i = 0; i < 10000; i++) {
            login = generateRandomLogin();
            password = generateRandomPassword();
            firstName = getRandomNameOrSurname(namesList);
            secondName = getRandomNameOrSurname(namesList);
            surname = getRandomNameOrSurname(surnamesList);
            birthday = generateRandomBirthday();

            insertCustomer(c, login, password, firstName, secondName, surname, birthday);
        }

        database.disconnect();
    }

    public void fillCustomersAddresses() throws SQLException {
        LoaderCsv loaderCsv = new LoaderCsv();

        List<String[]> addressesList;
        List<String> citiesList;
        String street, postcode, city, state;
        long customerId;

        loaderCsv.setPath("src/main/resources/addresses.csv");
        addressesList = loaderCsv.load();
        citiesList = loaderCsv.loadCities();

        c = database.getConnection2();

        for (int i = 0; i < 20000; i++) {
            String[] addressArr = getRandomAddress(addressesList);
            street = addressArr[0];
            postcode = addressArr[1];
            city = getRandomCity(citiesList);
            state = city;
            customerId = getRandomIndex(20000);

            insertCustomerAddress(c, street, postcode, city, state, customerId);
        }

        database.disconnect();
    }

    public void fillCards() throws ParseException, SQLException {
        long cardNumber, customerId;
        Date expirationDate;
        short cardTypeId;

        c = database.getConnection2();

        for (int i = 0; i < 31000; i++) {
            cardNumber = generateRandomCardNumber();
            expirationDate = generateRandomBirthday();
            customerId = getRandomIndex(20000);
            cardTypeId = (short) ThreadLocalRandom.current().nextInt(1, 4 + 1);

            insertCard(c, cardNumber, expirationDate, customerId, cardTypeId);
        }

        database.disconnect();
    }

    public void fillAccounts() throws SQLException {
        long balance;
        BigDecimal accountNumber;
        short accountTypeId;

        c = database.getConnection2();

        for (int i = 0; i < 20000; i++) {
            balance = getRandomIndex(10000000);
            accountNumber = BigDecimal.valueOf(generateRandomCardNumber());
            accountTypeId = (short) ThreadLocalRandom.current().nextInt(1, 4 + 1);

            insertAccount(balance, accountNumber, accountTypeId, c);
        }

        database.disconnect();
    }

    public void fillCustomersAccounts() throws SQLException {
        long accountId, customerId;

        c = database.getConnection2();

        for (int i = 0; i < 1102; i++) {
            accountId = getRandomIndex(20004);
            customerId = getRandomIndex(20004);

            insertCustomersAccount(accountId, customerId, c);
        }

        database.disconnect();
    }

    public void fillAccountsCards() throws SQLException {
        long cardId, accountId;

        c = database.getConnection2();

        for (int i = 0; i < 20000; i++) {
            accountId = getRandomIndex(20004);
            cardId = getRandomIndex(20004);

            insertAccountsCard(cardId, accountId, c);
        }

        database.disconnect();
    }

    public void fillCredits() throws SQLException {
        int value;
        BigDecimal interest;
        short creditTypeId;
        long accountId;

        c = database.getConnection2();

        for (int i = 0; i < 19997; i++) {
            value = getRandomIndex(1000000);
            interest = BigDecimal.valueOf(getRandomIndex(1000) / 100);
            creditTypeId = (short) ThreadLocalRandom.current().nextInt(1, 3 + 1);
            accountId = getRandomIndex(20004);

            insertCredit(value, interest, creditTypeId, accountId, c);
        }

        database.disconnect();
    }

    public void fillTransactions() throws SQLException {
        long value, accountIdFrom, accountIdTo, cardIdFrom, cardIdTo;
        Timestamp timestamp;
        short transactionTypeId;

        c = database.getConnection2();

        for (int i = 0; i < 30000; i++) {
            value = getRandomIndex(3000);
            accountIdFrom = getRandomIndex(20004);
            accountIdTo = getRandomIndex(20004);
            cardIdFrom = getRandomIndex(34000);
            cardIdTo = getRandomIndex(34000);
            timestamp = getRandomTimestamp();
            transactionTypeId = (short) ThreadLocalRandom.current().nextInt(1, 2 + 1);

            insertTransaction(value, accountIdFrom, accountIdTo, cardIdFrom, cardIdTo, timestamp, transactionTypeId, c);
        }

        database.disconnect();
    }

    private void insertAccountType(Connection c, String type) {
        final String INSERT_SQL = "INSERT INTO \"Accounts_Types\" (\"Account_Type_Name\") VALUES(?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setString(1, type);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void insertCardType(Connection c, String type) {
        final String INSERT_SQL = "INSERT INTO \"Cards_Types\" (\"Card_Type_Name\") VALUES(?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setString(1, type);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void insertCreditType(Connection c, String type) {
        final String INSERT_SQL = "INSERT INTO \"Credits_Types\" (\"Credit_Type_Name\") VALUES(?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setString(1, type);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void insertTransactionType(Connection c, String type) {
        final String INSERT_SQL = "INSERT INTO \"Transactions_Types\" (\"Transaction_Type_Name\") VALUES(?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setString(1, type);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void insertCustomer(Connection c, int login, String password, String firstName,
                                String secondName, String surname, Date birthday) {
        final String INSERT_SQL = "INSERT INTO \"Customers\" (\"Login\", \"Password\", " +
                "\"First_Name\", \"Second_Name\", \"Surname\", \"Birthday\") " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setInt(1, login);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, secondName);
            ps.setString(5, surname);
            ps.setDate(6, birthday);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertCustomerAddress(Connection c, String street, String postcode,
                                       String city, String state, long customerId) {
        final String INSERT_SQL = "INSERT INTO \"Customers_Addresses\" (\"Street\", \"Postcode\", " +
                "\"City\", \"State\", \"Customer_Id\") " +
                "VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setString(1, street);
            ps.setString(2, postcode);
            ps.setString(3, city);
            ps.setString(4, state);
            ps.setLong(5, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertCard(Connection c, long cardNumber, Date expirationDate, long customerId, short cardTypeId) {
        final String INSERT_SQL = "INSERT INTO \"Cards\" (\"Card_Number\", \"Expiration_Date\", " +
                "\"Customer_Id\", \"Card_Type_Id\") " +
                "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setLong(1, cardNumber);
            ps.setDate(2, expirationDate);
            ps.setLong(3, customerId);
            ps.setShort(4, cardTypeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertAccount(long balance, BigDecimal accountNumber, short accountTypeId, Connection c) {
        final String INSERT_SQL = "INSERT INTO \"Accounts\" (\"Balance\", \"Account_Number\", " +
                "\"Account_Type_Id\") " +
                "VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setLong(1, balance);
            ps.setBigDecimal(2, accountNumber);
            ps.setShort(3, accountTypeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertCustomersAccount(long accountId, long customerId, Connection c) {
        final String INSERT_SQL = "INSERT INTO \"Customers_Accounts\" (\"Account_Id\", \"Customer_Id\")" +
                "VALUES (?, ?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setLong(1, accountId);
            ps.setLong(2, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertAccountsCard(long cardId, long accountId, Connection c) {
        final String INSERT_SQL = "INSERT INTO \"Accounts_Cards\" (\"Card_Id\", \"Account_Id\")" +
                "VALUES (?, ?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setLong(1, cardId);
            ps.setLong(2, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertCredit(int value, BigDecimal interest, short creditTypeId, long accountId, Connection c) {
        final String INSERT_SQL = "INSERT INTO \"Credits\" (\"Value\", \"Interest\", " +
                "\"Credit_Type_Id\", \"Account_Id\") " +
                "VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setInt(1, value);
            ps.setBigDecimal(2, interest);
            ps.setShort(3, creditTypeId);
            ps.setLong(4, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertTransaction(long value, long accountIdFrom, long accountIdTo, long cardIdFrom, long cardIdTo, Timestamp timestamp, short transactionTypeId, Connection c) {
        final String INSERT_SQL = "INSERT INTO \"Transactions\" (\"Value\", \"Transaction_Date\", \"Transaction_Type_Id\", " +
                "\"Account_Id_From\", \"Account_Id_To\", \"Card_Id_From\", \"Card_Id_To\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = c.prepareStatement(INSERT_SQL);
            ps.setLong(1, value);
            ps.setTimestamp(2, timestamp);
            ps.setShort(3, transactionTypeId);
            ps.setLong(4, accountIdFrom);
            ps.setLong(5, accountIdTo);
            ps.setLong(6, cardIdFrom);
            ps.setLong(7, cardIdTo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getRandomNameOrSurname(List<String[]> list) {
        int randomIndex = getRandomIndex(list.size());
        return list.get(randomIndex)[0];
    }

    private int generateRandomLogin() {
        String numbers = "0123456789";
        StringBuilder sb = generateString(numbers);
        return Integer.parseInt(sb.toString());
    }

    private String generateRandomPassword() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = generateString(characters);
        return sb.toString();
    }

    private StringBuilder generateString(String characters) {
        char[] numbersArr = characters.toCharArray();
        StringBuilder sb = new StringBuilder();
        int randomIndex;
        for (int i = 0; i < 8; i++) {
            randomIndex = getRandomIndex(numbersArr.length);
            sb.append(numbersArr[randomIndex]);
        }
        return sb;
    }

    private int getRandomIndex(int max) {
        int randomNum = new Random().nextInt(max);
        return randomNum == 0 ? getRandomIndex(max) : randomNum;
    }

    private Date generateRandomBirthday() {
        Timestamp ts = getRandomTimestamp();
        return Date.valueOf(ts.toString().split(" ")[0]);
    }

    private Timestamp getRandomTimestamp() {
        long ms;
        Random rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (62L * 365 * 24 * 60 * 60 * 1000));
        Date dt = new Date(ms);
        return new Timestamp(dt.getTime());
    }

    private String[] getRandomAddress(List<String[]> list) {
        int randomIndex = getRandomIndex(list.size());
        String[] randomAddress = list.get(randomIndex);
        String[] exactAddress = new String[2];
        String address = randomAddress[1];
        String zipcode = randomAddress[6];
        exactAddress[0] = address;
        exactAddress[1] = zipcode;
        return exactAddress;
    }

    private String getRandomCity(List<String> list) {
        int randomIndex = getRandomIndex(list.size());
        return list.get(randomIndex);
    }

    private long generateRandomCardNumber() {
        return Long.parseLong(generateRandomLogin() + String.valueOf(generateRandomLogin()));
    }
}

