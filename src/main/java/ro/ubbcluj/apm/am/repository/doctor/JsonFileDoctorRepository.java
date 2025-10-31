package ro.ubbcluj.apm.am.repository.doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.FileRepository;
import ro.ubbcluj.apm.am.util.FileUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class JsonFileDoctorRepository extends FileRepository<Integer, Doctor> {
    private final ObjectMapper objectMapper;

    public JsonFileDoctorRepository() throws IOException {
        this.filePath = ApplicationConfig.getInstance().getDoctorRepositoryResourcePath("json");
        this.objectMapper = new ObjectMapper();
        readFromFile();
    }

    @Override
    protected void readFromFile() throws IOException {
        map.clear();

        try (InputStream is = JsonFileDoctorRepository.class.getResourceAsStream(this.filePath)) {
            Doctor[] doctors = objectMapper.readValue(is, Doctor[].class);
            for (Doctor doctor : doctors) {
                map.put(doctor.getId(), doctor);
            }
        }
    }

    @Override
    protected void writeToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileUtil.getFilePath(this.filePath)))) {
            String json = objectMapper.writeValueAsString(map.values());
            writer.write(json);
        }
    }
}
