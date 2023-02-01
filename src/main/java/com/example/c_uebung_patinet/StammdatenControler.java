package com.example.c_uebung_patinet;

import com.example.c_uebung_patinet.SQL_Model.Land;
import com.example.c_uebung_patinet.SQL_Tools.ConnectionHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

public class StammdatenControler implements Initializable {

    public StammdatenControler() {}
    public ConnectionHandler connectionHandler = null;

    @FXML
    private TextField tf_ln;
    @FXML
    private TextField tf_fn;
    @FXML
    private TextField tf_birthname;
    @FXML
    private TextField tf_title;
    @FXML
    private TextField tf_name_add;
    @FXML
    private DatePicker datePicker_dateOfBirth;
    @FXML
    private ComboBox cb_gender;
    @FXML
    private TextField tf_marrialStatus;
    @FXML
    private TextField tf_countryID;
    @FXML
    private TextField tf_postalCode;
    @FXML
    private TextField tf_place;
    @FXML
    private TextField tf_street;
    @FXML
    private TextField tf_hn;
    @FXML
    private TextField tf_vorw;
    @FXML
    private TextField tf_tel;
    @FXML
    private TextField tf_svnr;
    @FXML
    private TextField tf_placeOfBirth;
    @FXML
    private ComboBox cb_country;
    @FXML
    private ComboBox cb_konfession;

    @FXML
    private Button btn_exit;

    @FXML
    public void end(ActionEvent actionEvent) {
        if(tf_svnr.getText() == null || tf_svnr.getText().equals("")){
            return;
        }

        if(tf_ln.getText() == null || tf_ln.getText().equals("")){
            return;
        }

        if(tf_fn.getText() == null || tf_fn.getText().equals("")){
            return;
        }

        tf_birthname.getText();

        tf_title.getText();

        tf_name_add.getText();

        if(datePicker_dateOfBirth.getValue() == null){
            return;
        }

        if(tf_placeOfBirth.getText() == null || tf_placeOfBirth.getText().equals("")){
            return;
        }

        int genderindex = cb_gender.getSelectionModel().getSelectedIndex();
        if (cb_gender.getItems().get(genderindex) == null){
            return;
        }

        if(tf_marrialStatus.getText() == null || tf_marrialStatus.getText().equals("")){
            return;
        }

        if (tf_countryID.getText() == null || tf_countryID.getText().equals("")) {
            return;
        }

        int countryindex = cb_country.getSelectionModel().getSelectedIndex();
        if(cb_country.getItems().get(countryindex) == null){
            return;
        }

        if (tf_postalCode.getText() == null || tf_postalCode.getText().equals("")){
            return;
        }

        if (tf_place.getText() == null || tf_place.getText().equals("")){
            return;
        }

        if (tf_street.getText() == null || tf_street.getText().equals("")){
            return;
        }
        if (tf_hn.getText() == null || tf_hn.getText().equals("")){
            return;
        }

        if (tf_vorw.getText() == null || tf_vorw.getText().equals("")){
            return;
        }

        if (tf_tel.getText() == null || tf_tel.getText().equals("")){
            return;
        }

        int konfessionindex = cb_konfession.getSelectionModel().getSelectedIndex();
        if(cb_konfession.getItems().get(konfessionindex) == null){
            return;
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connectionHandler = new ConnectionHandler();
            cb_country.setItems(FXCollections.observableList(connectionHandler.selectAllCountries()));
            cb_gender.getItems().add("Männlich");
            cb_gender.getItems().add("Weiblich");
            cb_gender.getItems().add("Anders");
            cb_konfession.setItems(FXCollections.observableList(connectionHandler.selectAllReligions()));
            cb_country.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object o, Object t1) {
                    Land l = (Land) t1;
                    tf_countryID.setText(l.getKuerzel());
                }
            });

            // check if add or change
            if(MainViewController.selected_Patient != null) {
                // change
                tf_fn.setText(MainViewController.selected_Patient.getVorname());
                tf_ln.setText(MainViewController.selected_Patient.getNachname());
                tf_birthname.setText(MainViewController.selected_Patient.getGeburtsname());
                tf_title.setText(MainViewController.selected_Patient.getTitle());
                tf_name_add.setText(MainViewController.selected_Patient.getNamenszuatz());
                System.out.println(MainViewController.selected_Patient.getGeburtsdatum());
                datePicker_dateOfBirth.setValue(MainViewController.selected_Patient.getGeburtsdatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                tf_placeOfBirth.setText(MainViewController.selected_Patient.getGeburtsort());
                cb_gender.getSelectionModel().select(MainViewController.selected_Patient.getGeschlecht());
                tf_marrialStatus.setText(MainViewController.selected_Patient.getFamilienstand());
                tf_countryID.setText(MainViewController.selected_Patient.getStaatsangehörigkeit().getKuerzel());
                cb_country.getSelectionModel().select(MainViewController.selected_Patient.getStaatsangehörigkeit().getName());
                tf_postalCode.setText(MainViewController.selected_Patient.getPostleitzahl());
                tf_place.setText(MainViewController.selected_Patient.getOrt());
                tf_vorw.setText(MainViewController.selected_Patient.getStaatsangehörigkeit().getVorwahl());
                tf_tel.setText(MainViewController.selected_Patient.getTel());
                cb_konfession.getSelectionModel().select(MainViewController.selected_Patient.getReligionszugehörigkeit().getName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void addCountry(ActionEvent actionEvent) {
    }

    @FXML
    public void addReligion(ActionEvent actionEvent) {
    }
}