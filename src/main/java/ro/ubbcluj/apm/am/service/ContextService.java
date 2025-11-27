package ro.ubbcluj.apm.am.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import ro.ubbcluj.apm.am.AppointmentManagerApplication;
import ro.ubbcluj.apm.am.controller.DoctorListController;
import ro.ubbcluj.apm.am.repository.appointment.AppointmentRepository;
import ro.ubbcluj.apm.am.repository.doctor.DoctorRepository;
import ro.ubbcluj.apm.am.service.action.HistoryService;

public class ContextService {
    private static ContextService instance;
    private Parent mainView;
    private Node doctorListView;
    private DoctorListController doctorListController;
    private Node doctorAddView;
    private DoctorService doctorService;

    public static ContextService getInstance() {
        if (instance == null) {
            instance = new ContextService();
        }
        return instance;
    }


    public DoctorService getDoctorService() {
        if (doctorService == null) {
            doctorService = new DoctorService(new DoctorRepository(), new AppointmentService(new AppointmentRepository(), new HistoryService()), new HistoryService());
        }
        return doctorService;
    }

    @SneakyThrows
    public Parent getMainView() {
        if (mainView == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(AppointmentManagerApplication.class.getResource("/views/main-view.fxml"));
            mainView = fxmlLoader.load();
        }
        return mainView;
    }

    @SneakyThrows
    public Node getDoctorListView() {
        if (doctorListView == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/doctor-list-view.fxml"));
            doctorListView = loader.load();
            doctorListController = loader.getController();
        }
        doctorListController.refresh();
        return doctorListView;
    }

    @SneakyThrows
    public Node getDoctorAddView() {
        if (doctorAddView == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/doctor-add-view.fxml"));
            doctorAddView = loader.load();
        }
        return doctorAddView;
    }
}
