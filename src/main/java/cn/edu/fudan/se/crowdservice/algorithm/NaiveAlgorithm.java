package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.*;
import cn.edu.fudan.se.crowdservice.util.Logger;

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
        List<ServiceWorkers> serviceWorkers = rank(parameter);
        TimeCost[] timeCosts = new TimeCost[serviceWorkers.size()];

        long timeTotal = 0;
        double costTotal = 0;
        for (int i = 0; i < timeCosts.length; i++) {
            timeCosts[i] = new TimeCost();
            List<CrowdWorker> cw = serviceWorkers.get(i).workers();
            int resultNum = parameter.resultNums().get(i).resultNum();
            for (int j = 0; j < cw.size() && j < resultNum; j++) {
                CrowdWorker crowdWorker = cw.get(j);
                timeCosts[i].aggregate(crowdWorker.responseTime(), crowdWorker.cost());
            }
            Logger.info(parameter.resultNums().get(i).service() + " : " + timeCosts[i]);
            timeTotal += timeCosts[i].time();
            costTotal += timeCosts[i].cost();
        }
        return new TimeCost()
                .time(timeTotal == 0 ? 0 : parameter.deadline() * timeCosts[0].time() / timeTotal)
                .cost(costTotal == 0 ? 0 : parameter.cost() * timeCosts[0].cost() / costTotal);
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        List<CrowdWorker> cw = rank(parameter).get(0).workers();
        double totalCost = parameter.cost();
        ArrayList<CrowdWorker> result = new ArrayList<>();
        for (CrowdWorker worker : cw) {
            if (totalCost > worker.cost()) {
                totalCost -= worker.cost();
                result.add(worker);
            }
        }
        return result;
    }

    public List<ServiceWorkers> rank(AlgorithmParameter parameter) {
        List<ServiceResultNum> serviceResultNums = parameter.resultNums();
        List<ServiceWorkers> csw = parameter.workers();
        List<ServiceWorkers> result = new ArrayList<>();

        for (int i = 0; i < serviceResultNums.size(); i++) {
            ServiceWorkers serviceWorkers = csw.get(i);
            List<CrowdWorker> cw = serviceWorkers.workers();

            List<CrowdWorker> newcw = new ArrayList<>();
            for (CrowdWorker w : cw) {
                if (w.responseTime() < parameter.deadline()) {
                    newcw.add(w);
                }
            }

            Collections.sort(newcw, comparator);
            result.add(new ServiceWorkers().service(serviceWorkers.service()).workers(newcw));
        }
        return result;
    }
}
