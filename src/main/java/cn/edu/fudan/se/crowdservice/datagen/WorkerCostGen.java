package cn.edu.fudan.se.crowdservice.datagen;

import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class WorkerCostGen implements DataGenerator<Double> {
    @Override
    public Double generate(Random random) {
        return 12 + 3 * Gaussian2Sigma.get(random);
    }
}
