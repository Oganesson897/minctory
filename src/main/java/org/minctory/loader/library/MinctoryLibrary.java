package org.minctory.loader.library;

import org.minctory.Minctory;
import org.minctory.api.resources.ResourceReader;
import org.minctory.api.utils.logger.MinctoryLogger;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import static org.minctory.api.utils.multiplatform.MultiPlatformUtils.constructPath;

public class MinctoryLibrary {

    public static final File LIB_DIR = new File(Minctory.DATA_DIR, "libraries");

    public static void load() {
        LIB_DIR.mkdirs();

        // Load libraries
        ResourceReader reader = new ResourceReader("dependencies.txt");
        try (InputStream inputStream = reader.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 0: Group ID, 1: Artifact ID, 2: Version
                var dependency = line.split(":");
                var groupFolder = new File(LIB_DIR, constructPath(dependency[0].split("\\.")));
                if (groupFolder.mkdirs()) {
                    MinctoryLogger.LOGGER_MAIN.info("Created folder: {}", groupFolder);
                    loadJar(loadJar(dependency, groupFolder));
                }
            }
        } catch (Exception e) {
            MinctoryLogger.LOGGER_MAIN.error("Failed to load libraries", e);
        }
    }

    private static String loadJar(String[] dependency, File dir) throws IOException {
        var jarUrl = LibraryDownload.generateMavenUrl(dependency[0], dependency[1], dependency[2]);
        return LibraryDownload.downloadJar(jarUrl, new File(dir, "%s-%s.jar".formatted(dependency[1], dependency[2])).getAbsolutePath());
    }

    private static void loadJar(String jarPath) {
        try {
            final URL url = new File(jarPath).toURI().toURL();
            final Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            final URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            addURL.invoke(classLoader, url);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load file %s to classpath!".formatted(jarPath), e);
        }
    }

}
