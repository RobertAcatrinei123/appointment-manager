package ro.ubbcluj.apm.am.repository.doctor;

import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.FileRepository;
import ro.ubbcluj.apm.am.util.FileType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CsvFileDoctorRepository extends FileRepository<Doctor, Integer> {

    public CsvFileDoctorRepository() throws IOException {
        filePath = ApplicationConfig.getInstance().getDoctorRepositoryFilePath(FileType.CSV);
        readFromFile();
    }

    @Override
    protected void readFromFile() throws IOException {
        map.clear();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
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
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING)) {
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
