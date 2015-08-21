package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;
import cn.edu.fudan.se.crowdservice.bean.ServiceSetting;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;

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

        long timeTotal = 0;
        double costTotal = 0;
        TimeCost currentTimeCost = new TimeCost();
        for (String service : serviceWorkers.keySet()) {
            ServiceSetting setting = serviceWorkers.get(service);

            long serviceTime = 0;
            double serviceCost = 0;
            for (int j = 0; j < setting.workerGroup().size() && j < setting.resultNum(); j++) {
                CrowdWorker worker = setting.workerGroup().get(j);
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
    public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
        List<CrowdWorker> cw = rank(parameter, "ws").get(parameter.currentService()).workerGroup();
        double totalCost = parameter.cost();
        ArrayList<CrowdWorker> result = new ArrayList<>();
        for (CrowdWorker worker : cw) {
            if (totalCost > worker.cost()) {
                totalCost -= worker.cost();
                result.add(worker);
            }
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
}
