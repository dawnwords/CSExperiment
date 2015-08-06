package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;
import sutd.edu.sg.CrowdOptimizationResult;
import sutd.edu.sg.CrowdServiceProxy;
import sutd.edu.sg.CrowdWorker;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class TianHuatAlgorithm implements Algorithm {

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
        try {
            CrowdOptimizationResult result = new CrowdServiceProxy().globalOptimize(
                    parameter.compositeServiceXML(),
                    parameter.deadline(),
                    parameter.cost(),
                    parameter.workerArray(),
                    parameter.resultNumArray(),
                    parameter.iterationNum());
            CSWorker[] crowdServiceSelection = result.getCrowdServiceSelection();
            if (crowdServiceSelection.length == 0) {
                throw new RuntimeException("crowdServiceSelection.length == 0");
            }
            for (CSWorker cw : crowdServiceSelection) {
                for (CrowdWorker worker : cw.getValue()) {
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
}
