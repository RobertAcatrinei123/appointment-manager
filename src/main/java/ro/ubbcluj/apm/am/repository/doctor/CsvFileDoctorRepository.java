package ro.ubbcluj.apm.am.repository.doctor;

import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.FileRepository;
import ro.ubbcluj.apm.am.util.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvFileDoctorRepository extends FileRepository<Integer, Doctor> {

    public CsvFileDoctorRepository() throws IOException {
        this.filePath = ApplicationConfig.getInstance().getDoctorRepositoryResourcePath("csv");
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileUtil.getFilePath(this.filePath)))) {
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
}
