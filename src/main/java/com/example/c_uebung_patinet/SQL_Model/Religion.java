package com.example.c_uebung_patinet.SQL_Model;

public class Religion {
    private int id;
    private String name;

    public Religion() {
    }

    public Religion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Religion)) return false;
        if(this.id == ((Religion)obj).getId()) return true;
        return false;
    }
}
