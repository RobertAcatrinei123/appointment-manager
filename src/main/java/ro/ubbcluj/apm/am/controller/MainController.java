package ro.ubbcluj.apm.am.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import ro.ubbcluj.apm.am.service.ContextService;

public class MainController {
    @FXML
    public Pane content;

    @FXML
    public void onExitMenuClick() {
        System.exit(0);
    }

    @FXML
    public void onDoctorListMenuClick() {
        content.getChildren().setAll(ContextService.getInstance().getDoctorListView());
    }

    @FXML
    public void onDoctorAddMenuClick() {
        content.getChildren().setAll(ContextService.getInstance().getDoctorAddView());
    }
}
