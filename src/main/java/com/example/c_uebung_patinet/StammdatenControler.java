package com.example.c_uebung_patinet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StammdatenControler {

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
        tf_svnr.getText();
        tf_ln.getText();
        tf_fn.getText();
        tf_birthname.getText();
        tf_title.getText();
        tf_name_add.getText();
        DatePicker birthdate = datePicker_dateOfBirth;
        tf_placeOfBirth.getText();
        int genderindex = cb_gender.getSelectionModel().getSelectedIndex();
        cb_gender.getItems().get(genderindex);
        tf_marrialStatus.getText();
        tf_countryID.getText();
        int countryindex = cb_country.getSelectionModel().getSelectedIndex();
        cb_country.getItems().get(countryindex);
        tf_postalCode.getText();
        tf_place.getText();
        tf_street.getText();
        tf_hn.getText();
        tf_vorw.getText();
        tf_tel.getText();
        int konfessionindex = cb_konfession.getSelectionModel().getSelectedIndex();
        cb_konfession.getItems().get(konfessionindex);


    }
}