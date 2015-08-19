package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.*;
import cn.edu.fudan.se.crowdservice.thstub.THServiceStub;

import java.util.*;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class TianHuatAlgorithm implements Algorithm {
    private static final int ITERATION_NUM = 1000;

    @Override
    public TimeCost globalOptimize(AlgorithmParameter parameter) {
        try {
            List<CrowdWorker> crowdWorkers = workerSelection(parameter);
            double cost = 0;
            long time = 0;
            for (CrowdWorker worker : crowdWorkers) {
                cost += worker.cost();
                time = Math.max(time, worker.responseTime());
            }
            return new TimeCost().cost(cost).time(time);
        } catch (Exception e) {
            throw new RuntimeException("Global Optimization Fails");
        }
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        List<CrowdWorker> crowdWorkers = new LinkedList<>();
        Map<String, List<Integer>> indexMap = saveIndex(parameter.workers());
        OptimizationResult result = new THServiceStub().globalOptimize(
                parameter.compositeServiceXML(),
                parameter.deadline(),
                parameter.cost(),
                parameter.workers(),
                parameter.resultNums(),
                ITERATION_NUM);
        restoreIndex(parameter.workers(), indexMap);
        List<WorkerSelectionResult> crowdServiceSelection = result.selectionResult();
        if (crowdServiceSelection.size() == 0) {
            throw new RuntimeException("Worker Selection: Fails");
        }

        WorkerSelectionResult cw = crowdServiceSelection.get(0);
        List<Integer> index = indexMap.get(cw.service());
        for (CrowdWorker worker : cw.workers()) {
            worker.index(index.get(worker.index()));
            if (worker.selected()) {
                crowdWorkers.add(worker);
            }
        }
        return crowdWorkers;
    }

    private Map<String, List<Integer>> saveIndex(List<ServiceWorkers> workers) {
        Map<String, List<Integer>> result = new HashMap<>();
        for (ServiceWorkers worker : workers) {
            ArrayList<Integer> value = new ArrayList<>();
            int i = 0;
            for (CrowdWorker w : worker.workers()) {
                value.add(w.index());
                w.index(i++);
            }
            result.put(worker.service(), value);
        }
        return result;
    }

    private void restoreIndex(List<ServiceWorkers> workers, Map<String, List<Integer>> indexMap) {
        for (ServiceWorkers worker : workers) {
            List<Integer> index = indexMap.get(worker.service());
            for (CrowdWorker w : worker.workers()) {
                w.index(index.get(w.index()));
            }
        }
    }
}
