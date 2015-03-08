package uk.co.solong.linode;

import com.notedpath.linode.LinodeResponse;

public class StateAndResponse {
    private State state;
    private LinodeResponse linodeResponse;
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public LinodeResponse getLinodeResponse() {
        return linodeResponse;
    }
    public void setLinodeResponse(LinodeResponse linodeResponse) {
        this.linodeResponse = linodeResponse;
    } 
}
