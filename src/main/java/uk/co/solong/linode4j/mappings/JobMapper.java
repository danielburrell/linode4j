package uk.co.solong.linode4j.mappings;

import com.fasterxml.jackson.databind.JsonNode;

public class JobMapper {

    private final JsonNode context;

    public JsonNode getJobDataFromJobId(int jobId) {
        JsonNode data = context.get("DATA");
        for (JsonNode map : data) {
            if (jobId == (map.get("JOBID").asInt())) {
                return map;
            }
        }
        return null;
    }

    public JobMapper(JsonNode context) {
        this.context = context;
    }

}
