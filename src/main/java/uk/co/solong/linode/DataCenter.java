package uk.co.solong.linode;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by terabyte on 23/12/2014.
 */
public class DataCenter {
    private int dataCenterId;
    private String location;
    private String abbr;

    public int getDataCenterId() {
        return dataCenterId;
    }
    @JsonProperty("DATACENTERID")
    public void setDataCenterId(int dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public String getLocation() {
        return location;
    }
    @JsonProperty("LOCATION")
    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbbr() {
        return abbr;
    }
    @JsonProperty("ABBR")
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
}
