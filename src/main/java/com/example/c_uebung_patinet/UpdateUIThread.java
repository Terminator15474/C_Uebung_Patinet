package com.example.c_uebung_patinet;

import com.example.c_uebung_patinet.SQL_Tools.ConnectionHandler;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.sql.SQLException;

/**
 * Thread to Update the ListView in the UI
 * @author lmayer
 */
public class UpdateUIThread extends Task<Void> {
    MainViewController mwc;
    ConnectionHandler ch;

    public UpdateUIThread(MainViewController mwc) throws SQLException {
        this.mwc = mwc;
        ch = new ConnectionHandler();
    }


    @Override
    protected Void call() {
        while (true) {
            try {
                update();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method to update the UI in the Main View
     */
    public void update() {
        synchronized (mwc.lv_patienten) {
            Platform.runLater(() -> {
                try {
                    int selectIndex = mwc.lv_patienten.getSelectionModel().getSelectedIndex();
                    mwc.lv_patienten.getItems().setAll(ch.selectPatients());
                    mwc.lv_patienten.getSelectionModel().select(selectIndex);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
