package com.company.fillers;

import com.company.connector.DatabaseCredentials;
import com.company.connector.JSONService;
import com.company.connector.PostgreSQLJDBC;
import com.company.loader.LoaderCsv;
import com.company.loader.LoaderTxt;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

public class DatabaseFiller {

    public void fillCustomers() throws IOException, ParseException, SQLException {
        LoaderTxt loaderTxt = new LoaderTxt();

        List<String[]> namesList, surnamesList;
        int login;
        String password, firstName, secondName, surname;
        Date birthday;

        JSONService jsonService = new JSONService();
        DatabaseCredentials databaseCredentials = jsonService.readEnvironment();
        PostgreSQLJDBC database = new PostgreSQLJDBC();
        database.connect(databaseCredentials);
        Connection c = database.getConnection();

        loaderTxt.setPath("src/main/resources/first_names.txt");
        namesList = loaderTxt.load();
        loaderTxt.setPath("src/main/resources/last_names.txt");
        surnamesList = loaderTxt.load();

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

        JSONService jsonService = new JSONService();
        DatabaseCredentials databaseCredentials = jsonService.readEnvironment();
        PostgreSQLJDBC database = new PostgreSQLJDBC();
        database.connect(databaseCredentials);
        Connection c = database.getConnection();

        loaderCsv.setPath("src/main/resources/addresses.csv");
        addressesList = loaderCsv.load();
        citiesList = loaderCsv.loadCities();

        for (int i = 0; i < 19998; i++) {
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
        return new Random().nextInt(max);
    }

    private Date generateRandomBirthday() throws ParseException {
        long ms;
        Random rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (62L * 365 * 24 * 60 * 60 * 1000));
        Date dt = new Date(ms);
        Timestamp ts = new Timestamp(dt.getTime());
        return Date.valueOf(ts.toString().split(" ")[0]);
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


//    YAGNI
//
//    private void insert(Object o, String table, String... columns) {
//        final String INSERT_SQL = "INSERT INTO " + table + " (" + createColumns(columns) + ") " +
//                "VALUES (" + createQuestionMarks(columns.length) + ");";
//    }
//
//    private String createColumns(String... variables) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < variables.length; i++) {
//            if (i == variables.length - 1) {
//                sb.append(variables[i]);
//            } else {
//                sb.append(variables[i]).append(", ");
//            }
//        }
//        return sb.toString();
//    }
//
//
//    private String createQuestionMarks(int amount) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < amount; i++) {
//            if (i == amount - 1) {
//                sb.append("?");
//            } else {
//                sb.append("?").append(", ");
//            }
//        }
//        return sb.toString();
//    }
}

