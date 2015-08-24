package cn.edu.fudan.se.crowdservice.datagen;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;

/**
 * Created by Dawnwords on 2015/8/24.
 */
public class WorkerGenerator implements DataGenerator<CrowdWorker> {
    private double csAvg, csSd;
    private double rpAvg, rpSd;
    private long rtLow, rtRange;

    public WorkerGenerator() {
        this.csAvg = Parameter.instance().csAvg();
        this.csSd = Parameter.instance().csSd();
        this.rpAvg = Parameter.instance().rpAvg();
        this.rpSd = Parameter.instance().rpSd();
        this.rtLow = Parameter.instance().rtLow();
        this.rtRange = Parameter.instance().rtRange();
    }

    @Override
    public CrowdWorker generate(Random random) {
        return new CrowdWorker()
                .cost(csAvg + csSd * Gaussian2Sigma.get(random))
                .reliability(rpAvg + rpSd * Gaussian2Sigma.get(random))
                .responseTime((long) (rtLow + rtRange * random.nextDouble()));
    }
}
