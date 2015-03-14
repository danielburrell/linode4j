package linode4j;

import org.junit.Test;

import uk.co.solong.linode4j.Linode;

import com.fasterxml.jackson.core.JsonProcessingException;

public class LinodeBoot {

    @Test
    public void test() throws JsonProcessingException {
        Linode t = new Linode("YwdWGZaCx202dFQFr0z1H7UFv204MXRtddK9we4udEaZoCAV8HMkg5RsdMUjTIrs");
        String node = t.updateLinode(1).withAlertBwInEnabled(true).go();
        System.out.println(node);
        
    }

}
