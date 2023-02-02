package com.example.c_uebung_patinet.SQL_Tools;


import com.example.c_uebung_patinet.SQL_Model.Land;
import com.example.c_uebung_patinet.SQL_Model.Patient;
import com.example.c_uebung_patinet.SQL_Model.Religion;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConnectionHandler {
    Connection c = null;

    /**
     * Initialised the Connection to the Database
     * @throws SQLException when the Connection to the local Database fails for some reason
     */
    public ConnectionHandler() throws SQLException {
        c = DriverManager.getConnection("jdbc:derby://localhost:1527/Database", "Database", "Database");
    }

    /**
     * Closes the Database connection (REQUIRED!)
     * @throws SQLException when the close fails for any reason
     */
    public void closeDatabaseConnection() throws SQLException {
        c.close();
    }

    /**
     * This method adds a Patient to the Database if it doesn't already exist in the database, when it already exists the entry is updated
     *
     * @param p Patient to be added to / updated on Database
     * @return the number of lines effected in the Database
     * @throws Exception when an SQLException occurs or the connection with the database is not initialised
     */
    public int insertPatientInDatabase(Patient p) throws Exception {
        if(c == null) {
            throw new Exception("Connection is not initialized");
        }
        boolean isUpdate = false;
        PreparedStatement ps = null;
        if(selectPatientId(p.getSvnr()) != null) {
            ps = c.prepareStatement("UPDATE DATABASE.PATIENT SET " +
                    "SVNR = ?, VN = ?, NN = ?, GN = ?, TITEL = ?, NAMENSZUSATZ = ?, GEBDATUM = ?, GEBORT = ?, GESCHLECHT = ?," +
                    "FAMILIENSTAND = ?, STAATKUERZEL = ?, PLZ = ?, ORT = ?, STR = ?, HAUSNR = ?, TEL = ?, RELID = ? WHERE SVNR = ?");
            isUpdate = true;
        } else {
            // INSERT INTO PATIENT VALUES (17242, 'Holzinger', 'Alexander', 'Holzinger', 'Ing', NULL, '1999-02-24', 'Wolfseck am Hausruck', 'männlich', 'ledig', 'D', '4600', 'Wels', 'Linzerstraße', '187', '6776558107', 1);
            ps = c.prepareStatement("INSERT INTO DATABASE.PATIENT VALUES (" +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        }
        ps.setInt(1, p.getSvnr());
        ps.setString(2, p.getVorname());
        ps.setString(3, p.getNachname());
        ps.setString(4, p.getGeburtsname());
        ps.setString(5, p.getTitle());
        ps.setString(6, p.getNamenszuatz());
        ps.setDate(7, new java.sql.Date(p.getGeburtsdatum().getTime()));
        ps.setString(8, p.getGeburtsort());
        ps.setString(9, p.getGeschlecht());
        ps.setString(10, p.getFamilienstand());
        ps.setString(11, p.getStaatsangehörigkeit().getKuerzel());
        ps.setString(12, p.getPostleitzahl());
        ps.setString(13, p.getOrt());
        ps.setString(14, p.getStrasse());
        ps.setString(15, p.getHausnr());
        ps.setString(16, p.getTel());
        ps.setInt(17, p.getReligionszugehörigkeit().getId());
        if(isUpdate) {
            ps.setInt(18, p.getSvnr());
        }
        return ps.executeUpdate();
    }

    /**
     * Returns a List of all
     * @return
     * @throws Exception
     */
    public List<Patient> selectPatients() throws Exception {
        LinkedList<Patient> patients = new LinkedList<>();
        ResultSet rs = c.createStatement().executeQuery("SELECT DISTINCT * FROM DATABASE.GET_ALL_PATIENTS");
        while(rs.next()) {
            patients.add(parseResultSetToPatient(rs));
        }
        return patients;
    }

    public Patient selectPatientId (int svnr) throws Exception {
        PreparedStatement ps = c.prepareStatement("SELECT * FROM DATABASE.GET_ALL_PATIENTS WHERE SVNR = ?");
        ps.setInt(1, svnr);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return parseResultSetToPatient(rs);
        }
        return null;
    }

    private Patient parseResultSetToPatient(ResultSet rs) throws SQLException {
        Patient pat = new Patient();
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

        pat.setStaatsangehörigkeit(this.selectCountryByKuerzel(rs.getString(11)));

        pat.setPostleitzahl(rs.getString(14));
        pat.setOrt(rs.getString(15));
        pat.setStrasse(rs.getString(16));
        pat.setHausnr(rs.getString(17));
        pat.setTel(rs.getString(18));

        pat.setReligionszugehörigkeit(this.selectReligionByID(rs.getInt(19)));

        return pat;
    }

    public List<Religion> selectAllReligions() throws SQLException {
        List<Religion> religions = new LinkedList<>();
        Statement statereligion = c.createStatement();
        ResultSet rsreligion = statereligion.executeQuery("SELECT * from Religion");
        while (rsreligion.next()){
            Religion religion = new Religion();
            religion.setId(rsreligion.getInt(1));
            religion.setName(rsreligion.getString(2));
            religions.add(religion);
        }
      return religions;
    }

    public List<Land> selectAllCountries() throws SQLException {
        List<Land> countries = new LinkedList<>();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM DATABASE.LAND");
        while(rs.next()) {
            Land l = new Land();
            l.setKuerzel(rs.getString(1));
            l.setName(rs.getString(2));
            l.setVorwahl(rs.getString(3));
            countries.add(l);
        }
        return countries;
    }

    public Land selectCountryByKuerzel(String kuerzel) throws SQLException {
        PreparedStatement ps = c.prepareStatement("SELECT * FROM DATABASE.LAND WHERE KUERZEL = ?");
        ps.setString(1, kuerzel);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            Land l = new Land();
            l.setKuerzel(rs.getString(1));
            l.setName(rs.getString(2));
            l.setVorwahl(rs.getString(3));
            return l;
        }
        return null;
    }

    public Religion selectReligionByID(int id) throws SQLException {
        PreparedStatement ps = c.prepareStatement("SELECT * FROM RELIGION WHERE ID = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            Religion r = new Religion();
            r.setId(rs.getInt(1));
            r.setName(rs.getString(2));
            return r;
        }
        return null;
    }


    public int deletePatient(Patient p) throws SQLException {
        PreparedStatement ps = c.prepareStatement("DELETE FROM DATABASE.PATIENT WHERE SVNR = ?");
        ps.setInt(1, p.getSvnr());
        return ps.executeUpdate();
    }

    public int deleteCountry(Land l) throws SQLException {
        PreparedStatement ps = c.prepareStatement("DELETE FROM DATABASE.LAND WHERE KUERZEL = ?");
        ps.setString(1, l.getKuerzel());
        return ps.executeUpdate();
    }

    public int deleteReligion(Religion r) throws SQLException {
        PreparedStatement ps = c.prepareStatement("DELETE FROM DATABASE.RELIGION WHERE ID = ?");
        ps.setInt(1, r.getId());
        return ps.executeUpdate();
    }

    public int insertCountry(Land l) throws SQLException {
        if(selectCountryByKuerzel(l.getKuerzel()) != null) return -1;
        PreparedStatement ps = c.prepareStatement("INSERT INTO DATABASE.LAND VALUES(?, ?, ?)");
        ps.setString(1, l.getKuerzel());
        ps.setString(2, l.getName());
        ps.setString(3, l.getVorwahl());
        return ps.executeUpdate();
    }

    public int insertReligion(Religion r) throws SQLException {
        if(selectReligionByID(r.getId()) != null) return -1;
        PreparedStatement ps = c.prepareStatement("INSERT INTO DATABASE.RELIGION VALUES (?, ?)");
        ps.setInt(1, r.getId());
        ps.setString(2, r.getName());
        return ps.executeUpdate();
    }

}
