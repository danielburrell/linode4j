package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class KernelMapper {

    private final JsonNode context;

    public Integer getKernelIdFromLabel(String label) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            if (label.equals(map.get("LABEL").asText())) {
                return map.get("KERNELID").asInt();
            }
        }
        return null;
    }

    public KernelMapper(JsonNode context) {
        this.context = context;
    }
}
