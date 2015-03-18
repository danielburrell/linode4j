package uk.co.solong.linode4j;

import org.junit.Test;

import uk.co.solong.linode4j.Linode;

import com.fasterxml.jackson.core.JsonProcessingException;

public class VerifyLinodeAPI {

    @Test
    public void test() throws JsonProcessingException {
        Linode t = new Linode("notrequired");
        String apiSpec = t.apiSpec().go();
        System.out.println(apiSpec);
        //verify the following
        //every method in the api exists
        //for every method, every optional parameter exists
        //for every method, every mandatory parameter exists
        //for every method, every parameter type is correct
        //for every method, every parameter json value is correct
    }

}