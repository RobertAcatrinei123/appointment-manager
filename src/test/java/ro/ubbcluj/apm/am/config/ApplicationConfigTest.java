package ro.ubbcluj.apm.am.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationConfigTest {

    @Test
    void getCsvFileDoctorRepositoryPath_withSuccess() {
        String path = ApplicationConfig.getInstance().getDoctorRepositoryResourcePath("csv");

        assertEquals("/data/doctors.csv", path);
    }
}