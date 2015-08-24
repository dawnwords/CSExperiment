package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.*;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public abstract class NaiveAlgorithm implements Algorithm {
    private Comparator<CrowdWorker> comparator;

    protected NaiveAlgorithm(Comparator<CrowdWorker> comparator) {
        this.comparator = comparator;
    }

    @Override
    public TimeCost globalOptimize(AlgorithmParameter parameter) {
        Map<String, ServiceSetting> serviceWorkers = rank(parameter, "go");
        String outputPath = String.format("%sEXP-%d-%s-go-output", Parameter.instance().ioPath(), parameter.expId(), parameter.currentService());

        long timeTotal = 0;
        double costTotal = 0;
        TimeCost currentTimeCost = new TimeCost();
        List<WorkerSelectionResult> print = new LinkedList<>();
        for (String service : serviceWorkers.keySet()) {
            ServiceSetting setting = serviceWorkers.get(service);

            long serviceTime = 0;
            double serviceCost = 0;
            WorkerSelectionResult result = new WorkerSelectionResult().service(service);
            for (int j = 0; j < setting.workerGroup().size() && j < setting.resultNum(); j++) {
                CrowdWorker worker = setting.workerGroup().get(j);
                result.addWorker(worker);
                serviceTime = Math.max(serviceTime, worker.responseTime());
                serviceCost += worker.cost();
            }
            if (service.equals(parameter.currentService())) {
                currentTimeCost = new TimeCost().cost(serviceCost).time(serviceTime);
            }
            print.add(result);
            timeTotal += serviceTime;
            costTotal += serviceCost;
        }
        printResult(print, outputPath);
        return new TimeCost()
                .time(timeTotal == 0 ? 0 : parameter.deadline() * currentTimeCost.time() / timeTotal)
                .cost(costTotal == 0 ? 0 : parameter.cost() * currentTimeCost.cost() / costTotal);
    }

    @Override
    public WorkerSelectionResult workerSelection(AlgorithmParameter parameter) {
        List<CrowdWorker> cw = rank(parameter, "ws").get(parameter.currentService()).workerGroup();
        WorkerSelectionResult print = new WorkerSelectionResult().service(parameter.currentService());
        WorkerSelectionResult result = new WorkerSelectionResult();

        double totalCost = parameter.cost();
        for (CrowdWorker worker : cw) {
            if (totalCost > worker.cost()) {
                totalCost -= worker.cost();
                result.addWorker(worker);
                print.addWorker(worker);
            }
        }
        printResult(Collections.singletonList(print),
                String.format("%sEXP-%d-%s-ws-output", Parameter.instance().ioPath(), parameter.expId(), parameter.currentService()));
        if (result.workers().size() == 0) {
            throw new RuntimeException("Worker Selection Fail");
        }
        return result;
    }

    private Map<String, ServiceSetting> rank(AlgorithmParameter parameter, String invoker) {
        String inputPath = String.format("%sEXP-%d-%s-%s-input", Parameter.instance().ioPath(), parameter.expId(), parameter.currentService(), invoker);

        try {
            PrintWriter input = new PrintWriter(new OutputStreamWriter(new FileOutputStream(inputPath, false)));
            input.printf("time=%d,cost=%f\n", parameter.deadline(), parameter.cost());
            input.println("===");
            for (String service : parameter.serviceSettings().keySet()) {
                input.println("key=" + service);
                for (CrowdWorker worker : parameter.serviceSettings().get(service).workerGroup()) {
                    input.println(worker);
                }
            }
            input.println("===");
            for (String service : parameter.serviceSettings().keySet()) {
                input.printf("CSResultNum{key='%s', value=%d}\n", service, parameter.serviceSettings().get(service).resultNum());
            }
            input.flush();
            input.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<String, ServiceSetting> result = new HashMap<>();
        for (String service : parameter.serviceSettings().keySet()) {
            List<CrowdWorker> newcw = new ArrayList<>();
            ServiceSetting setting = parameter.serviceSettings().get(service);
            for (CrowdWorker w : setting.workerGroup()) {
                if (w.responseTime() < parameter.deadline()) {
                    newcw.add(w);
                }
            }
            Collections.sort(newcw, comparator);
            result.put(service, new ServiceSetting().resultNum(setting.resultNum()).workerGroup(newcw));
        }
        return result;
    }

    private void printResult(List<WorkerSelectionResult> results, String outputPath) {
        try {
            PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputPath, false)));

            output.println("Total Reliability=");
            output.println("===============================================");
            for (WorkerSelectionResult result : results) {
                output.println("key=" + result.service());
                long maxResponseTime = 0;
                double totalCost = 0;
                int count = result.workers().size();
                for (CrowdWorker worker : result.workers()) {
                    output.println(worker);
                    totalCost += worker.cost();
                    maxResponseTime = Math.max(maxResponseTime, worker.responseTime());
                }
                output.println("Total Cost=" + totalCost);
                output.println("Max Response Time=" + maxResponseTime);
                output.println("Selected Cnt=" + count);
                output.println("===============================================");
            }
            output.flush();
            output.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
