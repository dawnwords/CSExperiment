package cn.edu.fudan.se.crowdservice.datagen;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ResultNumGen implements DataGenerator<Integer> {
    @Override
    public Integer generate(Random random) {
        return (int) (3 + 1 * Gaussian2Sigma.get(random));
    }
}
