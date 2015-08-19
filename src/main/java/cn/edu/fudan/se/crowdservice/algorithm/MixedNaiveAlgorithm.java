package cn.edu.fudan.se.crowdservice.algorithm;


import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;

import java.util.Comparator;

public class MixedNaiveAlgorithm extends NaiveAlgorithm {

    private static final double coe_rep = 1.0; //co-efficiency of reputation
    private static final double coe_res = 1.0; //co-efficiency of response time
    private static final double coe_cost = 1.0;//co-efficiency of cost

    public MixedNaiveAlgorithm() {
        super(new Comparator<CrowdWorker>() {
            public int compare(CrowdWorker arg0, CrowdWorker arg1) {
                Double d1 = arg0.reliability() * coe_rep - arg0.responseTime() * coe_res - arg0.cost() * coe_cost;
                Double d2 = arg1.reliability() * coe_rep - arg1.responseTime() * coe_res - arg1.cost() * coe_cost;
                return -d1.compareTo(d2);
            }
        });
    }
}
