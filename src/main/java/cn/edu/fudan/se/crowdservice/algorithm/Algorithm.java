package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;

import java.util.List;


/**
 * Created by Dawnwords on 2015/8/6.
 */
public interface Algorithm {
    TimeCost globalOptimize(AlgorithmParameter parameter);

    List<CrowdWorker> workerSelection(AlgorithmParameter parameter);

    enum AlgorithmClass {
        th(TianHuatAlgorithm.class),
        cs(OnlyCostNaiveAlgorithm.class),
        rp(OnlyReputationNavieAlgorithm.class),
        rt(OnlyResponseTimeNaiveAlgorithm.class),
        mx(MixedNaiveAlgorithm.class);

        private Class<? extends Algorithm> cls;

        AlgorithmClass(Class<? extends Algorithm> cls) {
            this.cls = cls;
        }

        public Class cls() {
            return cls;
        }
    }
}
