package uk.co.solong.linode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.notedpath.linode.Linode;
import com.notedpath.linode.LinodeResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by terabyte on 23/12/2014.
 */
public class LinodeTemplate {
    private Linode linode;

    public LinodeTemplate(String api) {
        linode = new Linode(api);
    }

    public List<DataCenter> getAvailableDataCenters() throws IOException {
        LinodeResponse r = linode.availableDatacenters();
        JsonNode dataArray = r.getData();
        if (dataArray.isArray()) {
            ObjectMapper m = new ObjectMapper();
            ObjectReader reader = m.reader(DataCenter.class);
            List<DataCenter> list = new ArrayList<DataCenter>();
            for (final JsonNode node : dataArray) {
                DataCenter dc = reader.readValue(node);
                list.add(dc);
            }
            return list;
        } else {
            throw new RuntimeException("Expected datacenter list, only 1 element returned");
        }
    }
}
