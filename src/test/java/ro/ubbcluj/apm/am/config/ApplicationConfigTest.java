package ro.ubbcluj.apm.am.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationConfigTest {

    @Disabled
    @Test
    void getCsvFileDoctorRepositoryPath_withSuccess() {
        String path = ApplicationConfig.getInstance().getCsvFileDoctorRepositoryPath();

        assertEquals("/data/doctors-test.txt", path);
    }
}