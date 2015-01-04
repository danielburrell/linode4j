package uk.co.solong.linode;

import java.util.List;

/**
 * Created by terabyte on 24/12/2014.
 */
public class DataCenterMapper {
    private final List<DataCenter> list;
    public DataCenterMapper(List<DataCenter> list) {
        this.list = list;
    }
    public int getDataCenterIdFromAbbr(String abbr) {
        for (DataCenter dc:list){
            if (abbr.equals(dc.getAbbr())) {
                return dc.getDataCenterId();
            }
        }
        return -1;
    }
}
