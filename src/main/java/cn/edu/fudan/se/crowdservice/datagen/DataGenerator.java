package cn.edu.fudan.se.crowdservice.datagen;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public interface DataGenerator<T> {
    T generate(Random random);

    class Gaussian2Sigma {
        static double get(Random random) {
            return Math.min(Math.max(random.nextGaussian(), -2), 2);
        }
    }
}
