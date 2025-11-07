package ro.ubbcluj.apm.am.config;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.util.FileType;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationConfigTest {

    @Test
    void getCsvFileDoctorRepositoryPath_withSuccess() {
        Path filePath = ApplicationConfig.getInstance().getDoctorRepositoryFilePath(FileType.CSV);

        assertThat(filePath).endsWith(Path.of("data/doctors.csv"));
    }

    @Test
    void getBinaryFileDoctorRepositoryPath_withSuccess() {
        Path filePath = ApplicationConfig.getInstance().getDoctorRepositoryFilePath(FileType.BINARY);

        assertThat(filePath).endsWith(Path.of("data/doctors.bin"));
    }

    @Test
    void getSqliteFileDoctorRepositoryPath_withSuccess() {
        Path filePath = ApplicationConfig.getInstance().getDoctorRepositoryFilePath(FileType.SQLITE);

        assertThat(filePath).endsWith(Path.of("data/doctors.db"));
    }
}