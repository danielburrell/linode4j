package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class StackScriptMapper {

    private final JsonNode context;

    public Integer getStackScriptIdFromLabel(String label) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            if (label.equals(map.get("LABEL").asText())) {
                return map.get("STACKSCRIPTID").asInt();
            }
        }
        return null;
    }

    public StackScriptMapper(JsonNode context) {
        this.context = context;
    }

}
