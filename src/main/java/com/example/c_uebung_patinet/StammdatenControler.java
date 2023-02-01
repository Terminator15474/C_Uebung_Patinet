package com.example.c_uebung_patinet;

import com.example.c_uebung_patinet.SQL_Model.Land;
import com.example.c_uebung_patinet.SQL_Model.Patient;
import com.example.c_uebung_patinet.SQL_Model.Religion;
import com.example.c_uebung_patinet.SQL_Tools.ConnectionHandler;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StammdatenControler implements Initializable {

    @FXML
    private Node root;

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
    private ComboBox<String> cb_gender;
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
    private ComboBox<Land> cb_country;
    @FXML
    private ComboBox<Religion> cb_konfession;

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

        // System.out.println(tf_ln.getText());

        Patient p = new Patient();
        p.setSvnr(Integer.parseInt(tf_svnr.getText()));
        p.setVorname(tf_fn.getText());
        // System.out.println(tf_ln.getText());
        p.setNachname(tf_ln.getText());
        p.setGeburtsname(tf_birthname.getText());
        p.setTitle(tf_title.getText());
        p.setNamenszuatz(tf_name_add.getText());
        p.setGeburtsdatum(Date.from(datePicker_dateOfBirth.getValue().atStartOfDay().toInstant(ZoneOffset.UTC)));
        p.setGeburtsort(tf_placeOfBirth.getText());
        p.setGeschlecht(cb_gender.getSelectionModel().getSelectedItem());
        p.setFamilienstand(tf_marrialStatus.getText());
        p.setStaatsangehörigkeit(cb_country.getSelectionModel().getSelectedItem());
        p.setPostleitzahl(tf_postalCode.getText());
        p.setOrt(tf_place.getText());
        p.setStrasse(tf_street.getText());
        p.setHausnr(tf_hn.getText());
        p.setTel(tf_tel.getText());
        p.setReligionszugehörigkeit(cb_konfession.getSelectionModel().getSelectedItem());
        // System.out.println(p.getNachname());
        try {
            connectionHandler.insertPatientInDatabase(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            connectionHandler.closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        root.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connectionHandler = new ConnectionHandler();
            cb_country.getItems().setAll(connectionHandler.selectAllCountries());
            cb_gender.getItems().add("männlich");
            cb_gender.getItems().add("weiblich");
            cb_gender.getItems().add("anders");
            cb_konfession.getItems().setAll(connectionHandler.selectAllReligions());
            cb_country.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Land>() {
                @Override
                public void changed(ObservableValue<? extends Land> observableValue, Land land, Land t1) {
                    if(t1 == null) return;
                    tf_countryID.setText(t1.getKuerzel());
                    tf_vorw.setText(t1.getVorwahl());
                }
            });

            // check if add or change
            if(MainViewController.selected_Patient != null) {
                // change
                tf_svnr.setText("" + MainViewController.selected_Patient.getSvnr());
                tf_fn.setText(MainViewController.selected_Patient.getVorname());
                tf_ln.setText(MainViewController.selected_Patient.getNachname());
                tf_birthname.setText(MainViewController.selected_Patient.getGeburtsname());
                tf_title.setText(MainViewController.selected_Patient.getTitle());
                tf_name_add.setText(MainViewController.selected_Patient.getNamenszuatz());
                datePicker_dateOfBirth.setValue(new java.sql.Date(MainViewController.selected_Patient.getGeburtsdatum().getTime()).toLocalDate());

                tf_placeOfBirth.setText(MainViewController.selected_Patient.getGeburtsort());
                cb_gender.getSelectionModel().select(cb_gender.getItems().indexOf(MainViewController.selected_Patient.getGeschlecht()));
                tf_marrialStatus.setText(MainViewController.selected_Patient.getFamilienstand());
                tf_countryID.setText(MainViewController.selected_Patient.getStaatsangehörigkeit().getKuerzel());
                cb_country.getSelectionModel().select(cb_country.getItems().indexOf(MainViewController.selected_Patient.getStaatsangehörigkeit()));
                // System.out.println(cb_country.getItems().indexOf(MainViewController.selected_Patient.getStaatsangehörigkeit()));
                // System.out.println(cb_country.getItems().stream().filter(l -> l.getKuerzel().equals(MainViewController.selected_Patient.getStaatsangehörigkeit().getKuerzel())).collect(Collectors.toList()));
                tf_postalCode.setText(MainViewController.selected_Patient.getPostleitzahl());
                tf_place.setText(MainViewController.selected_Patient.getOrt());
                tf_street.setText(MainViewController.selected_Patient.getStrasse());
                tf_hn.setText(MainViewController.selected_Patient.getHausnr());
                tf_vorw.setText(MainViewController.selected_Patient.getStaatsangehörigkeit().getVorwahl());
                tf_tel.setText(MainViewController.selected_Patient.getTel());
                cb_konfession.getSelectionModel().select(cb_konfession.getItems().indexOf(MainViewController.selected_Patient.getReligionszugehörigkeit()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public void addCountry(ActionEvent actionEvent) {
        Stage root = new Stage();
        root.setTitle("Add a country");
        TilePane r = new TilePane();
        TextField id = new TextField();
        id.setPromptText("Enter Country ID");
        TextField nd = new TextField();
        nd.setPromptText("Enter Country Name");
        TextField xd = new TextField();
        xd.setPromptText("Enter Vorwahl");
        Button b = new Button("save");

        EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    connectionHandler.insertCountry(new Land(id.getText(), nd.getText(), xd.getText()));
                    nd.getScene().getWindow().hide();
                    Platform.runLater(() -> {
                        try {
                            cb_country.getItems().setAll(connectionHandler.selectAllCountries());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        b.setOnAction(click);
        r.getChildren().addAll(id, nd, xd, b);
        Scene scene = new Scene(r);
        root.setScene(scene);
        root.setOnHidden(windowEvent -> {
            root.close();
        });
        root.show();

    }

    @Deprecated
    public void addReligion(ActionEvent actionEvent) {
        Stage root = new Stage();
        root.setTitle("Add a country");
        TilePane r = new TilePane();
        TextField id = new TextField();
        id.setPromptText("Enter Religion ID");
        TextField nd = new TextField();
        nd.setPromptText("Enter Religion Name");
        Button b = new Button("save");

        EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    connectionHandler.insertReligion(new Religion(Integer.parseInt(id.getText()), nd.getText()));
                    nd.getScene().getWindow().hide();
                    Platform.runLater(() -> {
                        try {
                            cb_konfession.getItems().setAll(connectionHandler.selectAllReligions());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        b.setOnAction(click);
        r.getChildren().addAll(id, nd, b);
        Scene scene = new Scene(r);
        root.setScene(scene);
        root.setOnHidden(windowEvent -> {
            root.close();
        });
        root.show();

    }
}