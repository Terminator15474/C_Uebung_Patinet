package com.example.c_uebung_patinet.SQL_Model;

/**
 * Class which models the Religion Table in the Database
 * @author lmayer
 */
public class Religion {
    private int id;
    private String name;

    /**
     * empty Constructor
     * @autor lmayer
     */
    public Religion() {
    }

    /**
     * Constructor sets the variables
     * @param id
     * @param name
     */
    public Religion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the value of id
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the String representation of the Object
     * @return the String representation of the Object
     */

    @Override
    public String toString() {
        return id + " - " + name;
    }

    /**
     * Compares this Object with the specified Object for equality
     * @param obj the Object to compare with
     * @return true if the Objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Religion)) return false;
        if(this.id == ((Religion)obj).getId()) return true;
        return false;
    }
}
