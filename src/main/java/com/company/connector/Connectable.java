package com.company.connector;

import java.sql.Connection;
import java.sql.SQLException;

public interface Connectable {
    Connection getConnection();
    void connect();
    void disconnect() throws SQLException;
}
