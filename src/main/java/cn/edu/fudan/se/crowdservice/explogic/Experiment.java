package cn.edu.fudan.se.crowdservice.explogic;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.algorithm.Algorithm.AlgorithmFactory;
import cn.edu.fudan.se.crowdservice.bean.*;
import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerGroupDAO;
import cn.edu.fudan.se.crowdservice.dao.InsertExpInputDAO;
import cn.edu.fudan.se.crowdservice.dao.InsertExpStatusDAO;
import cn.edu.fudan.se.crowdservice.dao.UpdateTimeCostResultNumDAO;
import cn.edu.fudan.se.crowdservice.datagen.Random;
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
        for (AlgorithmFactory algorithm : AlgorithmFactory.values()) {
            Random workerGroupRandom = Parameter.instance().workerGroupRandom();
            for (int i = 0; i < Parameter.instance().expTimes(); i++) {
                CrowdWorkerGroups workerGroups = new GenerateWorkerGroupDAO()
                        .cs1GroupNum(input.cs1GroupNum())
                        .cs2GroupNum(input.cs2GroupNum())
                        .cs3GroupNum(input.cs3GroupNum())
                        .random(workerGroupRandom).getResult();
                for (int j = 0; j < algorithm.times(); j++) {
                    input = new InsertExpInputDAO().expTimes(i).input(input).getResult();
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

                    AlgorithmParameter cs3WS = new AlgorithmParameter()
                            .expId(input.expId())
                            .currentService(Parameter.instance().cs3Name())
                            .bpelPath(Parameter.instance().bpelPath() + "cs3_ws.bpel")
                            .addServiceSetting(Parameter.instance().cs3Name(), new ServiceSetting().resultNum(input.cs3ResultNum()).workerGroup(workerGroups.cs3Group()));

                    try {
                        TimeCost totalTimeCost = new TimeCost(input.timeCost());
                        executeCS("CS1", algorithm, totalTimeCost, cs1GO, cs1WS);
                        executeCS("CS2", algorithm, totalTimeCost, cs2GO, cs2WS);
                        executeCS("CS3", algorithm, totalTimeCost, cs3WS);
                    } catch (Exception e) {
                        Logger.info(e.getMessage());
                    }
                }
            }
        }
    }

    private void executeCS(String cs, AlgorithmFactory algorithm, TimeCost totalTimeCost, AlgorithmParameter... parameters) {
        Logger.info("Executing Exp%d-%s", parameters[0].expId(), cs);
        TimeCost planTC;
        AlgorithmParameter workerSelectionPara;
        if (parameters.length == 2) {
            parameters[0].timeCost(totalTimeCost);
            planTC = algorithm.instance().globalOptimize(parameters[0]);
            Logger.info("Global Optimize:" + planTC);
            workerSelectionPara = parameters[1];
        } else {
            planTC = totalTimeCost;
            workerSelectionPara = parameters[0];
        }
        workerSelectionPara.timeCost(planTC);
        WorkerSelectionResult result = algorithm.instance().workerSelection(workerSelectionPara);
        Logger.info("Worker Selection:");
        List<ExpStatus> expStatus = new ArrayList<>();
        for (CrowdWorker worker : result.workers()) {
            Logger.info("%s", worker);
            expStatus.add(new ExpStatus()
                    .expid(input.expId())
                    .workerid(worker.index())
                    .cs(cs)
                    .algorithm(algorithm.name()));
        }
        TimeCost realTC = result.executeTimeCost();

        new InsertExpStatusDAO().expStatus(expStatus).getResult();
        new UpdateTimeCostResultNumDAO()
                .expId(input.expId())
                .planTC(planTC)
                .realTC(realTC)
                .cs(cs).getResult();
        Logger.info("Finish Executing: %s", realTC);
        totalTimeCost.minus(realTC);
    }
}
