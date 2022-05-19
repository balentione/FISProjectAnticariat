package org.loose.fis.sre.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    public static String APPLICATION_FOLDER = "/Users/gabriel-claudiuprivantu/Documents/FACULTATE AC_CTIro/AN2/SEM2/FIS/database1/registration-example";
    ///Users/gabriel-claudiuprivantu/Documents/FACULTATE AC_CTIro/AN2/SEM2/FIS/registration-example
    ///Users/gabriel-claudiuprivantu/Documents/FACULTATE AC_CTIro/AN2/SEM2/proba_Java/registration-example
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }

    public static void initDirectory() {
        Path applicationHomePath = APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
}
