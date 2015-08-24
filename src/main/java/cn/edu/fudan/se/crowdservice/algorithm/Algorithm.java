package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.bean.WorkerSelectionResult;


/**
 * Created by Dawnwords on 2015/8/6.
 */
public interface Algorithm {
    TimeCost globalOptimize(AlgorithmParameter parameter);

    WorkerSelectionResult workerSelection(AlgorithmParameter parameter);

    enum AlgorithmFactory {
        th(new TianHuatAlgorithm()),
        cs(new OnlyCostNaiveAlgorithm()),
        rp(new OnlyReputationNavieAlgorithm()),
        rt(new OnlyResponseTimeNaiveAlgorithm()),
        mx(new MixedNaiveAlgorithm());

        private Algorithm instance;
        private int times;

        AlgorithmFactory(Algorithm instance) {
            this.instance = instance;
            this.times = Parameter.instance().times(this);
        }

        public Algorithm instance() {
            return instance;
        }

        public int times() {
            return times;
        }
    }
}
