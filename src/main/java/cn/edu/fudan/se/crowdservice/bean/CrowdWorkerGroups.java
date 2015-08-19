package cn.edu.fudan.se.crowdservice.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class CrowdWorkerGroups {
    List<CrowdWorker> cs1Group;
    List<CrowdWorker> cs2Group;
    List<CrowdWorker> cs3Group;

    public CrowdWorkerGroups() {
        this.cs1Group = new ArrayList<>();
        this.cs2Group = new ArrayList<>();
        this.cs3Group = new ArrayList<>();
    }

    public CrowdWorkerGroups addCS1Worker(CrowdWorker worker) {
        cs1Group.add(worker);
        return this;
    }

    public CrowdWorkerGroups addCS2Worker(CrowdWorker worker) {
        cs2Group.add(worker);
        return this;
    }

    public CrowdWorkerGroups addCS3Worker(CrowdWorker worker) {
        cs3Group.add(worker);
        return this;
    }

    public List<CrowdWorker> cs1Group() {
        return cs1Group;
    }

    public List<CrowdWorker> cs2Group() {
        return cs2Group;
    }

    public List<CrowdWorker> cs3Group() {
        return cs3Group;
    }
}
