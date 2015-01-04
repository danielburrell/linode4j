package uk.co.solong.linode;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by terabyte on 25/12/2014.
 */
public class Distribution {
    private boolean os64bit;
    private String label;
    private int minImageSize;
    private int distributionId;
    private String createDate;
    private boolean requiresPvopsKernel;

    public boolean isOs64bit() {
        return os64bit;
    }

    @JsonProperty("IS64BIT")
    public void setOs64bit(boolean os64bit) {
        this.os64bit = os64bit;
    }

    public String getLabel() {
        return label;
    }

    @JsonProperty("LABEL")
    public void setLabel(String label) {
        this.label = label;
    }

    public int getMinImageSize() {
        return minImageSize;
    }

    @JsonProperty("MINIMAGESIZE")
    public void setMinImageSize(int minImageSize) {
        this.minImageSize = minImageSize;
    }

    public int getDistributionId() {
        return distributionId;
    }

    @JsonProperty("DISTRIBUTIONID")
    public void setDistributionId(int distributionId) {
        this.distributionId = distributionId;
    }

    public String getCreateDate() {
        return createDate;
    }

    @JsonProperty("CREATEDATE")
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isRequiresPvopsKernel() {
        return requiresPvopsKernel;
    }

    @JsonProperty("REQUIRESPVOPSKERNEL")
    public void setRequiresPvopsKernel(boolean requiresPvopsKernel) {
        this.requiresPvopsKernel = requiresPvopsKernel;
    }


}
