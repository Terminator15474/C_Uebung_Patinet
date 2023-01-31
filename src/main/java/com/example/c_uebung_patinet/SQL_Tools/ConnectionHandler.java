package com.example.c_uebung_patinet.SQL_Tools;


import com.example.c_uebung_patinet.SQL_Model.Land;
import com.example.c_uebung_patinet.SQL_Model.Patient;
import com.example.c_uebung_patinet.SQL_Model.Religion;

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

        public void selectPatientId (int svnr) throws SQLException
        {
            PreparedStatement prs = c.prepareStatement("SELECT * from Patient where id = svnr");
            ResultSet rs = prs.executeQuery();
            Patient pat = new Patient();
            while (rs.next()){
                pat.setSvnr(rs.getInt(1));
                pat.setVorname(rs.getString(2));
                pat.setNachname(rs.getString(3));
                pat.setGeburtsname(rs.getString(4));
                pat.setTitle(rs.getString(5));
                pat.setNamenszuatz(rs.getString(6));
                pat.setGeburtsdatum(rs.getDate(7));
                pat.setGeburtsort(rs.getString(8));
                pat.setGeschlecht(rs.getString(9));
                pat.setFamilienstand(rs.getString(10));
                Land l = new Land();
                l.setVorwahl(rs.getString(11));
                l.setName(rs.getString(12));
                l.setKuerzel(rs.getString(13));
                pat.setPostleitzahl(rs.getString(14));
                pat.setOrt(rs.getString(15));
                pat.setStrasse(rs.getString(16));
                pat.setHausnr(rs.getString(17));
                pat.setTel(rs.getString(18));
                Religion rel = new Religion();
                rel.setId(rs.getInt(19));
                rel.setName(rs.getString(20));
                c.close();
            }
    }
}
