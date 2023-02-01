module com.example.c_uebung_patinet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.c_uebung_patinet to javafx.fxml;
    opens com.example.c_uebung_patinet.SQL_Model to javafx.fxml;

    exports com.example.c_uebung_patinet;
}