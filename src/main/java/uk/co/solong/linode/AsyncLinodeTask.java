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
public class AsyncLinodeTask<T extends CreateDiskResponse, R> implements Future<R> {
    private final T data;
    private final String apiKey;
    private final Linode linode;
    private boolean done = false;
    AsyncLinodeTask(String apiKey, T data){
        this.data = data;
        this.apiKey = apiKey;
        linode = new Linode(apiKey);
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
    public R get() throws InterruptedException, ExecutionException {
        //refactor this crap code as "while job i'm looking for in map not in completed or failed state, block if not available with notify all to wake to check.
        //while the map object is wrapped by a class which populates the map and actually performs a single list job based call.
        try {
            while (!done) {
                LinodeResponse r = linode.execute(API_ACTION.LINODE_JOB_LIST, Long.toString(data.getDiskId()), Integer.toString(data.getJobId()));
                if (r.getData().get("HOST_SUCCESS").asBoolean(false)) {
                    return true;
                }
                Thread.sleep(10000L);
            }

        } catch (IOException e) {
            throw new ExecutionException(e);
        }
    }

    @Override
    public R get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
