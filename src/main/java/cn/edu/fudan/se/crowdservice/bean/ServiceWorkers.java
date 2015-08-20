package cn.edu.fudan.se.crowdservice.bean;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("key=");
        sb.append(service);
        sb.append('\n');
        for (CrowdWorker worker : workers) {
            sb.append(worker);
            sb.append('\n');
        }
        return sb.toString();
    }
}
