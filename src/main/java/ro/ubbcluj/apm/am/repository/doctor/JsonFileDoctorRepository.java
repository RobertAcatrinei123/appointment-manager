package ro.ubbcluj.apm.am.repository.doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.FileRepository;
import ro.ubbcluj.apm.am.util.FileType;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class JsonFileDoctorRepository extends FileRepository<Doctor, Integer> {
    private final ObjectMapper objectMapper;

    public JsonFileDoctorRepository() throws IOException {
        filePath = ApplicationConfig.getInstance().getDoctorRepositoryFilePath(FileType.JSON);
        objectMapper = new ObjectMapper();
        readFromFile();
    }

    @Override
    protected void readFromFile() throws IOException {
        map.clear();

        try (InputStream is = Files.newInputStream(filePath)) {
            Doctor[] doctors = objectMapper.readValue(is, Doctor[].class);
            for (Doctor doctor : doctors) {
                map.put(doctor.getId(), doctor);
            }
        }
    }

    @Override
    protected void writeToFile() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            String json = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(map.values());
            writer.write(json);
        }
    }
}
