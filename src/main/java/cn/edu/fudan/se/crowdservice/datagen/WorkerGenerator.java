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
    private int executeTime;

    public WorkerGenerator() {
        this.csAvg = Parameter.instance().csAvg();
        this.csSd = Parameter.instance().csSd();
        this.rpAvg = Parameter.instance().rpAvg();
        this.rpSd = Parameter.instance().rpSd();
        this.rtLow = Parameter.instance().rtLow();
        this.rtRange = Parameter.instance().rtRange();
        this.executeTime = Parameter.instance().executeTimes();
    }

    @Override
    public CrowdWorker generate(Random random) {
        double cost = csAvg + csSd * Gaussian2Sigma.get(random);
        double reliability = rpAvg + rpSd * Gaussian2Sigma.get(random);
        long responseTime = (long) (rtLow + rtRange * random.nextDouble());
        StringBuilder success = new StringBuilder();
        for (int i = 0; i < executeTime; i++) {
            success.append(random.nextDouble() < reliability ? '1' : '0');
        }
        return new CrowdWorker()
                .cost(cost)
                .reliability(reliability)
                .responseTime(responseTime)
                .success(success.toString());
    }
}
