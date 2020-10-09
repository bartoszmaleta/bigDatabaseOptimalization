package com.company.service;

import com.company.connector.JSONService;
import com.company.connector.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BankService {
    private PostgreSQLJDBC database;
    private Connection c;

    public BankService(String path) {
        this.database = new PostgreSQLJDBC(
                new JSONService()
                        .readEnvironment2(path));
    }

    public void showNumberOfCustomers() throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT customers_count();");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customersCount = rs.getInt("customers_count");
                System.out.println("Customers Count = " + customersCount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
    }

    public int showSumOfProvidedNumberCustomersBalances(int numberOfCustomersToSumBalances) throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT richest_balances_sum(?);");
            ps.setString(1, String.valueOf(numberOfCustomersToSumBalances));
            ResultSet rs = ps.executeQuery();
            return rs.getInt("richest_balances_sum");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return 0;
    }

    public String getFirstFiveCustomersInfo() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM customer_information_limit_five;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("ID");
                String firstName = rs.getString("First name");
                String secondName = rs.getString("Second name");
                String surname = rs.getString("Surname");
                String street = rs.getString("Street");
                String city = rs.getString("City");
                String custInfo = String.format("%d %s %s %s %s %s"
                        , customerId
                        , firstName
                        , surname
                        , secondName
                        , street
                        , city);
                sb.append(custInfo).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public String getCardsTypes() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM cards_types;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cardId = rs.getInt("id");
                String cardType = rs.getString("type");
                String cardRecord = String.format("%d %s"
                        , cardId
                        , cardType);
                sb.append(cardRecord).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public String getFiveOldestCustomers() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM five_oldest_customer_information;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("ID");
                String firstName = rs.getString("First name");
                String secondName = rs.getString("Second name");
                String surname = rs.getString("Surname");
                String street = rs.getString("Street");
                String city = rs.getString("City");
                String custInfo = String.format("%d %s %s %s %s %s"
                        , customerId
                        , firstName
                        , surname
                        , secondName
                        , street
                        , city);
                sb.append(custInfo).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public String getFiveYoungestCustomers() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM five_youngest_customer_information;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("ID");
                String firstName = rs.getString("First name");
                String secondName = rs.getString("Second name");
                String surname = rs.getString("Surname");
                String street = rs.getString("Street");
                String city = rs.getString("City");
                String custInfo = String.format("%d %s %s %s %s %s"
                        , customerId
                        , firstName
                        , surname
                        , secondName
                        , street
                        , city);
                sb.append(custInfo).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public String getFiveLastTransactions() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM five_last_transactions_value;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int value = rs.getInt("value");
                String date = String.valueOf(rs.getDate("date"));
                String transaction = String.format("%d %d %s"
                        , id
                        , value
                        , date);
                sb.append(transaction).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }
}
