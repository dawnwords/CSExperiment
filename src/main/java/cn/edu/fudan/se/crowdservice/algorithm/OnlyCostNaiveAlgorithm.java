package cn.edu.fudan.se.crowdservice.algorithm;


import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;

import java.util.Comparator;

public class OnlyCostNaiveAlgorithm extends NaiveAlgorithm {

    public OnlyCostNaiveAlgorithm() {
        super(new Comparator<CrowdWorker>() {
            public int compare(CrowdWorker arg0, CrowdWorker arg1) {
                return new Double(arg0.cost()).compareTo(arg1.cost());
            }
        });
    }
}
