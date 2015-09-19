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

    public Integer findLatest(boolean bit64) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            String label = map.get("LABEL").asText();
            if (bit64) {
                if (label.contains("Latest")&& label.contains("64 bit")) {
                    return map.get("KERNELID").asInt();
                }
            } else {
                if (label.contains("Latest") && label.contains("32 bit")) {
                    return map.get("KERNELID").asInt();
                }
            }
        }
        return null;
    }

    public KernelMapper(JsonNode context) {
        this.context = context;
    }
}
