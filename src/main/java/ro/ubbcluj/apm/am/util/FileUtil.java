package ro.ubbcluj.apm.am.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {
    public static String getFilePath(String resourcePath) throws FileNotFoundException {
        URL url = FileUtil.class.getResource(resourcePath);
        if (url == null) {
            throw new FileNotFoundException("File not found: " + resourcePath);
        }
        return url.getPath();
    }
}
