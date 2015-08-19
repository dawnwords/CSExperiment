package cn.edu.fudan.se.crowdservice.algorithm;


import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;

import java.util.Comparator;

public class OnlyResponseTimeNaiveAlgorithm extends NaiveAlgorithm {

    public OnlyResponseTimeNaiveAlgorithm() {
        super(new Comparator<CrowdWorker>() {
            public int compare(CrowdWorker arg0, CrowdWorker arg1) {
                return new Long(arg0.responseTime()).compareTo(arg1.responseTime());
            }
        });
    }

}
