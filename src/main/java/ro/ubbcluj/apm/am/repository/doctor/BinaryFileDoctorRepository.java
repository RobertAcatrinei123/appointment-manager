package ro.ubbcluj.apm.am.repository.doctor;

import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.FileRepository;
import ro.ubbcluj.apm.am.util.FileUtil;

import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class BinaryFileDoctorRepository extends FileRepository<Integer, Doctor> {

    public BinaryFileDoctorRepository() throws IOException {
        this.filePath = ApplicationConfig.getInstance().getDoctorRepositoryResourcePath("binary");
        readFromFile();
    }

    @Override
    protected void readFromFile() throws IOException {
        map.clear();

        try (InputStream is = BinaryFileDoctorRepository.class.getResourceAsStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(is)) {

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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileUtil.getFilePath(filePath)))) {
            oos.writeObject(map);
        }
    }
}
