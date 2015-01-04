package uk.co.solong.linode;

import java.util.List;

/**
 * Created by terabyte on 25/12/2014.
 */
public class DistributionMapper {
    private final List<Distribution> list;
    public DistributionMapper(List<Distribution> list) {
        this.list = list;
    }
    public int getDistributionIdFromLabel(String abbr) {
        for (Distribution dc:list){
            if (abbr.equals(dc.getLabel())) {
                return dc.getDistributionId();
            }
        }
        return -1;
    }
}
