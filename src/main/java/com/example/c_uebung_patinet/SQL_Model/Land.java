package com.example.c_uebung_patinet.SQL_Model;

public class Land {
    public Land(String kuerzel, String name, String vorwahl) {
        this.kuerzel = kuerzel;
        this.name = name;
        this.vorwahl = vorwahl;
    }

    public Land() {
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorwahl() {
        return vorwahl;
    }

    public void setVorwahl(String vorwahl) {
        this.vorwahl = vorwahl;
    }

    private String kuerzel, name, vorwahl;

}
