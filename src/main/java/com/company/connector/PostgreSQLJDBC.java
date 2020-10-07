package com.company.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {

    private Connection c;

    public void connect(DatabaseCredentials databaseCredentials) {
        String HOST = databaseCredentials.getHost();
        String PORT = databaseCredentials.getPort();
        String DATABASE = databaseCredentials.getDatabase();
        String LOGIN = databaseCredentials.getLogin();
        String PASSWORD = databaseCredentials.getPassword();

        try {
            this.c = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE, LOGIN, PASSWORD);
            System.out.println("Database connection opened.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void disconnect() throws SQLException {
        this.c.close();
        System.out.println("Database connection closed.");
    }

    public Connection getConnection() {
        return this.c;
    }
}
