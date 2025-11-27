package ro.ubbcluj.apm.am;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.ubbcluj.apm.am.service.ContextService;

public class AppointmentManagerApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Appointment Manager");
        stage.setScene(new Scene(ContextService.getInstance().getMainView(), 800, 600));
        stage.show();
    }
}
