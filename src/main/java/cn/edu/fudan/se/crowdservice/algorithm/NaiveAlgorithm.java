package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.util.Logger;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;
import sutd.edu.sg.CrowdWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class NaiveAlgorithm implements Algorithm {
    private Comparator<CrowdWorker> comparator;

    protected NaiveAlgorithm(Comparator<CrowdWorker> comparator) {
        this.comparator = comparator;
    }

    @Override
    public TimeCost globalOptimize(AlgorithmParameter parameter) {
        List<CSWorker> csWorkers = rank(parameter);
        TimeCost[] timeCosts = new TimeCost[csWorkers.size()];

        long timeTotal = 0;
        double costTotal = 0;
        for (int i = 0; i < timeCosts.length; i++) {
            timeCosts[i] = new TimeCost();
            CrowdWorker[] cw = csWorkers.get(i).getValue();
            int resultNum = parameter.resultNumArray()[i].getValue();
            for (int j = 0; j < cw.length && j < resultNum; j++) {
                timeCosts[i].aggregate(cw[j].getResponseTime(), cw[j].getCost());
            }
            Logger.info(parameter.resultNumArray()[i].getKey() + " : " + timeCosts[i]);
            timeTotal += timeCosts[i].time();
            costTotal += timeCosts[i].cost();
        }
        return new TimeCost()
                .time(timeTotal == 0 ? 0 : parameter.deadline() * timeCosts[0].time() / timeTotal)
                .cost(costTotal == 0 ? 0 : parameter.cost() * timeCosts[0].cost() / costTotal);
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        CrowdWorker[] cw = rank(parameter).get(0).getValue();
        double totalCost = parameter.cost();
        ArrayList<CrowdWorker> result = new ArrayList<>();
        for (CrowdWorker worker : cw) {
            if (totalCost > worker.getCost()) {
                totalCost -= worker.getCost();
                result.add(worker);
            }
        }
        return result;
    }

    public List<CSWorker> rank(AlgorithmParameter parameter) {
        List<CSResultNum> csResultNums = parameter.resultNums();
        List<CSWorker> csw = parameter.workers();
        List<CSWorker> result = new ArrayList<>();

        for (int i = 0; i < csResultNums.size(); i++) {
            CSWorker csWorker = csw.get(i);
            CrowdWorker[] cw = csWorker.getValue();

            List<CrowdWorker> newcw = new ArrayList<>();
            for (CrowdWorker w : cw) {
                if (w.getResponseTime() < parameter.deadline()) {
                    newcw.add(w);
                }
            }

            Collections.sort(newcw, comparator);
            result.add(new CSWorker(csWorker.getKey(), newcw.toArray(new CrowdWorker[newcw.size()])));
        }
        return result;
    }
}
