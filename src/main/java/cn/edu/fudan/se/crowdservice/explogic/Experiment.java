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
                .cs3GroupNum(input.cs3GroupNum())
                .random(input.random()).getResult();
        AlgorithmParameter cs1GO = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs1Name())
                .bpelPath(Parameter.instance().bpelPath() + "cs1_go.bpel")
                .addServiceSetting(Parameter.instance().cs1Name(), new ServiceSetting().resultNum(input.cs1ResultNum()).workerGroup(workerGroups.cs1Group()))
                .addServiceSetting(Parameter.instance().cs2Name(), new ServiceSetting().resultNum(input.cs2ResultNum()).workerGroup(workerGroups.cs2Group()))
                .addServiceSetting(Parameter.instance().cs3Name(), new ServiceSetting().resultNum(input.cs3ResultNum()).workerGroup(workerGroups.cs3Group()));
        AlgorithmParameter cs1WS = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs1Name())
                .bpelPath(Parameter.instance().bpelPath() + "cs1_ws.bpel")
                .addServiceSetting(Parameter.instance().cs1Name(), new ServiceSetting().resultNum(input.cs1ResultNum()).workerGroup(workerGroups.cs1Group()));

        AlgorithmParameter cs2GO = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs2Name())
                .bpelPath(Parameter.instance().bpelPath() + "cs2_go.bpel")
                .addServiceSetting(Parameter.instance().cs2Name(), new ServiceSetting().resultNum(input.cs2ResultNum()).workerGroup(workerGroups.cs2Group()))
                .addServiceSetting(Parameter.instance().cs3Name(), new ServiceSetting().resultNum(input.cs3ResultNum()).workerGroup(workerGroups.cs3Group()));
        AlgorithmParameter cs2WS = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs2Name())
                .bpelPath(Parameter.instance().bpelPath() + "cs2_ws.bpel")
                .addServiceSetting(Parameter.instance().cs2Name(), new ServiceSetting().resultNum(input.cs2ResultNum()).workerGroup(workerGroups.cs2Group()));

        AlgorithmParameter cs3GO = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs3Name())
                .bpelPath(Parameter.instance().bpelPath() + "cs3_go.bpel")
                .addServiceSetting(Parameter.instance().cs3Name(), new ServiceSetting().resultNum(input.cs3ResultNum()).workerGroup(workerGroups.cs3Group()));
        AlgorithmParameter cs3WS = new AlgorithmParameter()
                .expId(input.expId())
                .currentService(Parameter.instance().cs3Name())
                .bpelPath(Parameter.instance().bpelPath() + "cs3_ws.bpel")
                .addServiceSetting(Parameter.instance().cs3Name(), new ServiceSetting().resultNum(input.cs3ResultNum()).workerGroup(workerGroups.cs3Group()));


        try {
            executeCS("CS1", cs1GO, cs1WS);
            executeCS("CS2", cs2GO, cs2WS);
            executeCS("CS3", cs3GO, cs3WS);
        } catch (Exception e) {
            Logger.info(e.getMessage());
        }
    }

    private void executeCS(String cs, AlgorithmParameter globalPlanningPara, AlgorithmParameter workerSelectionPara) {
        Logger.info("Executing %s", cs);
        globalPlanningPara.timeCost(input.timeCost());
        TimeCost planTC = input.algorithm().globalOptimize(globalPlanningPara);
        Logger.info("Global Optimize:" + planTC);

        workerSelectionPara.timeCost(planTC);
        List<CrowdWorker> selectedWorkers = input.algorithm().workerSelection(workerSelectionPara);
        Logger.info("Worker Selection:");
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
        if (realResultNum < globalPlanningPara.currentResultNum()) {
            throw new RuntimeException(cs + " Fails");
        }
        input.timeCost().minus(realTC);
    }
}
