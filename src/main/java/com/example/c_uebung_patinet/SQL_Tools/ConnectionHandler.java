package com.example.c_uebung_patinet.SQL_Tools;


import com.example.c_uebung_patinet.SQL_Model.Land;
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

    /**
     *
     * TODO: Add a check if the patient already exists, then modify them in the database
     * @param p Patient to be added to Database
     * @return the number of lines effected in the Database
     * @throws Exception when an SQLException occurs or the connection with the database is not initialised
     */
    public int insertPatientInDatabase(Patient p) throws Exception {
        if(c == null) {
            throw new Exception("Connection is not initialized");
        }
        // INSERT INTO PATIENT VALUES (17242, 'Holzinger', 'Alexander', 'Holzinger', 'Ing', NULL, '1999-02-24', 'Wolfseck am Hausruck', 'männlich', 'ledig', 'D', '4600', 'Wels', 'Linzerstraße', '187', '6776558107', 1);
        PreparedStatement ps = c.prepareStatement("INSERT INTO DATABASE.PATIENT VALUES (" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setInt(1, p.getSvnr());
        ps.setString(2, p.getVorname());
        ps.setString(3, p.getNachname());
        ps.setString(4, p.getGeburtsname());
        ps.setString(5, p.getTitle());
        ps.setString(6, p.getNamenszuatz());
        ps.setDate(7, (Date)p.getGeburtsdatum());
        ps.setString(8, p.getGeschlecht());
        ps.setString(9, p.getFamilienstand());
        ps.setString(10, p.getStaatsangehörigkeit().getKuerzel());
        ps.setString(11, p.getPostleitzahl());
        ps.setString(12, p.getHausnr());
        ps.setString(13, p.getTel());
        ps.setInt(14, p.getReligionszugehörigkeit().getId());
        return ps.executeUpdate();
    }

    /**
     * TODO: add a check if the country already exists and if it is already there do not execute
     * @param l The country to be inserted into the database
     * @return the number of lines effected in the database
     * @throws Exception when an SQLException occurs or the connection with the database is not initialised
     */
    public int insertCountryIntoDatabase(Land l) throws Exception {
        if(c == null) {
            throw  new Exception("Connection is not initialized");
        }
        PreparedStatement ps = c.prepareStatement("INSERT INTO DATABASE.LAND VALUES(?, ?, ?)");
        ps.setString(1, l.getKuerzel());
        ps.setString(2, l.getName());
        ps.setString(3, l.getVorwahl());
        return ps.executeUpdate();
    }
}
