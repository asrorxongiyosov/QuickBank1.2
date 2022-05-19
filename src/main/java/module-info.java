module com.quickbank.quickbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.quickbank.quickbank to javafx.fxml;
    exports com.quickbank.quickbank;
}