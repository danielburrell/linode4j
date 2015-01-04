package uk.co.solong.linode;

import com.notedpath.linode.Linode;

/**
 * Created by terabyte on 26/12/2014.
 */
public class JobListDao {
    private Linode linode;

    private Map<Integer, LinodeJob> jobs;

    public State getState(int jobId) {
        //return true if the given job is in a success state, fail state, running, unknown (not found)
        //return jobs.
    }

    private void updateJobs() {
        
    }


}
