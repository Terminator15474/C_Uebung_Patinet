package com.example.c_uebung_patinet;

import com.example.c_uebung_patinet.SQL_Tools.ConnectionHandler;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.sql.SQLException;

public class UpdateUIThread extends Task<Void> {
    MainViewController mwc;
    ConnectionHandler ch;

    public UpdateUIThread(MainViewController mwc) throws SQLException {
        this.mwc = mwc;
        ch = new ConnectionHandler();
    }


    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                synchronized (mwc.lv_patienten) {
                    Platform.runLater(() -> {
                        try {
                            mwc.lv_patienten.getItems().setAll(ch.selectPatients());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
