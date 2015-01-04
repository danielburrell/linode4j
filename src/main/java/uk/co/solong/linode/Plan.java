package uk.co.solong.linode;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by terabyte on 24/12/2014.
 */
public class Plan {
    private int cores;
    private BigDecimal price;
    private int ram;
    private int transfer;
    private int planId;
    private String label;
    private Map<String, Integer> avail;
    private int disk;

    public int getCores() {
        return cores;
    }
    @JsonProperty("CORES")
    public void setCores(int cores) {
        this.cores = cores;
    }

    public BigDecimal getPrice() {
        return price;
    }
    @JsonProperty("PRICE")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRam() {
        return ram;
    }
    @JsonProperty("RAM")
    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getTransfer() {
        return transfer;
    }
    @JsonProperty("XFER")
    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public int getPlanId() {
        return planId;
    }
    @JsonProperty("PLANID")
    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getLabel() {
        return label;
    }
    @JsonProperty("LABEL")
    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, Integer> getAvail() {
        return avail;
    }
    @JsonProperty("AVAIL")
    public void setAvail(Map<String, Integer> avail) {
        this.avail = avail;
    }

    public int getDisk() {
        return disk;
    }
    @JsonProperty("DISK")
    public void setDisk(int disk) {
        this.disk = disk;
    }

    public BigDecimal getHourly() {
        return hourly;
    }
    @JsonProperty("HOURLY")
    public void setHourly(BigDecimal hourly) {
        this.hourly = hourly;
    }

    private BigDecimal hourly;

}
