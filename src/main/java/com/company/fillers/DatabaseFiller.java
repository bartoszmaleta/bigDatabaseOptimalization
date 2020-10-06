package com.company.fillers;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class DatabaseFiller {

    public void fill() {
        createCustomersEntry();
    }

    private void createCustomersEntry() {
        final String INSERT_SQL = "INSERT INTO customers () " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        BigInteger id;
        String login, password, firstName, secondName, surname, birthday;

    }

    private String generateRandomBirthday() {
        long ms;
        Random rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (62L * 365 * 24 * 60 * 60 * 1000));
        Date dt = new Date(ms);
        Timestamp ts = new Timestamp(dt.getTime());
        return ts.toString().split(" ")[0];
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

