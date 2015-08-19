package cn.edu.fudan.se.crowdservice.bean;


import cn.edu.fudan.se.crowdservice.algorithm.ServiceWorkers;

import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class AlgorithmParameter {
    private String compositeServiceXML;
    private TimeCost timeCost;
    private List<ServiceWorkers> workers;
    private List<ServiceResultNum> resultNums;

    public String compositeServiceXML() {
        return compositeServiceXML;
    }

    public AlgorithmParameter compositeServiceXML(String compositeServiceXML) {
        this.compositeServiceXML = compositeServiceXML;
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

    public List<ServiceWorkers> workers() {
        if (workers == null) {
            throw new NullPointerException("workers is null");
        }
        return workers;
    }


    public AlgorithmParameter workers(List<ServiceWorkers> workers) {
        this.workers = workers;
        return this;
    }

    public List<ServiceResultNum> resultNums() {
        if (resultNums == null) {
            throw new NullPointerException("resultNums is null");
        }
        return resultNums;
    }

    public AlgorithmParameter resultNums(List<ServiceResultNum> resultNums) {
        this.resultNums = resultNums;
        return this;
    }

    @Override
    public String toString() {
        String result = "AlgorithmParameter{" +
                "\n compositeServiceXML:\n" + compositeServiceXML +
                "\n timecost:" + timeCost +
                "\n workers: [\n";
        for (ServiceWorkers worker : workers) {
            result += worker.toString() + "\n";
        }
        result += " ]\n resultnums:[\n";
        for (ServiceResultNum resultNum : resultNums) {
            result += resultNum.toString() + "\n";
        }
        return result + " ]\n}";
    }
}
