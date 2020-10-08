package com.company.service;

import com.company.connector.JSONService;
import com.company.connector.PostgreSQLJDBC;

import java.sql.Connection;

public class BankService {
    private PostgreSQLJDBC database;
    private Connection c;

    public BankService(String path) {
        this.database = new PostgreSQLJDBC(
                new JSONService()
                        .readEnvironment2(path));
    }

    public void showNumberOfCustomers() {
        c = this.database.getConnection2();

    }
}
