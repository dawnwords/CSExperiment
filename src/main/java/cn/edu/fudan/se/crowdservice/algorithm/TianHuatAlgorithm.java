package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;
import sutd.edu.sg.CrowdOptimizationResult;
import sutd.edu.sg.CrowdServiceProxy;
import sutd.edu.sg.CrowdWorker;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class TianHuatAlgorithm implements Algorithm {
    private static final int ITERATION_NUM = 100;

    @Override
    public TimeCost globalOptimize(AlgorithmParameter parameter) {
        List<CrowdWorker> crowdWorkers = workerSelection(parameter);
        double cost = 0;
        long time = 0;
        for (CrowdWorker worker : crowdWorkers) {
            cost += worker.getCost();
            time = Math.max(time, worker.getResponseTime());
        }
        return new TimeCost().cost(cost).time(time);
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        List<CrowdWorker> crowdWorkers = new LinkedList<>();
        Map<String, List<Integer>> indexMap = saveIndex(parameter.workers());
        try {
            CrowdOptimizationResult result = new CrowdServiceProxy().globalOptimize(
                    parameter.compositeServiceXML(),
                    parameter.deadline(),
                    parameter.cost(),
                    parameter.workerArray(),
                    parameter.resultNumArray(),
                    ITERATION_NUM);
            CSWorker[] crowdServiceSelection = result.getCrowdServiceSelection();
            if (crowdServiceSelection.length == 0) {
                throw new RuntimeException("crowdServiceSelection.length == 0");
            }
            for (CSWorker cw : crowdServiceSelection) {
                List<Integer> index = indexMap.get(cw.getKey());
                for (CrowdWorker worker : cw.getValue()) {
                    worker.setIndex(index.get(worker.getIndex()));
                    if (worker.getSelected()) {
                        crowdWorkers.add(worker);
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return crowdWorkers;
    }

    private Map<String, List<Integer>> saveIndex(List<CSWorker> workers) {
        Map<String, List<Integer>> result = new HashMap<>();
        for (CSWorker worker : workers) {
            ArrayList<Integer> value = new ArrayList<>();
            int i = 0;
            for (CrowdWorker w : worker.getValue()) {
                value.add(w.getIndex());
                w.setIndex(i++);
            }
            result.put(worker.getKey(), value);
        }
        return result;
    }
}
