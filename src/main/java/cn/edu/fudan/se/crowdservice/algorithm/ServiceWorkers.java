package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;

import java.util.List;

/**
 * Created by Dawnwords on 2015/8/19.
 */
public class ServiceWorkers {
    String service;
    List<CrowdWorker> workers;

    public String service() {
        return service;
    }

    public ServiceWorkers service(String service) {
        this.service = service;
        return this;
    }

    public List<CrowdWorker> workers() {
        return workers;
    }

    public ServiceWorkers workers(List<CrowdWorker> workers) {
        this.workers = workers;
        return this;
    }
}
