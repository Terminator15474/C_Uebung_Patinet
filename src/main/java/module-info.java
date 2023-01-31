module com.example.c_uebung_patinet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.c_uebung_patinet to javafx.fxml;
    exports com.example.c_uebung_patinet;
}