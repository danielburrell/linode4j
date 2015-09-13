package uk.co.solong.linode4j.jobmanager;

import com.fasterxml.jackson.databind.JsonNode;

import uk.co.solong.linode4j.Linode;
import uk.co.solong.linode4j.mappings.JobMapper;

public class JobManager {
    private final Linode linode;

    public JobManager(Linode linode) {
        this.linode = linode;
    }
    
    public JsonNode waitForJob(int linodeId, int jobId) {
        JsonNode jobResult = null;
        do {
            JsonNode result = linode.listJobs(linodeId).withJobId(jobId).asJson();
            JobMapper jobMapper = new JobMapper(result);
            jobResult = jobMapper.getJobDataFromJobId(jobId);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (jobResult.get("HOST_SUCCESS").asInt() != 1);
        return jobResult;
    }
    
    public JsonNode getJobStatus(int linodeId, int jobId) {
        JsonNode result = linode.listJobs(linodeId).withJobId(jobId).asJson();
        JobMapper jobMapper = new JobMapper(result);
        JsonNode jobResult = jobMapper.getJobDataFromJobId(jobId);
        return jobResult;
    }
}
