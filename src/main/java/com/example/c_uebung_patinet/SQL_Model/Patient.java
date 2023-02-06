package com.example.c_uebung_patinet.SQL_Model;

import java.util.Date;

/**
 * Class which models the Patient table in the Database
 * @author lmayer
 */
public class Patient {
    private int svnr;
    private String vorname, nachname, geburtsname, title, namenszuatz;
    private Date geburtsdatum;
    private String geburtsort, geschlecht, familienstand;
    private Land staatsangehörigkeit;
    private String postleitzahl, ort, strasse, hausnr, tel;
    private Religion religionszugehörigkeit;

    public Patient() {
    }

    public Patient(int svnr, String vorname, String nachname, String geburtsname, String title, String namenszuatz, Date geburtsdatum, String geburtsort, String geschlecht, String familienstand, Land staatsangehörigkeit, String postleitzahl, String ort, String strasse, String hausnr, String tel, Religion religionszugehörigkeit) {
        this.svnr = svnr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsname = geburtsname;
        this.title = title;
        this.namenszuatz = namenszuatz;
        this.geburtsdatum = geburtsdatum;
        this.geburtsort = geburtsort;
        this.geschlecht = geschlecht;
        this.familienstand = familienstand;
        this.staatsangehörigkeit = staatsangehörigkeit;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.tel = tel;
        this.religionszugehörigkeit = religionszugehörigkeit;
    }

    /**
     * get the value of svnr
     * @return the value of svnr
     */
    public int getSvnr() {
        return svnr;
    }

    /**
     * set the value of svnr
     * @param svnr new value of svnr
     */
    public void setSvnr(int svnr) {
        this.svnr = svnr;
    }

    /**
     * get the value of vorname
     * @return the value of vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * set the value of vorname
     * @param vorname new value of vorname
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * get the value of nachname
     * @return the value of nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * set the value of nachname
     * @param nachname new value of nachname
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * get the value of geburtsname
     * @return the value of geburtsname
     */
    public String getGeburtsname() {
        return geburtsname;
    }

    /**
     * set the value of geburtsname
     * @param geburtsname new value of geburtsname
     */
    public void setGeburtsname(String geburtsname) {
        this.geburtsname = geburtsname;
    }

    /**
     * get the value of title
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set the value of title
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get the value of namenszuatz
     * @return the value of namenszuatz
     */
    public String getNamenszuatz() {
        return namenszuatz;
    }

    /**
     * set the value of namenszuatz
     * @param namenszuatz new value of namenszuatz
     */
    public void setNamenszuatz(String namenszuatz) {
        this.namenszuatz = namenszuatz;
    }

    /**
     * get the value of geburtsdatum
     * @return the value of geburtsdatum
     */
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * set the value of geburtsdatum
     * @param geburtsdatum new value of geburtsdatum
     */
    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * get the value of geburtsort
     * @return the value of geburtsort
     */
    public String getGeburtsort() {
        return geburtsort;
    }

    /**
     * set the value of geburtsort
     * @param geburtsort new value of geburtsort
     */
    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    /**
     * get the value of geschlecht
     * @return the value of geschlecht
     */
    public String getGeschlecht() {
        return geschlecht;
    }

    /**
     * set the value of geschlecht
     * @param geschlecht new value of geschlecht
     */
    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    /**
     * get the value of familienstand
     * @return the value of familienstand
     */
    public String getFamilienstand() {
        return familienstand;
    }

    /**
     * set the value of familienstand
     * @param familienstand new value of familienstand
     */
    public void setFamilienstand(String familienstand) {
        this.familienstand = familienstand;
    }

    /**
     * get the value of staatsangehörigkeit
     * @return the value of staatsangehörigkeit
     */
    public Land getStaatsangehörigkeit() {
        return staatsangehörigkeit;
    }

    /**
     * set the value of staatsangehörigkeit
     * @param staatsangehörigkeit new value of staatsangehörigkeit
     */
    public void setStaatsangehörigkeit(Land staatsangehörigkeit) {
        this.staatsangehörigkeit = staatsangehörigkeit;
    }

    /**
     * get the value of religion
     * @return the value of religion
     */
    public String getPostleitzahl() {
        return postleitzahl;
    }

    /**
     * set the value of religion
     * @param religion new value of religion
     */
    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    /**
     * get the value of ort
     * @return the value of ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * set the value of ort
     * @param ort new value of ort
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * get the value of strasse
     * @return the value of strasse
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * set the value of strasse
     * @param strasse new value of strasse
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * get the value of hausnr
     * @return the value of hausnr
     */
    public String getHausnr() {
        return hausnr;
    }

    /**
     * set the value of hausnr
     * @param hausnr new value of hausnr
     */
    public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }

    /**
     * get the value of the telephone number
     * @return the value of the telephone number
     */
    public String getTel() {
        return tel;
    }

    /**
     * set the value of the telephone number
     * @param tel new value of the telephone number
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * get religion
     * @return the religion of the patient
     */
    public Religion getReligionszugehörigkeit() {
        return religionszugehörigkeit;
    }

    /**
     * set religion
     * @param religionszugehörigkeit new value of religion
     */
    public void setReligionszugehörigkeit(Religion religionszugehörigkeit) {
        this.religionszugehörigkeit = religionszugehörigkeit;
    }

    /**
     * get the string representation of the patient
     * @return the string representation of the patient
     */
    @Override
    public String toString() {
        return this.vorname + " " + this.nachname;
    }
}
