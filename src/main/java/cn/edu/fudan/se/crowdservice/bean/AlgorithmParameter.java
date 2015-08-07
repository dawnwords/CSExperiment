package cn.edu.fudan.se.crowdservice.bean;

import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;

import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class AlgorithmParameter {
    private String compositeServiceXML;
    private long deadline;
    private double cost;
    private List<CSWorker> workers;
    private List<CSResultNum> resultNums;

    public String compositeServiceXML() {
        return compositeServiceXML;
    }

    public AlgorithmParameter compositeServiceXML(String compositeServiceXML) {
        this.compositeServiceXML = compositeServiceXML;
        return this;
    }

    public long deadline() {
        return deadline;
    }

    public AlgorithmParameter deadline(long deadline) {
        this.deadline = deadline;
        return this;
    }

    public double cost() {
        return cost;
    }

    public AlgorithmParameter cost(double cost) {
        this.cost = cost;
        return this;
    }

    public List<CSWorker> workers() {
        if (workers == null) {
            throw new NullPointerException("workers is null");
        }
        return workers;
    }

    public CSWorker[] workerArray() {
        return workers().toArray(new CSWorker[workers().size()]);
    }

    public AlgorithmParameter workers(List<CSWorker> workers) {
        this.workers = workers;
        return this;
    }

    public CSResultNum[] resultNumArray() {
        return resultNums().toArray(new CSResultNum[resultNums().size()]);
    }

    public List<CSResultNum> resultNums() {
        if (resultNums == null) {
            throw new NullPointerException("resultNums is null");
        }
        return resultNums;
    }

    public AlgorithmParameter resultNums(List<CSResultNum> resultNums) {
        this.resultNums = resultNums;
        return this;
    }

    @Override
    public String toString() {
        String result = "AlgorithmParameter{" +
                "\n compositeServiceXML:\n" + compositeServiceXML +
                "\n deadline:" + deadline +
                "\n cost:" + cost +
                "\n workers: [\n";
        for (CSWorker worker : workers) {
            result += worker.toString() + "\n";
        }
        result += " ]\n resultnums:[\n";
        for (CSResultNum resultNum : resultNums) {
            result += resultNum.toString() + "\n";
        }
        return result + " ]\n}";
    }
}
