package uk.co.solong.linode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.notedpath.linode.API_ACTION;
import com.notedpath.linode.Linode;
import com.notedpath.linode.LinodeResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by terabyte on 23/12/2014.
 */
public class LinodeTemplate {
    private static final Logger logger = LoggerFactory.getLogger(LinodeTemplate.class);
    private Linode linode;

    public LinodeTemplate(String api) {
        linode = new Linode(api);
    }
/*
    public CreateDiskResponse createDisk(int linodeId) throws IOException {
        LinodeResponse r = linode.execute(API_ACTION.LINODE_DISK_CREATE, "LinodeID", Integer.toString(linodeId));
        return getCreateDiskResponse(r,linodeId);
    }

    public CreateDiskResponse createDisk(int linodeId, int fromDistributionId, String rootPass, String rootSSHKey) throws IOException {
        LinodeResponse r = linode.execute(API_ACTION.LINODE_DISK_CREATE, "LinodeID", Integer.toString(linodeId), "FromDistributionID",Integer.toString(fromDistributionId), "rootPass", rootPass, "rootSSHKey", rootSSHKey);
        return getCreateDiskResponse(r,linodeId);
    }

    public CreateDiskResponse createDiskFromStackScript(int linodeId, int stackScriptId, JsonNode stackScriptUDFResponses, int distributionId, String diskLabel, int diskSize, String rootPass, String rootSSHKey) throws IOException {
        LinodeResponse r = linode.execute(API_ACTION.LINODE_DISK_CREATEFROMDSTACKSCRIPT, "LinodeID", Integer.toString(linodeId), "StackScriptID", Integer.toString(stackScriptId), "StackScriptUDFResponses", stackScriptUDFResponses.asText(), "DistributionID", Integer.toString(distributionId), "Label", diskLabel, "Size", Integer.toString(diskSize), "rootPass", rootPass, "rootSSHKey", rootSSHKey);
        return getCreateDiskResponse(r,linodeId);
    }

    private CreateDiskResponse getCreateDiskResponse(LinodeResponse r, int linodeId) throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        logger.debug("Response: {}",m.writeValueAsString(r));
        JsonNode resultJobId = r.getData().get("JobID");
        JsonNode resultDiskId = r.getData().get("DiskID");
        if (resultJobId.isNull()||resultDiskId.isNull()) {
            throw new RuntimeException("Failed to create Linode");
        } else {
            CreateDiskResponse res = new CreateDiskResponse();
            res.setDiskId(resultDiskId.asLong());
            res.setJobId(resultJobId.asInt());
            res.setLinodeId(linodeId);
            return res;
        }
    }*/

    public int createStackScript(String label, String description, List<Integer> distributionIdList, boolean isPublic, String revisionNote, String script) throws IOException {
        String isPublicNumeric = isPublic ? "1" : "0";
        LinodeResponse r = linode.execute(API_ACTION.STACKSCRIPT_CREATE,"Label",label,"Description",description, "DistributionIDList", StringUtils.join(distributionIdList), "isPublic", isPublicNumeric, "rev_note", revisionNote, "script", script);
        ObjectMapper m = new ObjectMapper();
        logger.debug("Response: {}",m.writeValueAsString(r));
        JsonNode resultLinodeId = r.getData().get("LinodeID");
        if (resultLinodeId.isNull()) {
            throw new RuntimeException("Failed to create Linode");
        } else {
            return resultLinodeId.asInt();
        }
    }
    
    public List<Distribution> getAvailableDistributions() throws IOException {
        LinodeResponse r = linode.availableLinodePlans();
        DataToListMapper<Distribution> dataToListMapper= new DataToListMapper<Distribution>();
        return dataToListMapper.mapDataListToClass(r, Distribution.class);
    }

    public int updateLabel(int linodeId, String label) throws IOException {
        LinodeResponse r = linode.execute(API_ACTION.LINODE_UPDATE,"LinodeID",Integer.toString(linodeId),"Label",label);
        ObjectMapper m = new ObjectMapper();
        logger.debug("Response: {}",m.writeValueAsString(r));
        JsonNode resultLinodeId = r.getData().get("LinodeID");
        if (resultLinodeId.isNull()) {
            throw new RuntimeException("Failed to create Linode");
        } else {
            return resultLinodeId.asInt();
        }
    }
    public int createLinode(int datacenterId, int planId, PaymentTerm paymentTerm) throws IOException {
        LinodeResponse r = linode.execute(API_ACTION.LINODE_CREATE,"DataCenterID",Integer.toString(datacenterId),"PlanID",Integer.toString(planId),"PaymentTerm",Integer.toString(paymentTerm.getPaymentTerm()));
        ObjectMapper m = new ObjectMapper();
        logger.debug("Response: {}",m.writeValueAsString(r));
        JsonNode linodeId = r.getData().get("LinodeID");
        if (linodeId.isNull()) {
            throw new RuntimeException("Failed to create Linode");
        } else {
            return linodeId.asInt();
        }
    }
    public List<Plan> getAvailablePlans() throws IOException {
        LinodeResponse r = linode.availableLinodePlans();
        DataToListMapper<Plan> dataToListMapper= new DataToListMapper<Plan>();
        return dataToListMapper.mapDataListToClass(r, Plan.class);
    }

    public List<DataCenter> getAvailableDataCenters() throws IOException {
        LinodeResponse r = linode.availableDatacenters();
        JsonNode dataArray = r.getData();
        DataToListMapper<DataCenter> dataToListMapper= new DataToListMapper<DataCenter>();
        return dataToListMapper.mapDataListToClass(r, DataCenter.class);
    }


}
