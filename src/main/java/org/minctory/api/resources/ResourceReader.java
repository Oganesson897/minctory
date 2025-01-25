package org.minctory.api.resources;

import java.io.InputStream;
import java.net.URL;

public class ResourceReader {
    private final String path;

    public ResourceReader(String path) {
        this.path = path;
    }

    public InputStream getInputStream() {
        return getClass().getClassLoader().getResourceAsStream(path);
    }

    public URL getURL() {
        return getClass().getClassLoader().getResource(path);
    }

}
