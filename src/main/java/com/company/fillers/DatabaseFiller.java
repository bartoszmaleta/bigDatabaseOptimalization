package com.company.fillers;

import com.company.loader.LoaderCsv;
import com.company.loader.LoaderTxt;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DatabaseFiller {

    public void fillCustomers() throws IOException {
        LoaderCsv loaderCsv = new LoaderCsv();
        LoaderTxt loaderTxt = new LoaderTxt();

        List<String[]> namesList, surnamesList, addressesList;

        loaderCsv.setPath("src/main/resources/addresses.csv");
        addressesList = loaderCsv.load();
        loaderTxt.setPath("src/main/resources/first_names.txt");
        namesList = loaderTxt.load();
        loaderTxt.setPath("src/main/resources/last_names.txt");
        surnamesList = loaderTxt.load();

        String login = generateRandomLogin();
        String password = generateRandomPassword();
        String firstName = getRandomNameOrSurname(namesList);
        String secondName = getRandomNameOrSurname(namesList);
        String surname = getRandomNameOrSurname(surnamesList);
        String birthday = generateRandomBirthday();

        final String INSERT_SQL = "INSERT INTO \"Customers\" (\"Login\", \"Password\", " +
                "\"First_Name\", \"Second_Name\", \"Surname\", \"Birthday\") " +
                "VALUES (?, ?, ?, ?, ?, ?);";


    }

    private String getRandomNameOrSurname(List<String[]> list) {
        int randomIndex = getRandomIndex(list.size());
        return list.get(randomIndex)[0];
    }

    private String generateRandomLogin() {
        String numbers = "0123456789";
        StringBuilder sb = generateString(numbers);
        return sb.toString();
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


    private String generateRandomBirthday() {
        long ms;
        Random rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (62L * 365 * 24 * 60 * 60 * 1000));
        Date dt = new Date(ms);
        Timestamp ts = new Timestamp(dt.getTime());
        return ts.toString().split(" ")[0];
    }

    private int getRandomIndex(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
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

    private void createCustomersEntry() {
        final String INSERT_SQL = "INSERT INTO customers () " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        BigInteger id;
        String login, password, firstName, secondName, surname, birthday;

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

