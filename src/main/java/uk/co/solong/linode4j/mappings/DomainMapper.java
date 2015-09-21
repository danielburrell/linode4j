package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class DomainMapper {
    private final JsonNode context;

    public Integer getDomainIdFromDomainName(String domainName) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            if (domainName.equals(map.get("DOMAIN").asText())) {
                return map.get("DOMAINID").asInt();
            }
        }
        return null;
    }

    public DomainMapper(JsonNode context) {
        this.context = context;
    }

}
