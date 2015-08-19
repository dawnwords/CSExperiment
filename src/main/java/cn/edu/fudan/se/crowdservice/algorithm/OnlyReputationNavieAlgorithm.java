package cn.edu.fudan.se.crowdservice.algorithm;


import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;

import java.util.Comparator;

public class OnlyReputationNavieAlgorithm extends NaiveAlgorithm {

    public OnlyReputationNavieAlgorithm() {
        super(new Comparator<CrowdWorker>() {
            public int compare(CrowdWorker arg0, CrowdWorker arg1) {
                return -new Double(arg0.reliability()).compareTo(arg1.reliability());
            }
        });
    }
}
