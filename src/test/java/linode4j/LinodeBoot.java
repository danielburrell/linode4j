package linode4j;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import uk.co.solong.linode4j.Linode;
import uk.co.solong.linode4j.bootlinode.BootLinodeResult;

public class LinodeBoot {

    @Test
    public void test() {
        Linode t = new Linode("apiKey");
        BootLinodeResult node = t.bootLinode(1).withConfigId(2).go();
        
    }

}
