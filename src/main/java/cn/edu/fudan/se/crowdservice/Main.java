package cn.edu.fudan.se.crowdservice;

import cn.edu.fudan.se.crowdservice.bean.ExperimentInput;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerDAO;
import cn.edu.fudan.se.crowdservice.dao.ResetDBDAO;
import cn.edu.fudan.se.crowdservice.explogic.Experiment;
import cn.edu.fudan.se.crowdservice.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Main {

    static {
        System.setProperty("Debug", "true");
    }

    public static void main(String[] args) {
        resetDB();
        generateWorker();
        for (ExperimentInput input : getExperimentInputs()) {
            try {
                new Experiment(input).preform();
            } catch (Exception e) {
                Logger.info("Error: %s", e.getMessage());
            }
        }
        Logger.info("==============================================");
        //TODO calculate result
    }

    private static void resetDB() {
        ResetDBDAO reset = new ResetDBDAO();
        if (!Parameter.instance().clearWorkers()) {
            reset.ignoreWorker();
        }
        if (!Parameter.instance().clearExps()) {
            reset.ignoreExpinput().ignoreExpstatus();
        }
        reset.getResult();
    }


    private static void generateWorker() {
        if (Parameter.instance().clearWorkers()) {
            new GenerateWorkerDAO()
                    .random(Parameter.instance().workerRandom())
                    .workerNumber(Parameter.instance().workerSize())
                    .executeTimes(Parameter.instance().executeTimes())
                    .getResult();
        }
        Logger.info("Generate Worker: %d", Parameter.instance().workerSize());
    }

    private static List<ExperimentInput> getExperimentInputs() {
        ArrayList<ExperimentInput> result = new ArrayList<>();
        TimeCost totalTimeCost = Parameter.instance().totalTimeCost();
        String[] groupSizes = Parameter.instance().workerGroupSize().split(",");
        int cs1GroupSize = Integer.parseInt(groupSizes[0]);
        int cs2GroupSize = Integer.parseInt(groupSizes[1]);
        int cs3GroupSize = Integer.parseInt(groupSizes[2]);

        for (String token : Parameter.instance().resultNums().split("|")) {
            String[] resultNums = token.split(",");
            result.add(new ExperimentInput()
                    .cs1GroupNum(cs1GroupSize)
                    .cs2GroupNum(cs2GroupSize)
                    .cs3GroupNum(cs3GroupSize)
                    .cs1ResultNum(Integer.parseInt(resultNums[0]))
                    .cs2ResultNum(Integer.parseInt(resultNums[1]))
                    .cs3ResultNum(Integer.parseInt(resultNums[2]))
                    .timeCost(totalTimeCost));
        }
        return result;
    }
}
