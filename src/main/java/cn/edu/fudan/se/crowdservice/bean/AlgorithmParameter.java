package cn.edu.fudan.se.crowdservice.bean;


import com.microsoft.schemas._2003._10.serialization.arrays.CSResultNumList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSResultNumList.CSResultNum;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList.CSWorker;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class AlgorithmParameter {
    private String compositeServiceXML;
    private TimeCost timeCost;
    private CSWorkerList workers;
    private CSResultNumList resultNums;

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

    public CSWorkerList workers() {
        if (workers == null) {
            throw new NullPointerException("workers is null");
        }
        return workers;
    }

    public AlgorithmParameter workers(CSWorkerList workers) {
        this.workers = workers;
        return this;
    }

    public CSResultNumList resultNums() {
        if (resultNums == null) {
            throw new NullPointerException("resultNums is null");
        }
        return resultNums;
    }

    public AlgorithmParameter resultNums(CSResultNumList resultNums) {
        this.resultNums = resultNums;
        return this;
    }

    @Override
    public String toString() {
        String result = "AlgorithmParameter{" +
                "\n compositeServiceXML:\n" + compositeServiceXML +
                "\n timecost:" + timeCost +
                "\n workers: [\n";
        for (CSWorker worker : workers.getCSWorker()) {
            result += worker.toString() + "\n";
        }
        result += " ]\n resultnums:[\n";
        for (CSResultNum resultNum : resultNums.getCSResultNum()) {
            result += resultNum.toString() + "\n";
        }
        return result + " ]\n}";
    }
}
