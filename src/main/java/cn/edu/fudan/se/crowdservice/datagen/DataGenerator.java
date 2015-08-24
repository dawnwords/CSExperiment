package cn.edu.fudan.se.crowdservice.datagen;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public interface DataGenerator<T> {
    T generate(Random random);

    class Gaussian2Sigma {
        static double get(Random random) {
            double result;
            do {
                result = random.nextGaussian();
            } while (Math.abs(result) > 2);
            return result;
        }
    }
}
