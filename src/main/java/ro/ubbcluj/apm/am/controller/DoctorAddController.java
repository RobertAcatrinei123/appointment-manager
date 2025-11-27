package ro.ubbcluj.apm.am.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.service.ContextService;

public class DoctorAddController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField specialtyField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField gradeField;

    @FXML
    private void handleSave() {
        Doctor doctor = new Doctor(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                specialtyField.getText(),
                locationField.getText(),
                Double.parseDouble(gradeField.getText())
        );
        ContextService.getInstance().getDoctorService().addDoctor(doctor);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Doctor saved successfully!");
        alert.showAndWait();
        handleClear();
    }

    @FXML
    private void handleClear() {
        idField.clear();
        nameField.clear();
        specialtyField.clear();
        locationField.clear();
        gradeField.clear();
    }
}
