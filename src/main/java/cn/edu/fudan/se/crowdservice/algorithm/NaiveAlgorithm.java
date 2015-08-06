package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;
import sutd.edu.sg.CrowdWorker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class NaiveAlgorithm implements Algorithm {
    private Comparator<CrowdWorker> comparator;

    protected NaiveAlgorithm(Comparator<CrowdWorker> comparator) {
        this.comparator = comparator;
    }

    @Override
    public TimeCost globalOptimize(AlgorithmParameter parameter) {
        List<CrowdWorker> cwList = rank(parameter);
        long time = cwList.get(0).getResponseTime();
        double cost = 0;
        for (CrowdWorker cw : cwList) {
            if (time < cw.getResponseTime()) time = cw.getResponseTime();
            cost += cw.getCost();
        }
        TimeCost retVal = new TimeCost();
        retVal.cost(cost);
        retVal.time(time);
        return retVal;
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        return rank(parameter);
    }

    public List<CrowdWorker> rank(AlgorithmParameter parameter) {
        List<CSResultNum> retNums = parameter.resultNums();
        List<CSWorker> csw = parameter.workers();
        List<CrowdWorker> retCrowdWorker = new ArrayList<>();

        for (int i = 0; i < retNums.size(); i++) {
            CSWorker csWorker = csw.get(i);
            CrowdWorker[] cw = csWorker.getValue();
            Arrays.sort(cw, comparator);
            retCrowdWorker.addAll(Arrays.asList(cw).subList(0, retNums.get(i).getValue()));
        }
        return retCrowdWorker;
    }
}
