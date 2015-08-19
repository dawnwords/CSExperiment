package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/19.
 */
public class CrowdWorker {
    private boolean selected;
    private long responseTime;
    private double reliability;
    private int index;
    private double cost;

    public boolean selected() {
        return selected;
    }

    public CrowdWorker selected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public long responseTime() {
        return responseTime;
    }

    public CrowdWorker responseTime(long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public double reliability() {
        return reliability;
    }

    public CrowdWorker reliability(double reliability) {
        this.reliability = reliability;
        return this;
    }

    public int index() {
        return index;
    }

    public CrowdWorker index(int index) {
        this.index = index;
        return this;
    }

    public double cost() {
        return cost;
    }

    public CrowdWorker cost(double cost) {
        this.cost = cost;
        return this;
    }
}
