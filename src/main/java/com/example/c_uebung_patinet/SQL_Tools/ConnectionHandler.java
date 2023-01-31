package com.example.c_uebung_patinet.SQL_Tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
    Connection c = null;
    public void initDatabaseConnection() throws SQLException {
        c = DriverManager.getConnection("jdbc:derby://localhost:1527/Database", "Database", "Database");
    }

    public void closeDatabaseConnection() throws SQLException {
        c.close();
    }
}
