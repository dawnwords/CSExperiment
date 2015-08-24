package cn.edu.fudan.se.crowdservice.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/19.
 */
public class WorkerSelectionResult {
    List<CrowdWorker> workers = new ArrayList<>();
    private String service;
    private TimeCost executeTimeCost = new TimeCost();
    private TimeCost planTimeCost = new TimeCost();

    public List<CrowdWorker> workers() {
        return workers;
    }

    public WorkerSelectionResult workers(List<CrowdWorker> workers) {
        this.workers = workers;
        return this;
    }

    public WorkerSelectionResult addWorker(CrowdWorker worker) {
        this.workers.add(worker);
        return this;
    }

    public String service() {
        return service;
    }

    public WorkerSelectionResult service(String service) {
        this.service = service;
        return this;
    }

    public TimeCost executeTimeCost() {
        return executeTimeCost;
    }

    public WorkerSelectionResult executeTimeCost(TimeCost executeTimeCost) {
        this.executeTimeCost = executeTimeCost;
        return this;
    }

    public TimeCost planTimeCost() {
        return planTimeCost;
    }

    public WorkerSelectionResult executeTime(long time) {
        this.executeTimeCost.time(time);
        return this;
    }

    public WorkerSelectionResult executeCost(double cost) {
        this.executeTimeCost.cost(cost);
        return this;
    }

    public WorkerSelectionResult planTime(long time) {
        this.planTimeCost.time(time);
        return this;
    }

    public WorkerSelectionResult planCost(double cost) {
        this.planTimeCost.cost(cost);
        return this;
    }
}
