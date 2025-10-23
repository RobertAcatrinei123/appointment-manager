package ro.ubbcluj.apm.am.repository.doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.FileRepository;

import java.io.IOException;
import java.io.InputStream;

public class JsonFileDoctorRepository extends FileRepository<Integer, Doctor> {

    public JsonFileDoctorRepository() throws IOException {
        this.filePath = ApplicationConfig.getInstance().getJsonFileDoctorRepositoryPath();
        readFromFile();
    }

    @Override
    protected void readFromFile() throws IOException {
        map.clear();

        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = JsonFileDoctorRepository.class.getResourceAsStream(this.filePath)) {
            Doctor[] doctors = objectMapper.readValue(is, Doctor[].class);
            for (Doctor doctor : doctors) {
                map.put(doctor.getId(), doctor);
            }
        }
    }

    @Override
    protected void writeToFile() throws IOException {

    }

    @Override
    public Doctor add(Doctor value) {
        Doctor doctor = super.add(value);
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return doctor;
    }

    @Override
    public void update(Doctor value) {
        super.update(value);
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
