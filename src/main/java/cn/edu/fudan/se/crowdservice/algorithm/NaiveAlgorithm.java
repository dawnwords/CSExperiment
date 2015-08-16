package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.util.Logger;
import com.microsoft.schemas._2003._10.serialization.arrays.CSResultNumList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList.CSWorker;
import sutd.edu.sg.ArrayOfCrowdWorker;
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
        CSWorkerList csWorkers = rank(parameter);
        TimeCost[] timeCosts = new TimeCost[csWorkers.getCSWorker().size()];

        long timeTotal = 0;
        double costTotal = 0;
        for (int i = 0; i < timeCosts.length; i++) {
            timeCosts[i] = new TimeCost();
            ArrayOfCrowdWorker cw = csWorkers.getCSWorker().get(i).getValue();
            int resultNum = parameter.resultNums().getCSResultNum().get(i).getValue();
            for (int j = 0; j < cw.getCrowdWorker().size() && j < resultNum; j++) {
                CrowdWorker worker = cw.getCrowdWorker().get(j);
                timeCosts[i].aggregate(worker.getResponseTime(), worker.getCost());
            }
            Logger.info(parameter.resultNums().getCSResultNum().get(i).getKey() + " : " + timeCosts[i]);
            timeTotal += timeCosts[i].time();
            costTotal += timeCosts[i].cost();
        }
        return new TimeCost()
                .time(timeTotal == 0 ? 0 : parameter.deadline() * timeCosts[0].time() / timeTotal)
                .cost(costTotal == 0 ? 0 : parameter.cost() * timeCosts[0].cost() / costTotal);
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        ArrayOfCrowdWorker cw = rank(parameter).getCSWorker().get(0).getValue();
        double totalCost = parameter.cost();
        ArrayList<CrowdWorker> result = new ArrayList<>();
        for (CrowdWorker worker : cw.getCrowdWorker()) {
            if (totalCost > worker.getCost()) {
                totalCost -= worker.getCost();
                result.add(worker);
            }
        }
        return result;
    }

    public CSWorkerList rank(AlgorithmParameter parameter) {
        CSResultNumList csResultNums = parameter.resultNums();
        CSWorkerList csw = parameter.workers();
        CSWorkerList result = new CSWorkerList();

        for (int i = 0; i < csResultNums.getCSResultNum().size(); i++) {
            CSWorker csWorker = csw.getCSWorker().get(i);
            ArrayOfCrowdWorker cw = csWorker.getValue();

            List<CrowdWorker> newcw = new ArrayList<>();
            for (CrowdWorker w : cw.getCrowdWorker()) {
                if (w.getResponseTime() < parameter.deadline()) {
                    newcw.add(w);
                }
            }

            Collections.sort(newcw, comparator);
            CSWorker worker = new CSWorker();
            worker.setKey(csWorker.getKey());

            ArrayOfCrowdWorker value = new ArrayOfCrowdWorker();
            value.setCrowdWorker(newcw);
            worker.setValue(value);
            result.add(worker);
        }
        return result;
    }
}
