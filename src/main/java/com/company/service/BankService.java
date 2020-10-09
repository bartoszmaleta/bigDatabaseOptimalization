package com.company.service;

import com.company.connector.JSONService;
import com.company.connector.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankService {
    private PostgreSQLJDBC database;
    private Connection c;

    public BankService(String path) {
        this.database = new PostgreSQLJDBC(
                new JSONService()
                        .readEnvironment2(path));
    }

    public void showNumberOfCustomers() throws SQLException {
        System.out.println("showNumberOfCustomers");
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
}
