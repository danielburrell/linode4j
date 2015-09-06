package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class DistributionMapper {

    private final JsonNode context;

    public int getDistributionIdFromOs(String os) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            if (os.equals(map.get("LABEL").asText())) {
                return map.get("DISTRIBUTIONID").asInt();
            }
        }
        throw new UnknownMapping("Unknown OS " + os);
    }

    public DistributionMapper(JsonNode context) {
        this.context = context;
    }
}
