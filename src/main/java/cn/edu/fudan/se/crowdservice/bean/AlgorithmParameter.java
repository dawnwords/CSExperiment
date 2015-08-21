package cn.edu.fudan.se.crowdservice.bean;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class AlgorithmParameter {
    private int expId;
    private String currentService;
    private String bpelPath;
    private TimeCost timeCost;
    private Map<String, ServiceSetting> serviceSettings = new HashMap<>();

    public int expId() {
        return expId;
    }

    public AlgorithmParameter expId(int expId) {
        this.expId = expId;
        return this;
    }

    public String currentService() {
        return currentService;
    }

    public AlgorithmParameter currentService(String currentService) {
        this.currentService = currentService;
        return this;
    }

    public String bpelPath() {
        return bpelPath;
    }

    public AlgorithmParameter bpelPath(String compositeServiceXML) {
        this.bpelPath = compositeServiceXML;
        return this;
    }

    public long deadline() {
        return timeCost.time();
    }

    public double cost() {
        return timeCost.cost();
    }

    public AlgorithmParameter timeCost(TimeCost timeCost) {
        this.timeCost = timeCost;
        return this;
    }

    public Map<String, ServiceSetting> serviceSettings() {
        return serviceSettings;
    }

    public AlgorithmParameter addServiceSetting(String service, ServiceSetting serviceSetting) {
        this.serviceSettings.put(service, serviceSetting);
        return this;
    }
}
