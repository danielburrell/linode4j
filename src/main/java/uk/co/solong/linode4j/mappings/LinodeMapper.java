package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class LinodeMapper {

    private final JsonNode context;

    public int getLinodeIdFromLabel(String abbr) {
        JsonNode data = context.get("DATA");
        for (JsonNode linode : data) {
            if (abbr.equals(linode.get("LABEL").asText())) {
                return linode.get("LINODEID").asInt();
            }
        }
        throw new UnknownMapping("Unknown linode label " + abbr);
    }

    public LinodeMapper(JsonNode context) {
        this.context = context;
    }
}
