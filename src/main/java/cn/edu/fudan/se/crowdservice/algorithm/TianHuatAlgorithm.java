package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

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
        OptimizationResult result = globalOptimize(
                parameter.bpelPath(),
                parameter.deadline(),
                parameter.cost(),
                parameter.workers(),
                parameter.resultNums(),
                ITERATION_NUM);
        List<WorkerSelectionResult> crowdServiceSelection = result.selectionResult();
        if (crowdServiceSelection.size() == 0) {
            throw new RuntimeException("Worker Selection: Fails");
        }

        WorkerSelectionResult cw = crowdServiceSelection.get(0);
        for (CrowdWorker worker : cw.workers()) {
            if (worker.selected()) {
                crowdWorkers.add(worker);
            }
        }
        return crowdWorkers;
    }

    private OptimizationResult globalOptimize(String bpelPath, long deadline, double cost, List<ServiceWorkers> csWorkers,
                                              List<ServiceResultNum> csResultNums, int iterationNum) {
        try {
            PrintWriter parameter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Parameter.instance().parameterPath(), false)));
            parameter.printf("time=%d,cost=%f,numOfGeneration=%d\n", deadline, cost, iterationNum);
            parameter.println("===");
            for (ServiceWorkers sw : csWorkers) {
                parameter.println(sw);
            }
            parameter.println("===");
            for (ServiceResultNum sr : csResultNums) {
                parameter.println(sr);
            }
            parameter.flush();
            parameter.close();

            String command = String.format("%s \"%s\" \"%s\"", Parameter.instance().thServicePath(), bpelPath, Parameter.instance().parameterPath());
            Process service = Runtime.getRuntime().exec(command);
            return new OptimizationResult().build(new BufferedReader(new InputStreamReader(service.getInputStream())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
