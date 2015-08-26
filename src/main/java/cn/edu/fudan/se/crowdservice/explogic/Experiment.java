package cn.edu.fudan.se.crowdservice.explogic;

import cn.edu.fudan.se.crowdservice.Parameter;
import cn.edu.fudan.se.crowdservice.algorithm.Algorithm.AlgorithmFactory;
import cn.edu.fudan.se.crowdservice.bean.*;
import cn.edu.fudan.se.crowdservice.dao.*;
import cn.edu.fudan.se.crowdservice.datagen.Random;
import cn.edu.fudan.se.crowdservice.util.Logger;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Experiment {

    private ExperimentInput input;
    private int executeTimes;

    public Experiment(ExperimentInput input) {
        this.input = input;
        this.executeTimes = Parameter.instance().executeTimes();
    }

    public void preform() {
        for (AlgorithmFactory algorithm : AlgorithmFactory.values()) {
            input.algorithm(algorithm);
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

                    double cs1successRate = 0, cs2successRate = 0, cs3successRate = 0, successRate = 0;
                    try {
                        TimeCost totalTimeCost = new TimeCost(input.timeCost());
                        BitSet cs1Success = executeCS("CS1", input.cs1ResultNum(), totalTimeCost, cs1GO, cs1WS);
                        BitSet cs2Success = executeCS("CS2", input.cs2ResultNum(), totalTimeCost, cs2GO, cs2WS);
                        BitSet cs3Success = executeCS("CS3", input.cs3ResultNum(), totalTimeCost, cs3WS);
                        cs1successRate = successRate(cs1Success);
                        cs2successRate = successRate(cs2Success);
                        cs3successRate = successRate(cs3Success);

                        cs1Success.and(cs2Success);
                        cs1Success.and(cs3Success);
                        successRate = cs1Success.cardinality() / ((double) executeTimes);
                    } catch (Exception e) {
                        Logger.info(e.getMessage());
                    }
                    new UpdateSuccessRateDAO()
                            .expid(input.expId())
                            .cs1successRate(cs1successRate)
                            .cs2successRate(cs2successRate)
                            .cs3successRate(cs3successRate)
                            .successRate(successRate).getResult();
                }
            }
        }
    }

    private double successRate(BitSet success) {
        return success.cardinality() / ((double) executeTimes);
    }

    private BitSet executeCS(String cs, int expectResultNum, TimeCost totalTimeCost, AlgorithmParameter... parameters) {
        Logger.info("Executing Exp%d-%s", parameters[0].expId(), cs);
        TimeCost planTC;
        AlgorithmParameter workerSelectionPara;
        if (parameters.length == 2) {
            parameters[0].timeCost(totalTimeCost);
            planTC = input.algorithm().instance().globalOptimize(parameters[0]);
            Logger.info("Global Optimize:" + planTC);
            workerSelectionPara = parameters[1];
        } else {
            planTC = totalTimeCost;
            workerSelectionPara = parameters[0];
        }
        workerSelectionPara.timeCost(planTC);
        WorkerSelectionResult result = input.algorithm().instance().workerSelection(workerSelectionPara);
        Logger.info("Worker Selection:" + result.workers().size());
        List<ExpStatus> expStatus = new ArrayList<>();

        int[] success = new int[executeTimes];
        for (CrowdWorker worker : result.workers()) {
            expStatus.add(new ExpStatus()
                    .expid(input.expId())
                    .workerid(worker.index())
                    .cs(cs));
            for (int i = 0; i < executeTimes; i++) {
                success[i] += worker.success().charAt(i) - '0';
            }
        }
        TimeCost realTC = result.executeTimeCost();

        new InsertExpStatusDAO().expStatus(expStatus).getResult();
        new UpdateTimeCostResultNumDAO()
                .expId(input.expId())
                .planTC(planTC)
                .realTC(realTC)
                .cs(cs).getResult();
        totalTimeCost.minus(realTC);
        BitSet successBitSet = new BitSet(executeTimes);
        for (int i = 0; i < executeTimes; i++) {
            successBitSet.set(i, success[i] >= expectResultNum);
        }
        Logger.info("Finish Executing: %s", realTC);
        return successBitSet;
    }
}
