package com.company.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC implements Connectable {
    String HOST;
    String PORT;
    String DATABASE;
    String LOGIN;
    String PASSWORD;

    public PostgreSQLJDBC(DatabaseCredentials databaseCredentials) {
        this.HOST = databaseCredentials.getHost();
        this.PORT = databaseCredentials.getPort();
        this.DATABASE = databaseCredentials.getDatabase();
        this.LOGIN = databaseCredentials.getLogin();
        this.PASSWORD = databaseCredentials.getPassword();
    }

    public PostgreSQLJDBC() {
    }

    private Connection c;

    public void connect(DatabaseCredentials databaseCredentials) {
        try {
            this.c = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE, LOGIN, PASSWORD);
            System.out.println("Database connection opened.");
        } catch (SQLException e) {
            System.out.println("Error! Cannot connect with the database.");
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

    public Connection getConnection2() {
        try {
            this.c = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE, LOGIN, PASSWORD);
            System.out.println("Database connection opened.");
            return this.c;
        } catch (SQLException e) {
            System.out.println("Error! Cannot connect with the database.");
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    @Override
    public void connect() {

    }
}
