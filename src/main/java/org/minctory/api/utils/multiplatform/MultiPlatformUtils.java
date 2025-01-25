package org.minctory.api.utils.multiplatform;

import java.io.File;

public class MultiPlatformUtils {

    public static String constructPath(String... paths) {
        if (paths.length == 0) throw new IllegalArgumentException("Paths must not be empty!");
        StringBuilder path = new StringBuilder();
        for (String p : paths) {
            path.append(p).append(File.separator);
        }
        return path.toString();
    }

}
