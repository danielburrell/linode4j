package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class PlanMapper {

    private final JsonNode context;

    public PlanMapper(JsonNode context) {
        this.context = context;
    }

    public int getPlanIdFromRam(int requiredRam) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            int ram = map.get("RAM").asInt();
            if (ram == requiredRam) {
                return map.get("PLANID").asInt();
            }
        }
        throw new UnknownMapping("No plan with ram "+requiredRam);
    }
}
