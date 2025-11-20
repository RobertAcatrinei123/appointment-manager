module appoitment_manager {
    // dependencies (no test dependencies here)
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires java.sql;

    // required for running main method in JavaFX application
    exports ro.ubbcluj.apm.am;
    exports ro.ubbcluj.apm.am.domain;

    // packages to be accessible by FXMLLoader
    opens ro.ubbcluj.apm.am to javafx.fxml;
    opens ro.ubbcluj.apm.am.controller to javafx.fxml;
}