package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/5.
 */
public class GlobalOptimizeResult {
    private long time;
    private double cost;

    public long time() {
        return time;
    }

    public GlobalOptimizeResult time(long time) {
        this.time = time;
        return this;
    }

    public double cost() {
        return cost;
    }

    public GlobalOptimizeResult cost(double cost) {
        this.cost = cost;
        return this;
    }
}