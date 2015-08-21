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
        List<WorkerSelectionResult> results = globalOptimize(parameter, ITERATION_NUM).selectionResult();
        if (results.size() == 0) {
            throw new RuntimeException("Global Optimization Fails");
        }
        WorkerSelectionResult result = results.get(0);
        return new TimeCost().cost(result.totalCost()).time(result.maxResponseTime());
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        List<CrowdWorker> crowdWorkers = new LinkedList<>();
        List<WorkerSelectionResult> results = globalOptimize(parameter, ITERATION_NUM).selectionResult();
        if (results.size() == 0) {
            throw new RuntimeException("Worker Selection Fails");
        }

        WorkerSelectionResult cw = results.get(0);
        for (CrowdWorker worker : cw.workers()) {
            if (worker.selected()) {
                crowdWorkers.add(worker);
            }
        }
        return crowdWorkers;
    }

    private OptimizationResult globalOptimize(AlgorithmParameter parameter, int iterationNum, PrintWriter input, PrintWriter output) {
        try {
            String inputPath = Parameter.instance().ioPath(parameter.expId());
            PrintWriter input = new PrintWriter(new OutputStreamWriter(new FileOutputStream(inputPath, false)));
            input.printf("time=%d,cost=%f,numOfGeneration=%d\n", parameter.deadline(), parameter.cost(), iterationNum);
            input.println("===");
            for (ServiceWorkers sw : parameter.workers()) {
                input.println(sw);
            }
            input.println("===");
            for (ServiceResultNum sr : parameter.resultNums()) {
                input.println(sr);
            }
            input.flush();
            input.close();

            String command = String.format("%s \"%s\" \"%s\"", Parameter.instance().thServicePath(), parameter.bpelPath(), inputPath);
            Process service = Runtime.getRuntime().exec(command);
            OptimizationResult result = new OptimizationResult().build(new BufferedReader(new InputStreamReader(service.getInputStream())));

            String outputPath = Parameter.instance().outputPath(parameter.expId());
            PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputPath, false)));
            output.println(result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
