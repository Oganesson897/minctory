package org.minctory.api.utils.parsers;

import club.someoneice.json.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JSONParser {

    private static final JSON JSON5 = JSON.json5;

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd;HH:mm:ss")
            .create();

    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJson(File file) {
        return (Map<String, Object>) GSON.fromJson(JSON5.parse(file).toString(), Map.class);
    }

    public static void toJson(File file, Object object) throws IOException {
        GSON.toJson(object, new FileWriter(file));
    }

}
