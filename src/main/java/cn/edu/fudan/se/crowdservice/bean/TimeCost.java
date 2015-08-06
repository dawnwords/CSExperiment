package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/5.
 */
public class TimeCost {
    private long time;
    private double cost;

    public long time() {
        return time;
    }

    public TimeCost time(long time) {
        this.time = time;
        return this;
    }

    public double cost() {
        return cost;
    }

    public TimeCost cost(double cost) {
        this.cost = cost;
        return this;
    }

    public void minus(TimeCost timeCost) {
        this.time -= timeCost.time;
        this.cost -= timeCost.cost;
    }


    public void aggregate(long time, double cost) {
        this.time = Math.max(this.time, time);
        this.cost += cost;
    }
}