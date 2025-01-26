package org.minctory.api.save.detect;

import org.minctory.api.save.Save;

import java.util.Map;
import java.util.UUID;

public record SaveInfo(UUID uuid, Save save) {

    public Map<String, Object> toMap() {
        return Map.of(uuid.toString(), Map.of("name", save.getName(), "lastPlay", save.getLastPlay()));
    }

}
