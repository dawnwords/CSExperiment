package cn.edu.fudan.se.crowdservice.datagen;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class WorkerResponseTimeGen implements DataGenerator<Long> {
    @Override
    public Long generate(Random random) {
        return (long) (100 + 500 * random.nextDouble());
    }
}
