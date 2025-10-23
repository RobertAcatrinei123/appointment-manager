package ro.ubbcluj.apm.am.repository.doctor;

import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.FileRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class CsvFileDoctorRepository extends FileRepository<Integer, Doctor> {

    public CsvFileDoctorRepository() throws IOException {
        this.filePath = ApplicationConfig.getInstance().getCsvFileDoctorRepositoryPath();
        readFromFile();
    }

    @Override
    protected void readFromFile() throws IOException {
        map.clear();

        try (InputStream is = CsvFileDoctorRepository.class.getResourceAsStream(this.filePath)) {
            if (is == null) {
                throw new FileNotFoundException("File not found: " + this.filePath);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            boolean firstLine = true;
            List<String> lines = reader.lines().toList();
            for (String line : lines) {
                if (!firstLine) {
                    String[] columns = line.split(",");
                    Doctor doctor = new Doctor(Integer.parseInt(columns[0]), columns[1], columns[2], columns[3], Double.parseDouble(columns[4]));
                    map.put(doctor.getId(), doctor);
                }
                firstLine = false;
            }
        }
    }

    @Override
    protected void writeToFile() throws IOException {
        URL url = CsvFileDoctorRepository.class.getResource(this.filePath);
        if (url == null) {
            throw new FileNotFoundException("File not found: " + this.filePath);
        }
        String path = url.getPath();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("id,name,specialty,location,grade");
            writer.newLine();
            for (Doctor doctor : map.values()) {
                writer.write(String.valueOf(doctor.getId()));
                writer.write(",");
                writer.write(doctor.getName());
                writer.write(",");
                writer.write(doctor.getSpecialty());
                writer.write(",");
                writer.write(doctor.getLocation());
                writer.write(",");
                writer.write(String.valueOf(doctor.getGrade()));
                writer.newLine();
            }
        }
    }

    @Override
    public Doctor add(Doctor doctor) {
        Doctor addedDoctor = super.add(doctor);
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return addedDoctor;
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
