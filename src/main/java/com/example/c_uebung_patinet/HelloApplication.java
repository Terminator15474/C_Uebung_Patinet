package com.example.c_uebung_patinet;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    static Stage global_stage = null;
    @Override
    public void start(Stage stage) throws Exception {
        global_stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene( fxmlLoader.load());
        global_stage.setTitle("Patientenverwaltung");
        global_stage.setScene(scene);
        global_stage.show();
        global_stage.setOnCloseRequest(windowEvent -> {
            try {
                MainViewController.mostRecentController.connectionHandler.closeDatabaseConnection();
                MainViewController.mostRecentController.updateThread.interrupt();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                Platform.exit();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}