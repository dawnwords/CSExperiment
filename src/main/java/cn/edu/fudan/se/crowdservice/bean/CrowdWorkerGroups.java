package cn.edu.fudan.se.crowdservice.bean;

import sutd.edu.sg.CrowdWorker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class CrowdWorkerGroups {
    List<CrowdWorker> cs1Group;
    List<CrowdWorker> cs2Group;

    public CrowdWorkerGroups() {
        this.cs1Group = new ArrayList<>();
        this.cs2Group = new ArrayList<>();
    }

    public CrowdWorkerGroups addCS1Worker(CrowdWorker worker) {
        cs1Group.add(worker);
        return this;
    }

    public CrowdWorkerGroups addCS2Worker(CrowdWorker worker) {
        cs2Group.add(worker);
        return this;
    }

    public List<CrowdWorker> cs1Group() {
        return cs1Group;
    }

    public CrowdWorker[] cs1GroupArray() {
        return cs1Group.toArray(new CrowdWorker[cs1Group.size()]);
    }

    public List<CrowdWorker> cs2Group() {
        return cs2Group;
    }

    public CrowdWorker[] cs2GroupArray() {
        return cs2Group.toArray(new CrowdWorker[cs2Group.size()]);
    }
}
