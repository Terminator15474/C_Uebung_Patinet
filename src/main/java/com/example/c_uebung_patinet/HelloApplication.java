package com.example.c_uebung_patinet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static Stage global_stage;
    @Override
    public void start(Stage stage) throws Exception {
        global_stage = stage;
        setScene("main-view.fxml");
        global_stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setScene(String fxmlFilename) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFilename));
        Scene scene = new Scene(fxmlLoader.load());
        global_stage.setTitle("Patientenverwaltung");
        global_stage.setScene(scene);
    }
}