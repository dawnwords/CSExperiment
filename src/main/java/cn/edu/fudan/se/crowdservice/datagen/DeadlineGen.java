package cn.edu.fudan.se.crowdservice.datagen;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class DeadlineGen implements DataGenerator<Long> {
    @Override
    public Long generate(Random random) {
        return (long) (500 + 200 * Gaussian2Sigma.get(random));
    }
}
