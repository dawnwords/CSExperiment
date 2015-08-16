package cn.edu.fudan.se.crowdservice.algorithm;

import sutd.edu.sg.CrowdWorker;

import java.util.Comparator;

public class OnlyReputationNavieAlgorithm extends NaiveAlgorithm {

    public OnlyReputationNavieAlgorithm() {
        super(new Comparator<CrowdWorker>() {
            public int compare(CrowdWorker arg0, CrowdWorker arg1) {
                return -arg0.getReliability().compareTo(arg1.getReliability());
            }
        });
    }
}
