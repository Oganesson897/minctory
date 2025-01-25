package org.minctory.api.utils;

import static org.minctory.api.utils.multiplatform.MultiPlatformUtils.constructPath;

public class AppDataUtils {

    /**
     * Get the directory where the application data is stored.
     * @param appName The name of the application.
     * @return The directory where the application data is stored.
     */
    public static String getAppDataDirectory(String appName) {
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");

        if (os.contains("win")) {
            // Windows: %APPDATA%
            String appData = System.getenv("APPDATA");
            return appData != null ? constructPath(appData, appName) : userHome;
        } else if (os.contains("mac")) {
            // macOS: ~/Library/Application Support
            return constructPath(userHome, "Library", "Application Support", appName);
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            // Linux/Unix: ~/.config
            return constructPath(userHome, ".config", appName);
        } else {
            return userHome;
        }
    }

}
