package com.example.c_uebung_patinet.SQL_Model;

import java.util.Date;

public class Patient {
    private int svnr;
    private String vorname, nachname, geburtsname, title, namenszuatz;
    private Date geburtsdatum;
    private String geburtsort, geschlecht, familienstand;
    private Land staatsangehörigkeit;
    private String postleitzahl, ort, strasse, hausnr, tel;
    private Religion religionszugehörigkeit;

    public int getSvnr() {
        return svnr;
    }

    public void setSvnr(int svnr) {
        this.svnr = svnr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtsname() {
        return geburtsname;
    }

    public void setGeburtsname(String geburtsname) {
        this.geburtsname = geburtsname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNamenszuatz() {
        return namenszuatz;
    }

    public void setNamenszuatz(String namenszuatz) {
        this.namenszuatz = namenszuatz;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getGeburtsort() {
        return geburtsort;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getFamilienstand() {
        return familienstand;
    }

    public void setFamilienstand(String familienstand) {
        this.familienstand = familienstand;
    }

    public Land getStaatsangehörigkeit() {
        return staatsangehörigkeit;
    }

    public void setStaatsangehörigkeit(Land staatsangehörigkeit) {
        this.staatsangehörigkeit = staatsangehörigkeit;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnr() {
        return hausnr;
    }

    public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Religion getReligionszugehörigkeit() {
        return religionszugehörigkeit;
    }

    public void setReligionszugehörigkeit(Religion religionszugehörigkeit) {
        this.religionszugehörigkeit = religionszugehörigkeit;
    }
}
