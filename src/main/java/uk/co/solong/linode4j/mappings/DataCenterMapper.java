package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class DataCenterMapper {

    private final JsonNode context;
    public int getDataCenterIdFromAbbr(String abbr) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            if (abbr.equals(map.get("ABBR").asText())) {
                return map.get("DATACENTERID").asInt();
            }
        }
        throw new UnknownMapping("Unknown datacenter abbr " + abbr);
    }
   

    public DataCenterMapper(JsonNode context) {
        this.context = context;
    }
}
