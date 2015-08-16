package cn.edu.fudan.se.crowdservice.algorithm;

import sutd.edu.sg.CrowdWorker;

import java.util.Comparator;

public class OnlyResponseTimeNaiveAlgorithm extends NaiveAlgorithm {

    public OnlyResponseTimeNaiveAlgorithm() {
        super(new Comparator<CrowdWorker>() {
            public int compare(CrowdWorker arg0, CrowdWorker arg1) {
                return arg0.getResponseTime().compareTo(arg1.getResponseTime());
            }
        });
    }

}
