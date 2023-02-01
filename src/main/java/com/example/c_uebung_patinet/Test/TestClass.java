package com.example.c_uebung_patinet.Test;

import com.example.c_uebung_patinet.SQL_Model.Land;
import com.example.c_uebung_patinet.SQL_Model.Patient;
import com.example.c_uebung_patinet.SQL_Model.Religion;
import com.example.c_uebung_patinet.SQL_Tools.ConnectionHandler;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TestClass {
    public static void main(String[] args) throws SQLException {
        ConnectionHandler ch = new ConnectionHandler();
        Land l = new Land("AT", "Österreich", "+43");
        Religion r = new Religion(3, "römisch-evangälisch");
        Patient patient = new Patient(123, "Sepp", "Huber", "Huber", null, null, new Date(System.currentTimeMillis()), "Ried", "männlich", "ledig", l, "4600", "Wels", "Linzerstraße", "187", "1912412", r);
        try {
            ch.insertCountry(l);
            ch.insertReligion(r);
            ch.insertPatientInDatabase(patient);
            List<Patient> patients = ch.selectPatients();
            System.out.println(patients);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
