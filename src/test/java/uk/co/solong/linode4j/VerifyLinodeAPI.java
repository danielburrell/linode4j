package uk.co.solong.linode4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import uk.co.solong.linode4j.Linode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VerifyLinodeAPI {

    //TODO: refactor this method at some point into multiple tests.
    @Test
    public void apiLint() throws IOException {
        ObjectMapper m = new ObjectMapper();
        JsonNode manualApiSpec = m.readTree(new File("D:\\workspace\\linode4j\\src\\main\\resources\\schema.json"));
        Linode t = new Linode("notrequired");
        JsonNode officialApiSpec = t.apiSpec().asJson();
        Iterator<Entry<String, JsonNode>> methods = officialApiSpec.get("DATA").get("METHODS").fields();
        
        //for each method
        while (methods.hasNext()){
            Entry<String, JsonNode> officialMethod = methods.next();
            //check the actions match
            String officialAction = officialMethod.getKey();
            //find the entry in the manualApiSpec
            boolean methodFound = false;
            for (JsonNode manualMethod : manualApiSpec.get("methods")) {
                for (JsonNode fixedParameter : manualMethod.get("fixedParameters")){
                    if (fixedParameter.get("jsonName").asText().equals("api_action")){
                        String candidate = fixedParameter.get("jsonValue").asText();
                        if (candidate.equals(officialAction)){
                            //check the description matches
                            System.out.println("Found "+officialAction);
                            methodFound = true;
                            String officialDescription = officialMethod.getValue().get("DESCRIPTION").asText();
                            String manualDescription = manualMethod.get("description").asText("");
                            if (!officialDescription.equals("")){
                                Assert.assertEquals("Official Description doesn't match for method "+officialAction+", Official:["+officialDescription+"],["+manualDescription+"]",officialDescription,manualDescription);
                            }
                            //for every method, every optional parameter exists
                            JsonNode officialParametersNode = officialMethod.getValue().get("PARAMETERS");
                            Iterator<Entry<String, JsonNode>> officialParameters = officialParametersNode.fields();
                            while (officialParameters.hasNext()){
                                Entry<String, JsonNode> p = officialParameters.next();
                                JsonNode manualParameters = null;
                                boolean isMandatory = p.getValue().get("REQUIRED").asBoolean();
                                String isMandatoryText = "optional";
                                if (isMandatory) {
                                    isMandatoryText = "mandatory";
                                } 
                                if (isMandatory){
                                    manualParameters = manualMethod.get("mandatoryParameters");
                                } else {
                                    manualParameters = manualMethod.get("optionalParameters");
                                }
                                boolean foundParameter = false;
                                for (JsonNode manualParameter : manualParameters) {
                                    if (p.getValue().get("NAME").asText().equals(manualParameter.get("jsonName").asText())){
                                        foundParameter = true;
                                        if ("numeric".equals(p.getValue().get("TYPE").asText())){
                                            Assert.assertEquals("java.lang.Integer",manualParameter.get("type").asText());
                                        }
                                        if ("string".equals(p.getValue().get("TYPE").asText())){
                                            Assert.assertEquals("Parameter "+p.getKey()+" should be a string in "+officialMethod.getKey(),"java.lang.String",manualParameter.get("type").asText());
                                        }
                                    }
                                }
                                Assert.assertTrue(isMandatoryText+" parameter "+p.getValue().get("NAME")+" not found. in :"+officialMethod.getKey(),foundParameter);
                            }
                        }
                    }
                }
            }
            Assert.assertTrue("method "+officialAction+" not found. Details:"+officialMethod.getValue().toString(),methodFound);
        }
        
        
        System.out.println(officialApiSpec);
        //verify the following
    }

}
