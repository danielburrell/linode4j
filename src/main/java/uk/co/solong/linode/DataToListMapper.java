package uk.co.solong.linode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.notedpath.linode.LinodeResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by terabyte on 24/12/2014.
 */
public class DataToListMapper<T> {
    public List<T> mapDataListToClass(LinodeResponse r, Class<T> t) throws IOException {
        JsonNode dataArray = r.getData();
        if (dataArray.isArray()) {
            ObjectMapper m = new ObjectMapper();
            ObjectReader reader = m.reader(t);
            List<T> list = new ArrayList<T>();
            for (final JsonNode node : dataArray) {
                T dc = reader.readValue(node);
                list.add(dc);
            }
            return list;
        } else {
            ObjectMapper m = new ObjectMapper();
            ObjectReader reader = m.reader(t);
            List<T> list = new ArrayList<T>();

                T dc = reader.readValue(dataArray);
                list.add(dc);

            return list;
        }
    }
}
