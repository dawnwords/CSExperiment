package cn.edu.fudan.se.crowdservice.datagen;

import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class WorkerReliabilityGen implements DataGenerator<Double> {
    @Override
    public Double generate(Random random) {
        return 0.7 + 0.15 * Gaussian2Sigma.get(random);
    }
}
