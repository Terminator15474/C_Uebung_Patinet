package com.example.c_uebung_patinet;

import com.example.c_uebung_patinet.SQL_Model.Patient;
import com.example.c_uebung_patinet.SQL_Tools.ConnectionHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    ConnectionHandler connectionHandler = null;
    static Patient selected_Patient = null;
    @FXML
    private ListView<Patient> lv_patienten;
    @FXML
    private Label label_error;
    @FXML
    private Button btn_change;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_add;

    @FXML
    void addPatient(ActionEvent event) {

    }

    @FXML
    void changePatient(ActionEvent event) {

    }

    @FXML
    void deletePatient(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connectionHandler = new ConnectionHandler();
            List<Patient> patients = connectionHandler.selectPatients();
            lv_patienten.setItems(FXCollections.observableList(patients));
            btn_change.setDisable(true);
            btn_delete.setDisable(true);
            lv_patienten.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Patient>() {
                @Override
                public void changed(ObservableValue<? extends Patient> observableValue, Patient patient, Patient t1) {
                    btn_change.setDisable(false);
                    btn_delete.setDisable(false);
                    selected_Patient = t1;
                }
            });

        } catch (Exception e) {
            label_error.setText("An critical error occured! Please seek help and pray!");
        }
    }
}
