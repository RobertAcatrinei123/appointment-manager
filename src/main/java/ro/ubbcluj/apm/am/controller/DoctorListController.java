package ro.ubbcluj.apm.am.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.service.ContextService;

import java.util.Collection;

public class DoctorListController {
    @FXML
    private TableView<Doctor> doctorTable;
    @FXML
    private TableColumn<Doctor, Long> idColumn;
    @FXML
    private TableColumn<Doctor, String> nameColumn;
    @FXML
    private TableColumn<Doctor, String> specialtyColumn;
    @FXML
    private TableColumn<Doctor, String> locationColumn;
    @FXML
    private TableColumn<Doctor, Double> gradeColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        specialtyColumn.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        refresh();
    }

    public void refresh() {
        doctorTable.setItems(ContextService.getInstance()
                .getDoctorService()
                .getAllDoctors()
                .stream()
                .collect(FXCollections::observableArrayList, Collection::add, Collection::addAll)
        );
    }
}
