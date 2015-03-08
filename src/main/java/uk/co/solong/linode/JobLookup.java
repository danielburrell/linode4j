package uk.co.solong.linode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.httpclient.HttpException;

import com.notedpath.linode.API_ACTION;
import com.notedpath.linode.Linode;
import com.notedpath.linode.LinodeRequest;
import com.notedpath.linode.LinodeResponse;

public class JobLookup implements Runnable {
    private ConcurrentSkipListSet<Job> jobQueue;
    private ConcurrentHashMap<Job, StateAndResponse> jobStates;

    private final Linode linode;
    public JobLookup(ConcurrentSkipListSet<Job> jobQueue, ConcurrentHashMap<Job, StateAndResponse> jobStates, String apiKey) {
        this.jobQueue = jobQueue;
        this.jobStates = jobStates;
        this.linode = new Linode(apiKey);
    }

    @Override
    public void run() {
        //create the initial lookup set based on the list of requested jobs
        Set<Job> lookupSet = new HashSet<Job>();
        lookupSet.addAll(jobQueue);
        jobQueue.removeAll(lookupSet);
        
        //TODO add the list of jobs which are currently running
        
        //generate a list of linode requests to form the batch of job lookups
        List<LinodeRequest> batch = new ArrayList<LinodeRequest>(lookupSet.size());
        for (Job job : lookupSet){
            LinodeRequest r = new LinodeRequest();
            r.setAction(API_ACTION.LINODE_JOB_LIST);
            r.setParameters(Long.toString(job.getLinodeId()),Integer.toString(job.getJobId()));
            batch.add(r);
        }
        try {
            List<LinodeResponse> r = linode.batchExecute(batch);
            for (LinodeResponse currentResponse : r){
                int jobId = currentResponse.getData().get(0).get("JOBID").asInt();
                int state = currentResponse.getData().get(0).get("HOST_SUCCESS").asInt();
                int linodeId = currentResponse.getData().get(0).get("LINODEID").asInt();
                //get job id
                //get state
                StateAndResponse resp = new StateAndResponse();
                resp.setLinodeResponse(currentResponse);
                if (state == 1) {
                    resp.setState(State.SUCCESS);
                } else {
                    resp.setState(State.RUNNING);
                }
                Job job = new Job();
                job.setJobId(jobId);
                job.setLinodeId(linodeId);
                jobStates.put(job, resp);
            }
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            notifyAll();
        }
        
    }

}
