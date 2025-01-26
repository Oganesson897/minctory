package org.minctory.api.save;

import org.minctory.api.save.detect.SaveDetector;

import java.io.File;
import java.util.Date;
import java.util.Map;

public class Save {

    private final String name;
    private final long lastPlay;
    public final File saveFile;

    public Save(Map<String, Object> map) {
        this((String) map.get("name"), (long) map.get("lastPlay"));
    }

    public Save(String name, long lastPlay) {
        this.name = name;
        this.lastPlay = lastPlay;
        this.saveFile = SaveDetector.getSaveDir(this);
    }

    public String toSaveString() {
        return "Universe Name: %s | Last Played: %s".formatted(name, new Date(lastPlay).toString());
    }

    public String getName() {
        return name;
    }

    public long getLastPlay() {
        return lastPlay;
    }

}
