package cn.edu.fudan.se.crowdservice.bean;

import java.util.List;

/**
 * Created by Dawnwords on 2015/8/21.
 */
public class ServiceSetting {
    private int resultNum;
    private List<CrowdWorker> workerGroup;

    public int resultNum() {
        return resultNum;
    }

    public ServiceSetting resultNum(int resultNum) {
        this.resultNum = resultNum;
        return this;
    }

    public List<CrowdWorker> workerGroup() {
        return workerGroup;
    }

    public ServiceSetting workerGroup(List<CrowdWorker> workerGroup) {
        this.workerGroup = workerGroup;
        return this;
    }
}
