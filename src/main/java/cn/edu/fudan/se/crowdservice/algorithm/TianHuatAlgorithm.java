package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class TianHuatAlgorithm implements Algorithm {
    private static final int ITERATION_NUM = 1000;

    @Override
    public TimeCost globalOptimize(AlgorithmParameter parameter) {
        Map<String, WorkerSelectionResult> results = globalOptimize(parameter, ITERATION_NUM, "go").selectionResult();
        WorkerSelectionResult result = results.get(parameter.currentService());
        if (result == null) {
            throw new RuntimeException("Global Optimization Fails");
        }
        return new TimeCost().cost(result.totalCost()).time(result.maxResponseTime());
    }

    @Override
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        List<CrowdWorker> crowdWorkers = new LinkedList<>();
        Map<String, WorkerSelectionResult> results = globalOptimize(parameter, ITERATION_NUM, "ws").selectionResult();
        WorkerSelectionResult cw = results.get(parameter.currentService());
        if (cw == null) {
            throw new RuntimeException("Worker Selection Fails");
        }
        for (CrowdWorker worker : cw.workers()) {
            if (worker.selected()) {
                crowdWorkers.add(worker);
            }
        }
        return crowdWorkers;
    }

    private OptimizationResult globalOptimize(AlgorithmParameter parameter, int iterationNum, String invoker) {
        try {
            Map<String, ServiceSetting> serviceSettings = parameter.serviceSettings();

            String inputPath = String.format("%sEXP-%d-%s-%s-input", Parameter.instance().ioPath(), parameter.expId(), parameter.currentService(), invoker);
            String outputPath = String.format("%sEXP-%d-%s-%s-output", Parameter.instance().ioPath(), parameter.expId(), parameter.currentService(), invoker);

            PrintWriter input = new PrintWriter(new OutputStreamWriter(new FileOutputStream(inputPath, false)));
            input.printf("time=%d,cost=%f,numOfGeneration=%d\n", parameter.deadline(), parameter.cost(), iterationNum);
            input.println("===");
            for (String service : serviceSettings.keySet()) {
                input.println("key=" + service);
                for (CrowdWorker worker : serviceSettings.get(service).workerGroup()) {
                    input.println(worker);
                }
            }
            input.println("===");
            for (String service : serviceSettings.keySet()) {
                input.printf("CSResultNum{key='%s', value=%d}\n", service, serviceSettings.get(service).resultNum());
            }
            input.flush();
            input.close();

            String command = String.format("%s \"%s\" \"%s\"", Parameter.instance().thServicePath(), parameter.bpelPath(), inputPath);
            final Process service = Runtime.getRuntime().exec(command);
            final PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputPath, false)));
            return new OptimizationResult().build(new BufferedReader(new InputStreamReader(new InputStream() {
                private InputStream in = service.getInputStream();

                @Override
                public int read() throws IOException {
                    int read = in.read();
                    if (read >= 0) output.write(read);
                    return read;
                }

                @Override
                public void close() throws IOException {
                    output.flush();
                    output.close();
                }
            })));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
