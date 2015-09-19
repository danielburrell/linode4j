package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class LinodeConfigMapper {

    private final JsonNode context;

    public int getConfigIdFromLinodeId(String label) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            if (label.equals(map.get("Label").asText())) {
                return map.get("ConfigID").asInt();
            }
        }
        throw new UnknownMapping("Unknown config label " + label);

    }

    public LinodeConfigMapper(JsonNode context) {
        this.context = context;
    }

}
