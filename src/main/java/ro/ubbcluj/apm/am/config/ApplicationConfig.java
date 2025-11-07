package ro.ubbcluj.apm.am.config;

import ro.ubbcluj.apm.am.util.FileType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ApplicationConfig {
    private static ApplicationConfig instance;
    private final Properties properties;

    private ApplicationConfig(Properties properties) {
        this.properties = properties;
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            Properties prop = new Properties();
            try {
                InputStream inputStream = ApplicationConfig.class.getResourceAsStream("/settings.properties");
                if (inputStream == null) {
                    throw new FileNotFoundException("Property file was not found in the classpath");
                }
                prop.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            instance = new ApplicationConfig(prop);
        }
        return instance;
    }

    public Path getDoctorRepositoryFilePath(FileType fileType) {
        String relativePath = properties.getProperty(String.format("doctor-repository.relative-path.%s", fileType));
        String currentFolderPath = System.getProperty("user.dir");
        return Paths.get(currentFolderPath, relativePath);
    }
}
