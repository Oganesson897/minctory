package org.minctory.loader.library;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class LibraryDownload {

    public static String generateMavenUrl(String groupId, String artifactId, String version) {
        String groupPath = groupId.replace('.', '/');
        return "https://repo1.maven.org/maven2/%s/%s/%s/%s-%s.jar".formatted(groupPath, artifactId, version, artifactId, version);
    }

    public static String downloadJar(String jarUrl, String saveFilePath) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(jarUrl).build();

            httpclient.execute(httpGet, response -> {
                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    try (FileOutputStream fos = new FileOutputStream(saveFilePath)) {
                        entity.writeTo(fos);
                    }
                    EntityUtils.consume(entity);
                }
                return null;
            });

            return saveFilePath;
        }
    }

}
