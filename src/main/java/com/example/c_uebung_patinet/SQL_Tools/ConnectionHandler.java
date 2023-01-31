package com.example.c_uebung_patinet.SQL_Tools;


import com.example.c_uebung_patinet.SQL_Model.Patient;

import java.sql.*;

public class ConnectionHandler {
    Connection c = null;
    public void initDatabaseConnection() throws SQLException {
        c = DriverManager.getConnection("jdbc:derby://localhost:1527/Database", "Database", "Database");
    }

    public void closeDatabaseConnection() throws SQLException {
        c.close();
    }

    public void insertPatientInDatabase(Patient p) throws Exception {
        if(c == null) {
            throw new Exception("Connection is not initialized");
        }
        // INSERT INTO PATIENT VALUES (17242, 'Holzinger', 'Alexander', 'Holzinger', 'Ing', NULL, '1999-02-24', 'Wolfseck am Hausruck', 'männlich', 'ledig', 'D', '4600', 'Wels', 'Linzerstraße', '187', '6776558107', 1);
        PreparedStatement ps = c.prepareStatement("INSERT INTO DATABASE.PATIENT VALUES (" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setInt(1, p.getSvnr());
        ps.setString(2, p.getVorname());
        ps.setString(3, p.getNachname());
        ps.setString(4, p.getTitle());
        ps.setString(5, p.getNamenszuatz());

    }
}
