package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.util.Logger;
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
        List<CSWorker> csWorkers = serviceTopK(parameter);
        TimeCost[] timeCosts = new TimeCost[csWorkers.size()];

        long timeTotal = 0;
        double costTotal = 0;
        for (int i = 0; i < timeCosts.length; i++) {
            timeCosts[i] = new TimeCost();
            for (CrowdWorker cw : csWorkers.get(i).getValue()) {
                timeCosts[i].aggregate(cw.getResponseTime(), cw.getCost());
            }
            Logger.info(parameter.resultNumArray()[i].getKey() + " : " + timeCosts[i]);
            timeTotal += timeCosts[i].time();
            costTotal += timeCosts[i].cost();
        }
        return new TimeCost()
                .time(parameter.deadline() * timeCosts[0].time() / timeTotal)
                .cost(parameter.cost() * timeCosts[0].cost() / costTotal);
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        return Arrays.asList(serviceTopK(parameter).get(0).getValue());
    }

    public List<CSWorker> serviceTopK(AlgorithmParameter parameter) {
        List<CSResultNum> csResultNums = parameter.resultNums();
        List<CSWorker> csw = parameter.workers();
        List<CSWorker> result = new ArrayList<>();

        for (int i = 0; i < csResultNums.size(); i++) {
            CSWorker csWorker = csw.get(i);
            CrowdWorker[] cw = csWorker.getValue();
            CrowdWorker[] newcs = Arrays.copyOf(cw, cw.length);
            Arrays.sort(newcs, comparator);

            result.add(new CSWorker(csWorker.getKey(), Arrays.copyOf(newcs, csResultNums.get(i).getValue())));
        }
        return result;
    }
}
