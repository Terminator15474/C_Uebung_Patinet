package com.example.c_uebung_patinet;

import com.example.c_uebung_patinet.SQL_Model.Patient;
import com.example.c_uebung_patinet.SQL_Tools.ConnectionHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    ConnectionHandler connectionHandler = null;

    public static MainViewController mostRecentController;

    public static Patient selected_Patient = null;
    @FXML
    public ListView<Patient> lv_patienten;
    @FXML
    private Label label_error;
    @FXML
    private Button btn_change;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_add;

    @FXML
    /**
     * Opens the Stammdaten.fxml to enter a new Patient
     * @param event The event that triggered this method
     */
    void addPatient(ActionEvent event) throws IOException {
        selected_Patient = null;
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("Stammdaten.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add new Patient");
        stage.show();
        stage.getScene().getWindow().setOnHidden(windowEvent -> {
            stage.close();
        });
    }

    @FXML
    /**
     * Opens the Stammdaten.fxml to change the selected Patient
     * @param event The event that triggered this method
     */
    void changePatient(ActionEvent event) throws Exception {
        if(selected_Patient == null) {
            label_error.setText("An critical error occured! Please seek help and pray!");
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("Stammdaten.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(selected_Patient.getNachname() + " " + selected_Patient.getVorname());
        stage.show();
        stage.getScene().getWindow().setOnHidden(windowEvent -> {
            stage.close();
        });
    }

    @FXML
    /**
     * Deletes the selected Patient from the database and the ListView
     * @param event The event that triggered this method
     */
    void deletePatient(ActionEvent event) throws Exception {
        int index = lv_patienten.getSelectionModel().getSelectedIndex();
        Patient temp = lv_patienten.getItems().get(index);
        lv_patienten.getItems().remove(temp);
        connectionHandler.deletePatient(temp);
    }

    UpdateUIThread uiThread;
    public Thread updateThread;

    @Override
    /**
     * Initializes the MainViewController
     * @param url The url of the fxml file
     * @param resourceBundle The resourceBundle of the fxml file
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            mostRecentController = this;
            uiThread = new UpdateUIThread(this);
            updateThread = new Thread(uiThread);
            updateThread.start();
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
            e.printStackTrace();
            label_error.setText("An critical error occured! Please seek help and pray!");
        }
    }

    /**
     * Updates the ListView
     * @param actionEvent The event that triggered this method
     */
    public void updateListView(ActionEvent actionEvent) {
        uiThread.update();
    }
}
