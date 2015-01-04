package uk.co.solong.linode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.notedpath.linode.API_ACTION;
import com.notedpath.linode.Linode;
import com.notedpath.linode.LinodeResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by terabyte on 25/12/2014.
 */
public class CreateDiskResponse {

    //make this class generic with the inner type parameterised as well as the return type.
    //make the constructor private without an api key and parameterised type.
    private long diskId;

    public long getDiskId() {
        return diskId;
    }

    public void setDiskId(long diskId) {
        this.diskId = diskId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    private int jobId;

}
