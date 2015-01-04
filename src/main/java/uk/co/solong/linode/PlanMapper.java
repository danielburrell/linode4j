package uk.co.solong.linode;

import java.util.List;

/**
 * Created by terabyte on 24/12/2014.
 */
public class PlanMapper {
    private final List<Plan> list;
    public PlanMapper(List<Plan> list) {
        this.list = list;
    }
    public int getPlanIdFromRam(int ram) {
        for (Plan plan:list){
            if (ram == plan.getRam()) {
                return plan.getPlanId();
            }
        }
        return -1;
    }
}
