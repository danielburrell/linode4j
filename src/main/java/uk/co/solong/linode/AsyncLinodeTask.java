package uk.co.solong.linode;

import com.notedpath.linode.API_ACTION;
import com.notedpath.linode.Linode;
import com.notedpath.linode.LinodeResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by terabyte on 26/12/2014.
 */
public class AsyncLinodeTask<T extends JobListable<?>> implements Future<StateAndResponse> {
    private final T data;
    //private final Linode linode;
    private JobRegistry jobRegistry;
    private boolean done = false;
    AsyncLinodeTask(JobRegistry jobRegistry, T data){
        this.jobRegistry = jobRegistry;
        this.data = data;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public StateAndResponse get() throws InterruptedException {
        Job j = new Job();
        j.setJobId(data.getJobId());
        j.setLinodeId(data.getLinodeId());
        while (State.RUNNING.equals(jobRegistry.getJobState(j).getState())) {
            wait();
        }
        return jobRegistry.getJobState(j);
    }

    @Override
    public StateAndResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new RuntimeException("not implemented yet");
    }

   
}
