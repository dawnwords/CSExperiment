package cn.edu.fudan.se.crowdservice.datagen;

import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ResultNumGen implements DataGenerator<Integer> {
    @Override
    public Integer generate(Random random) {
        return (int) (5 + 2 * Gaussian2Sigma.get(random));
    }
}
