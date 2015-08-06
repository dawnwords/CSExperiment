package cn.edu.fudan.se.crowdservice.algorithm;

import sutd.edu.sg.CrowdWorker;

import java.util.Comparator;

public class MixedNaiveAlgorithm extends NaiveAlgorithm {

    private static final double coe_rep = 1.0; //co-efficiency of reputation
    private static final double coe_res = 1.0; //co-efficiency of response time
    private static final double coe_cost = 1.0;//co-efficiency of cost

    public MixedNaiveAlgorithm() {
        super(new Comparator<CrowdWorker>() {
            public int compare(CrowdWorker arg0, CrowdWorker arg1) {
                Double d1 = arg0.getReliability() * coe_rep - arg0.getResponseTime() * coe_res - arg0.getCost() * coe_cost;
                Double d2 = arg1.getReliability() * coe_rep - arg1.getResponseTime() * coe_res - arg1.getCost() * coe_cost;
                return -d1.compareTo(d2);
            }
        });
    }
}
