package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class DiskMapper {

    private final JsonNode context;

    public int getDiskIdFromLabel(String label) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            if (label.equals(map.get("LABEL").asText())) {
                return map.get("DISKID").asInt();
            }
        }
        throw new UnknownMapping("Unknown disk label" + label);
    }

    public DiskMapper(JsonNode context) {
        this.context = context;
    }
}
