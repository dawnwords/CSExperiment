package cn.edu.fudan.se.crowdservice.explogic;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.bean.*;
import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerGroupDAO;
import cn.edu.fudan.se.crowdservice.dao.InsertExpStatusDAO;
import cn.edu.fudan.se.crowdservice.dao.UpdateTimeCostResultNumDAO;
import cn.edu.fudan.se.crowdservice.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Experiment {

    private ExperimentInput input;

    public Experiment(ExperimentInput input) {
        this.input = input;
    }

    public void preform() {
        CrowdWorkerGroups workerGroups = new GenerateWorkerGroupDAO()
                .cs1GroupNum(input.cs1GroupNum())
                .cs2GroupNum(input.cs2GroupNum())
                .random(input.random()).getResult();
        AlgorithmParameter cs1GP = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs1Name())
                .bpelPath(Parameter.instance().cs1cs2Path())
                .addServiceSetting(Parameter.instance().cs1Name(), new ServiceSetting().resultNum(input.cs1ResultNum()).workerGroup(workerGroups.cs1Group()))
                .addServiceSetting(Parameter.instance().cs2Name(), new ServiceSetting().resultNum(input.cs2ResultNum()).workerGroup(workerGroups.cs2Group()));
        AlgorithmParameter cs1WS = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs1Name())
                .bpelPath(Parameter.instance().cs1Path())
                .addServiceSetting(Parameter.instance().cs1Name(), new ServiceSetting().resultNum(input.cs1ResultNum()).workerGroup(workerGroups.cs1Group()));
        AlgorithmParameter cs2GP = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs2Name())
                .bpelPath(Parameter.instance().cs2Path())
                .addServiceSetting(Parameter.instance().cs2Name(), new ServiceSetting().resultNum(input.cs2ResultNum()).workerGroup(workerGroups.cs2Group()));

        try {
            executeCS("CS1", cs1GP, cs1WS);
            executeCS("CS2", cs2GP, cs2GP);
        } catch (Exception e) {
            Logger.info(e.getMessage());
        }
    }

    private void executeCS(String cs, AlgorithmParameter globalPlanningPara, AlgorithmParameter workerSelectionPara) {
        Logger.info("Executing %s", cs);
        globalPlanningPara.timeCost(input.timeCost());
        Logger.info("Global Optimization Input:" + globalPlanningPara);
        TimeCost planTC = input.algorithm().globalOptimize(globalPlanningPara);
        Logger.info("Finish Global Optimize:" + planTC);

        workerSelectionPara.timeCost(planTC);
        Logger.info("Worker Selection Input:" + workerSelectionPara);
        List<CrowdWorker> selectedWorkers = input.algorithm().workerSelection(workerSelectionPara);
        Logger.info("Finish Worker Selection:");
        for (CrowdWorker worker : selectedWorkers) {
            Logger.info("%s", worker);
        }

        List<ExpStatus> expStatus = new ArrayList<>();
        TimeCost realTC = new TimeCost();
        int realResultNum = 0;
        for (CrowdWorker worker : selectedWorkers) {
            boolean success = worker.reliability() > input.reliability() &&
                    worker.responseTime() < planTC.time();
            expStatus.add(new ExpStatus()
                    .expid(input.expId())
                    .workerid(worker.index())
                    .cs(cs)
                    .selected(true)
                    .success(success));
            if (success) {
                realTC.aggregate(worker.responseTime(), worker.cost());
                realResultNum++;
            }
        }

        new InsertExpStatusDAO().expStatus(expStatus).getResult();
        new UpdateTimeCostResultNumDAO()
                .expId(input.expId())
                .planTC(planTC)
                .realTC(realTC)
                .realResultNum(realResultNum)
                .cs(cs).getResult();
        Logger.info("Finish Executing: %s", realTC);
        input.timeCost().minus(realTC);
    }
}
