package com.example.c_uebung_patinet;

import com.example.c_uebung_patinet.SQL_Model.Land;
import com.example.c_uebung_patinet.SQL_Model.Patient;
import com.example.c_uebung_patinet.SQL_Model.Religion;
import com.example.c_uebung_patinet.SQL_Tools.ConnectionHandler;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Class which handels the UI of the "Stammdaten".
 *
 */
public class StammdatenController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lable_error;

    public StammdatenController() {}
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
    /**
     * This method is called when the user clicks the "Fertig" button.
     * It checks if all the required fields are filled and if so, it creates a new patient and saves it to the database.
        * @param actionEvent The event that triggered this method.
     * @autor aholzinger
     */
    public void end(ActionEvent actionEvent) {
        if(tf_svnr.getText() == null || tf_svnr.getText().equals("")){
            lable_error.setText("Patienten ID ist ein Pflichtfeld");
            return;
        }
        if(tf_svnr.getText().matches("[a-zA-Z]+")) {
            lable_error.setText("Patienten ID darf nur Nummern enthalten");
            return;
        }

        if(tf_ln.getText() == null || tf_ln.getText().equals("")){
            lable_error.setText("Name ist ein Pflichtfeld");
            return;
        }

        if(tf_fn.getText() == null || tf_fn.getText().equals("")){
            lable_error.setText("Vorname ist ein Pflichtfeld");
            return;
        }

        tf_birthname.getText();

        tf_title.getText();

        tf_name_add.getText();

        if(datePicker_dateOfBirth.getValue() == null){
            lable_error.setText("Geburtsdatum ist ein Pflichtfeld");
            return;
        }

        if(tf_placeOfBirth.getText() == null || tf_placeOfBirth.getText().equals("")){
            lable_error.setText("Geburtsort ist ein Pflichtfeld");
            return;
        }

        int genderindex = cb_gender.getSelectionModel().getSelectedIndex();
        if (cb_gender.getItems().get(genderindex) == null){
            lable_error.setText("Geschlecht ist ein Pflichtfeld");
            return;
        }

        if(tf_marrialStatus.getText() == null || tf_marrialStatus.getText().equals("")){
            lable_error.setText("Familienstand ist ein Pflichtfeld");
            return;
        }

        if (tf_countryID.getText() == null || tf_countryID.getText().equals("")) {
            return;
        }

        int countryindex = cb_country.getSelectionModel().getSelectedIndex();
        if(cb_country.getItems().get(countryindex) == null){
            lable_error.setText("Land muss ausgewählt werden");
            return;
        }

        if (tf_postalCode.getText() == null || tf_postalCode.getText().equals("")){
            lable_error.setText("PLZ ist ein Pflichtfeld");
            return;
        }

        if (tf_place.getText() == null || tf_place.getText().equals("")){
            lable_error.setText("Ort ist ein Pflichtfeld");
            return;
        }

        if (tf_street.getText() == null || tf_street.getText().equals("")){
            lable_error.setText("Straße ist ein Pflichtfeld");
            return;
        }
        if (tf_hn.getText() == null || tf_hn.getText().equals("")){
            lable_error.setText("Hausnummer ist ein Pflichtfeld");
            return;
        }

        if (tf_vorw.getText() == null || tf_vorw.getText().equals("")){
            return;
        }

        if (tf_tel.getText() == null || tf_tel.getText().equals("")){
            lable_error.setText("Telefonnummer ist ein Pflichtfeld");
            return;
        }

        int konfessionindex = cb_konfession.getSelectionModel().getSelectedIndex();
        if(cb_konfession.getItems().get(konfessionindex) == null){
            lable_error.setText("Konfession ist ein Pflichtfeld");
            return;
        }

        if(tf_postalCode.getText().length() > 6) {
            lable_error.setText("PLZ darf Maximal 6 Zeichen lang sein");
            return;
        }

        if(tf_hn.getText().length() > 5) {
            lable_error.setText("Hausnummer darf Maximal 5 Zeichen lang sein");
            return;
        }

        if(tf_countryID.getText().length() > 3) {
            lable_error.setText("Landeskürzel darf Maximal 3 Zeichen lang sein");
            return;
        }

        if(tf_vorw.getText().length() > 3) {
            lable_error.setText("Vorwahl darf Maximal 3 Zeichen lang sein");
            return;
        }


        if(tf_name_add.getText().length() > 10) {
            lable_error.setText("Namenszusatz darf Maximal 10 Zeichen lang sein");
            return;
        }


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
    /**
     * This method is called when the window is loaded.
     * It initializes the comboboxes and the textfields.
     * @param url The url of the fxml file.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connectionHandler = new ConnectionHandler();
            cb_country.getItems().setAll(connectionHandler.selectAllCountries());
            cb_gender.getItems().add("männlich");
            cb_gender.getItems().add("weiblich");
            cb_gender.getItems().add("divers");

            cb_konfession.setCellFactory(param -> {
                return new ListCell<Religion>() {
                    @Override
                    protected void updateItem(Religion religion, boolean b) {
                        super.updateItem(religion, b);
                        if(religion == null) {
                            setText(null);
                            setTooltip(null);
                            return;
                        }
                        EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                try {
                                    connectionHandler.deleteReligion(religion);
                                    cb_konfession.getItems().setAll(connectionHandler.selectAllReligions());
                                    // System.out.println("Deletet" + religion.toString());
                                } catch (SQLIntegrityConstraintViolationException e){
                                    lable_error.setText("Religion " + religion.getName() + " wird verwendet!");
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        };
                        setText(religion.toString());
                        Button button = new Button("Delete");
                        button.setOnAction(click);
                        Tooltip t = new Tooltip();
                        t.setHideDelay(Duration.seconds(1));
                        t.setGraphic(button);
                        setTooltip(t);
                    }
                };
            });

            cb_country.setCellFactory(param -> new ListCell<Land>() {
                @Override
                protected void updateItem(Land land, boolean b) {
                    super.updateItem(land, b);
                    if(land == null) {
                        setText(null);
                        setTooltip(null);
                        return;
                    }
                    setText(land.toString());
                    Button button = new Button("Delete");
                    EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            try {
                                connectionHandler.deleteCountry(land);
                                cb_country.getItems().setAll(connectionHandler.selectAllCountries());
                            } catch (SQLIntegrityConstraintViolationException e) {
                                lable_error.setText("Land " + land.getKuerzel() + " " + land.getName() + " wird verwendet!");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    };
                    Tooltip t = new Tooltip();
                    t.setHideDelay(Duration.seconds(1));
                    button.setOnAction(click);
                    t.setGraphic(button);
                    setTooltip(t);
                }
            });
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
                // System.out.println(MainViewController.selected_Patient.getPostleitzahl());
                tf_place.setText(MainViewController.selected_Patient.getOrt());
                tf_street.setText(MainViewController.selected_Patient.getStrasse());
                tf_hn.setText(MainViewController.selected_Patient.getHausnr());
                tf_vorw.setText(MainViewController.selected_Patient.getStaatsangehörigkeit().getVorwahl());
                tf_tel.setText(MainViewController.selected_Patient.getTel());
                cb_konfession.getSelectionModel().select(cb_konfession.getItems().indexOf(MainViewController.selected_Patient.getReligionszugehörigkeit()));
            }
            Platform.runLater(() -> tf_svnr.requestFocus());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sortCountries();
        sortReligions();
    }

    @FXML
    /**
     * This method is called when the add country button is clicked.
     * It opens a new window to add a country.
     * @param actionEvent The event that triggered this method.
     */
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
        Button b = new Button("Save");
        b.setPrefWidth(150);
        Label error = new Label("");
        error.setLabelFor(r);
        error.setTextFill(Color.RED);

        EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
            /**
             * Class which handels the UI for "Stammdaten".
             * @param actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    if(id.getText().length() > 3) {
                        error.setText("ID max length 3!");
                        return;
                    }
                    if(nd.getText().length() > 30) {
                        error.setText("Name max length 30!");
                        return;
                    }
                    if(xd.getText().length() > 3) {
                        error.setText("Vorwahl max length 3!");
                        return;
                    }
                    if(xd.getText().matches("[a-zA-Z]+")) {
                        error.setText("Only numbers in the vorwahl!");
                        return;
                    }
                    if(id.getText().isEmpty() || nd.getText().isEmpty() || xd.getText().isEmpty()) {
                        error.setText("No empty fields!");
                        return;
                    }
                    int res = connectionHandler.insertCountry(new Land(id.getText(), nd.getText(), xd.getText()));
                    if(res == -1) {
                        error.setText("Country ID already exists!");
                        return;
                    }
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

        r.setHgap(10);
        r.setVgap(10);
        r.setPadding(new Insets(10, 10, 10, 10));
        r.setAlignment(Pos.CENTER);

        b.setOnAction(click);
        r.getChildren().addAll(id, nd, xd, b, error);
        r.autosize();
        Scene scene = new Scene(r, 217, 255);
        root.setScene(scene);
        root.setOnHidden(windowEvent -> {
            root.close();
            sortCountries();
        });
        root.show();

    }

    @FXML
    /**
     * This method is called when the add religion button is clicked.
     * It opens a new window to add a religion.
     * @param actionEvent The event that triggered this method.
     */
    public void addReligion(ActionEvent actionEvent) {
        Stage root = new Stage();
        root.setTitle("Add a religion");
        TilePane r = new TilePane();
        TextField id = new TextField();
        id.setPromptText("Enter Religion ID");
        TextField nd = new TextField();
        nd.setPromptText("Enter Religion Name");
        Button b = new Button("Save");
        b.setPrefWidth(150);
        Label error = new Label("");
        error.setLabelFor(r);
        error.setTextFill(Color.RED);

        EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
            /**
             * Class which handles the UI
             * @param actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    if(id.getText().isEmpty() || nd.getText().isEmpty()) {
                        error.setText("No empty fields");
                        return;
                    }
                    if(nd.getText().length() > 30) {
                        error.setText("Name no longer than 30 chars!");
                        return;
                    }
                    int res = connectionHandler.insertReligion(new Religion(Integer.parseInt(id.getText()), nd.getText()));
                    // System.out.println(res);
                    if(res < 0) {
                        error.setText("ID already exists!");
                        return;
                    }
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
                } catch (NumberFormatException e) {
                    error.setText("Enter a valid number");
                }
            }
        };


        r.setHgap(10);
        r.setVgap(10);
        r.setPadding(new Insets(10, 10, 10, 10));
        r.setAlignment(Pos.CENTER);

        b.setOnAction(click);
        r.getChildren().addAll(id, nd, b, error);
        r.autosize();
        Scene scene = new Scene(r, 203, 200);
        root.setScene(scene);
        root.setOnHidden(windowEvent -> {
            root.close();
            sortReligions();
        });
        root.show();

    }

    /**
     * Method sorts the countries
     */
    private void sortCountries() {
        cb_country.getItems().sort(Comparator.comparing(Land::getKuerzel));
    }

    /**
     * Method sorts the religions
     */
    private void sortReligions() {
        cb_konfession.getItems().sort(Comparator.comparing(Religion::getId));
    }
}