package org.minctory.api.utils.parsers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

public class CSVParser {

    public static void forEach(File file, Consumer<String[]> consumer) {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            reader.forEach(consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void toFile(File file, Collection<String[]> objects) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
