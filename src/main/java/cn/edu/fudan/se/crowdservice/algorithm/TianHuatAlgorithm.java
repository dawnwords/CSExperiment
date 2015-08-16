package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.util.Logger;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList.CSWorker;
import servicebehavior.CrowdService;
import servicebehavior.CrowdServiceImplement;
import sutd.edu.sg.CrowdOptimizationResult;
import sutd.edu.sg.CrowdWorker;

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
                cost += worker.getCost();
                time = Math.max(time, worker.getResponseTime());
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
        CrowdService service = new CrowdServiceImplement().getBasicHttpBindingCrowdService();
        Logger.info("[service version] %s", service.getVersion());
        CrowdOptimizationResult result = service.globalOptimize(
                parameter.compositeServiceXML(),
                parameter.deadline(),
                parameter.cost(),
                parameter.workers(),
                parameter.resultNums(),
                ITERATION_NUM);
        restoreIndex(parameter.workers(), indexMap);
        CSWorkerList crowdServiceSelection = result.getCrowdServiceSelection().getValue();
        if (crowdServiceSelection.getCSWorker().size() == 0) {
            throw new RuntimeException("Worker Selection: Fails");
        }

        CSWorker cw = crowdServiceSelection.getCSWorker().get(0);
        List<Integer> index = indexMap.get(cw.getKey());
        for (CrowdWorker worker : cw.getValue().getCrowdWorker()) {
            worker.setIndex(index.get(worker.getIndex()));
            if (worker.isSelected()) {
                crowdWorkers.add(worker);
            }
        }
        return crowdWorkers;
    }

    private Map<String, List<Integer>> saveIndex(CSWorkerList workers) {
        Map<String, List<Integer>> result = new HashMap<>();
        for (CSWorker worker : workers.getCSWorker()) {
            ArrayList<Integer> value = new ArrayList<>();
            int i = 0;
            for (CrowdWorker w : worker.getValue().getCrowdWorker()) {
                value.add(w.getIndex());
                w.setIndex(i++);
            }
            result.put(worker.getKey(), value);
        }
        return result;
    }

    private void restoreIndex(CSWorkerList workers, Map<String, List<Integer>> indexMap) {
        for (CSWorker worker : workers.getCSWorker()) {
            List<Integer> index = indexMap.get(worker.getKey());
            for (CrowdWorker w : worker.getValue().getCrowdWorker()) {
                w.setIndex(index.get(w.getIndex()));
            }
        }
    }
}
