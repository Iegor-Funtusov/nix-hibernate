package ua.com.alevel.hibernate.util;

import lombok.Getter;

import java.io.File;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:49 PM
 */

public class UploadUtil {

    public static String getPath(Folder folder) {
        return new File("").getAbsoluteFile() + "/" + folder.getFolder();
    }

    @Getter
    public enum Folder {

        CSV("csv");

        Folder(String folder) {
            this.folder = folder;
        }

        private final String folder;
    }
}
