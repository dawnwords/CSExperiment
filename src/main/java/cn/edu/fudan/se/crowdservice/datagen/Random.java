package cn.edu.fudan.se.crowdservice.datagen;

import cn.edu.fudan.se.crowdservice.util.Logger;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dawnwords on 2015/8/14.
 */
public class Random {

    public final AtomicLong count = new AtomicLong();

    private java.util.Random RANDOM;

    public Random(long seed) {
        this.RANDOM = new java.util.Random(seed);
    }

    public double nextGaussian() {
        count.incrementAndGet();
        return RANDOM.nextGaussian();
    }

    public double nextDouble() {
        count.incrementAndGet();
        return RANDOM.nextDouble();
    }

    public void logCount() {
        Logger.info("[Random count] %d", count.get());
    }
}
