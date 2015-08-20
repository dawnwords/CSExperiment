package cn.edu.fudan.se.crowdservice.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/19.
 */
public class WorkerSelectionResult {
    List<CrowdWorker> workers = new ArrayList<>();
    private String service;
    private double totalCost;
    private long maxResponseTime;

    public List<CrowdWorker> workers() {
        return workers;
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

    public double totalCost() {
        return totalCost;
    }

    public WorkerSelectionResult totalCost(double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public long maxResponseTime() {
        return maxResponseTime;
    }

    public WorkerSelectionResult maxResponseTime(long maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
        return this;
    }
}
