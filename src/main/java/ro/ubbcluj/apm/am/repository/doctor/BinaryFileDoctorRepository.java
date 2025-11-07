package ro.ubbcluj.apm.am.repository.doctor;

import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.FileRepository;
import ro.ubbcluj.apm.am.util.FileType;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class BinaryFileDoctorRepository extends FileRepository<Doctor, Integer> {

    public BinaryFileDoctorRepository() throws IOException {
        this.filePath = ApplicationConfig.getInstance().getDoctorRepositoryFilePath(FileType.BINARY);
        readFromFile();
    }

    @Override
    protected void readFromFile() throws IOException {
        map.clear();

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filePath))) {
            Map<Integer, Doctor> loadedMap = (Map<Integer, Doctor>) ois.readObject();
            map.putAll(loadedMap);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e) {
            // ignore the file is empty
        }

    }

    @Override
    protected void writeToFile() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filePath, StandardOpenOption.TRUNCATE_EXISTING))) {
            oos.writeObject(map);
        }
    }
}
