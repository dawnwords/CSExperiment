package cn.edu.fudan.se.crowdservice.algorithm;

import sutd.edu.sg.CrowdWorker;

import java.util.Comparator;

public class OnlyCostNaiveAlgorithm extends NaiveAlgorithm {

    public OnlyCostNaiveAlgorithm() {
        super(new Comparator<CrowdWorker>() {
            public int compare(CrowdWorker arg0, CrowdWorker arg1) {
                return arg0.getCost().compareTo(arg1.getCost());
            }
        });
    }
}
