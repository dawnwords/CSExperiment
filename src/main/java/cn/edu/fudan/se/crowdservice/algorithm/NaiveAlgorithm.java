package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.*;

import java.util.*;

public abstract class NaiveAlgorithm implements Algorithm {
    private Comparator<CrowdWorker> comparator;

    protected NaiveAlgorithm(Comparator<CrowdWorker> comparator) {
        this.comparator = comparator;
    }

    @Override
    public TimeCost globalOptimize(AlgorithmParameter parameter) {
        Map<String, ServiceSetting> serviceWorkers = rank(parameter);
        long timeTotal = 0;
        double costTotal = 0;
        TimeCost currentTimeCost = new TimeCost();
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
            timeTotal += serviceTime;
            costTotal += serviceCost;
        }
        return new TimeCost()
                .time(timeTotal == 0 ? 0 : parameter.deadline() * currentTimeCost.time() / timeTotal)
                .cost(costTotal == 0 ? 0 : parameter.cost() * currentTimeCost.cost() / costTotal);
    }

    @Override
    public WorkerSelectionResult workerSelection(AlgorithmParameter parameter) {
        List<CrowdWorker> cw = rank(parameter).get(parameter.currentService()).workerGroup();
        WorkerSelectionResult result = new WorkerSelectionResult().service(parameter.currentService());

        double totalCost = parameter.cost();
        for (CrowdWorker worker : cw) {
            if (totalCost > worker.cost()) {
                totalCost -= worker.cost();
                result.addWorker(worker);
            }
        }
        if (result.workers().size() == 0) {
            throw new RuntimeException("Worker Selection Fail");
        }
        TimeCost executeTimeCost = new TimeCost();
        for (CrowdWorker worker : result.workers()) {
            executeTimeCost.aggregate(worker.responseTime(), worker.cost());
        }
        result.executeTimeCost(executeTimeCost);
        return result;
    }

    private Map<String, ServiceSetting> rank(AlgorithmParameter parameter) {
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
}
