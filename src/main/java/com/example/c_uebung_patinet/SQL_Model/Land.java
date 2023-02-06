package com.example.c_uebung_patinet.SQL_Model;

/**
 * Class that models the Land Entry in the Database
 * @author lmayer
 */
public class Land {
    private String kuerzel, name, vorwahl;
    public Land(String kuerzel, String name, String vorwahl) {
        this.kuerzel = kuerzel;
        this.name = name;
        this.vorwahl = vorwahl;
    }

    public Land() {
    }

    /**
     * Returns the Kuerzel of the Land
     * @return Kuerzel of the Land
     */
    public String getKuerzel() {
        return kuerzel;
    }

    /**
     * Sets the Kuerzel of the Land
     * @param kuerzel Kuerzel of the Land
     */
    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    /**
     * Returns the Name of the Land
     * @return Name of the Land
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name of the Land
     * @param name Name of the Land
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the Vorwahl of the Land
     * @return Vorwahl of the Land
     */
    public String getVorwahl() {
        return vorwahl;
    }

    /**
     * Sets the Vorwahl of the Land
     * @param vorwahl Vorwahl of the Land
     */
    public void setVorwahl(String vorwahl) {
        this.vorwahl = vorwahl;
    }


    /**
     * Returns the String representation of the Land
     * @return String representation of the Land
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Checks if the Land is equal to another Land
     * @param obj Land to compare to
     * @return true if the Land is equal to the other Land, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Land)) return false;
        if(((Land)obj).getKuerzel().equals(this.getKuerzel())) return true;
        return false;
    }
}
