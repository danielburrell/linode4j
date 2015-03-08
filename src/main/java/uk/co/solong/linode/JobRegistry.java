package uk.co.solong.linode;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JobRegistry {
    private ConcurrentHashMap<Job, StateAndResponse> jobStates;
    private ConcurrentSkipListSet<Job> jobQueue;
    
    public StateAndResponse getJobState(Job job) throws InterruptedException {
        while (!jobStates.containsKey(job)) {
            jobQueue.add(job);
            wait();
        }
        return jobStates.get(job);
    }
  //schedule a lookup every 10 seconds
    public JobRegistry(String apiKey) {
        jobQueue = new ConcurrentSkipListSet<Job>();
        jobStates = new ConcurrentHashMap<Job, StateAndResponse>();
        Runnable lookup = new JobLookup(jobQueue,jobStates, apiKey);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(lookup, 0, 10, TimeUnit.SECONDS);
    }
    
    
}
