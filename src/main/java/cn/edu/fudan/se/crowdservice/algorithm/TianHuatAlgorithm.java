package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.*;

import java.io.*;
import java.util.Map;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class TianHuatAlgorithm implements Algorithm {
    private static final int ITERATION_NUM = 1000;

    @Override
    public TimeCost globalOptimize(AlgorithmParameter parameter) {
        WorkerSelectionResult result = globalOptimize(parameter, "go");
        if (result == null) {
            throw new RuntimeException("Global Optimization Fails");
        }
        return result.planTimeCost();
    }

    @Override
    public WorkerSelectionResult workerSelection(AlgorithmParameter parameter) {
        WorkerSelectionResult wsr = globalOptimize(parameter, "ws");
        if (wsr == null || wsr.workers().size() == 0) {
            throw new RuntimeException("Worker Selection Fails");
        }
        return wsr;
    }

    private WorkerSelectionResult globalOptimize(AlgorithmParameter parameter, String invoker) {
        try {
            Map<String, ServiceSetting> serviceSettings = parameter.serviceSettings();

            String inputPath = String.format("%sEXP-%d-%s-%s-input", Parameter.instance().ioPath(), parameter.expId(), parameter.currentService(), invoker);
            String outputPath = String.format("%sEXP-%d-%s-%s-output", Parameter.instance().ioPath(), parameter.expId(), parameter.currentService(), invoker);

            PrintWriter input = new PrintWriter(new OutputStreamWriter(new FileOutputStream(inputPath, false)));
            input.printf("time=%d,cost=%f,numOfGeneration=%d\n", parameter.deadline(), parameter.cost(), ITERATION_NUM);
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
            }))).selectionResult().get(parameter.currentService());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
