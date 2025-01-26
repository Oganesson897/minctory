package org.minctory.api.save.detect;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.minctory.Minctory;
import org.minctory.api.save.Save;
import org.minctory.api.utils.MapUtils;
import org.minctory.api.utils.logger.Printer;
import org.minctory.api.utils.parsers.JSONParser;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SaveDetector {

    private static final File SAVES_DIR = new File(Minctory.DATA_DIR, "saves");
    private static final File INFO = new File(SAVES_DIR, "info.json");
    public static final Object2ObjectOpenHashMap<UUID, Save> SAVES = new Object2ObjectOpenHashMap<>();

    public static File getSaveDir(Save save) {
        return new File(SAVES_DIR, MapUtils.getKeyByValue(SAVES, save).toString());
    }

    public static File getSaveDir() {
        return getSaveDir(Minctory.INSTANCE.currentSave);
    }

    @SuppressWarnings("unchecked")
    public static void detect() {
        if (SAVES_DIR.isDirectory()) {
            if (INFO.isFile()) {
                JSONParser.fromJson(INFO).forEach((key, value) -> {
                    UUID uuid = UUID.fromString(key);
                    Save save = new Save((Map<String, Object>) value);
                    SAVES.put(uuid, save);
                });
            } else {
                for (File file : SAVES_DIR.listFiles(File::isDirectory)) {
                    UUID uuid = UUID.fromString(file.getName());
                    Save save = new Save(file.getName(), file.lastModified());
                    SAVES.put(uuid, save);
                }
            }
        } else {
            SAVES_DIR.mkdirs();
        }
        saveConfig();
    }

    public static void saveSelect(Printer printer) {
        if (SAVES.isEmpty()) {
            printer.print("No saves detected.");
            if (printer.enter("Press enter to create an universe...")) {
                var name = printer.asking("Enter a name for the universe: ");
                Save newSave = new Save(name, System.currentTimeMillis());
                SAVES.put(UUID.randomUUID(), newSave);
                Minctory.INSTANCE.currentSave = newSave;
            } else {
                printer.print("Goodbye!");
                System.exit(0);
            }
        } else {
            printer.print("%s saves detected.".formatted(SAVES.size()));
            List<Save> sortedSave = new ArrayList<>(SAVES.values());
            sortedSave.sort(Comparator.comparingLong(Save::getLastPlay));
            for (Save save : sortedSave) {
                printer.print("No.%s | %s".formatted(sortedSave.indexOf(save), save.toString()));
            }
            if (printer.enter("Select a universe or press enter to create a new universe...")) {
                int index = printer.askingInt("Enter the number of the universe: ");
                Minctory.INSTANCE.currentSave = sortedSave.get(index);
            } else {
                var name = printer.asking("Enter a name for the universe: ");
                Save newSave = new Save(name, System.currentTimeMillis());
                SAVES.put(UUID.randomUUID(), newSave);
                Minctory.INSTANCE.currentSave = newSave;
            }
        }
    }

    public static void saveConfig() {
        if (SaveDetector.SAVES.isEmpty()) {
            try {
                INFO.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Map<String, Object> map = new HashMap<>();
            SAVES.forEach((uuid, save) -> {
                SaveInfo info = new SaveInfo(uuid, save);
                map.putAll(info.toMap());
            });
            try {
                JSONParser.toJson(INFO, map);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
