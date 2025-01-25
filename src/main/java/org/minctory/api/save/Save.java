package org.minctory.api.save;

import java.util.Date;
import java.util.Map;

public record Save(String name, long lastPlay) {

    public Save(Map<String, Object> map) {
        this((String) map.get("name"), (long) map.get("lastPlay"));
    }

    public String toSaveString() {
        return "Universe Name: %s | Last Played: %s".formatted(name, new Date(lastPlay).toString());
    }

}
