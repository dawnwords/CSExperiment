package cn.edu.fudan.se.crowdservice.datagen;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;

/**
 * Created by Dawnwords on 2015/8/24.
 */
public class WorkerGenerator implements DataGenerator<CrowdWorker> {
    @Override
    public CrowdWorker generate(Random random) {
        return new CrowdWorker()
                .cost(Parameter.instance().csAvg() + Parameter.instance().csSd() * Gaussian2Sigma.get(random))
                .reliability(Parameter.instance().rpAvg() + Parameter.instance().rpSd() * Gaussian2Sigma.get(random))
                .responseTime((long) (Parameter.instance().rtLow() + Parameter.instance().rtRange() * random.nextDouble()));
    }
}
