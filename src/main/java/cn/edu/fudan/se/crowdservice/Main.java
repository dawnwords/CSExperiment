package cn.edu.fudan.se.crowdservice;

import cn.edu.fudan.se.crowdservice.algorithm.Algorithm;
import cn.edu.fudan.se.crowdservice.bean.ExperimentInput;
import cn.edu.fudan.se.crowdservice.dao.*;
import cn.edu.fudan.se.crowdservice.datagen.Random;
import cn.edu.fudan.se.crowdservice.explogic.Experiment;
import cn.edu.fudan.se.crowdservice.util.Logger;

import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Main {
    public static final Algorithm ALGORITHM = new cn.edu.fudan.se.crowdservice.algorithm.OnlyReputationNavieAlgorithm();
    public static final boolean CLEAR_EXP = true;
    public static final boolean CLEAR_WORKER = false;
    public static final Random RANDOM = new Random(807090336);
    public static final Random RELIABILITY_RANDOM = new Random(814100102);

    static {
        System.setProperty("Debug", "true");
    }

    public static void main(String[] args) {
        resetDB();
        generateWorker(2000);
        for (ExperimentInput input : generateExperimentInput(10, 40, 60)) {
            try {
                new Experiment(input.random(RANDOM).reliability(RELIABILITY_RANDOM).algorithm(ALGORITHM)).preform();
            } catch (Exception e) {
                Logger.info("Error: %s", e.getMessage());
            }
        }
        Logger.info("==============================================");
        Logger.info(new SelectExpResultDAO().getResult().toString());
    }

    private static void resetDB() {
        ResetDBDAO reset = new ResetDBDAO();
        if (!CLEAR_WORKER) {
            reset.ignoreWorker();
        }
        if (!CLEAR_EXP) {
            reset.ignoreExpinput().ignoreExpstatus();
        }
        reset.getResult();
    }

    private static List<ExperimentInput> generateExperimentInput(int experimentInputNumber, int cs1GroupNumber, int cs2GroupNumber) {
        if (CLEAR_EXP) new GenerateInputDAO().random(RANDOM).experimentTimes(experimentInputNumber).getResult();
        Logger.info("Generate Experiment Input Finish: %d", experimentInputNumber);
        return new SelectExperimentInputDAO().cs1GroupNum(cs1GroupNumber).cs2GroupNum(cs2GroupNumber).getResult();
    }

    private static void generateWorker(int workerNumber) {
        if (CLEAR_WORKER) new GenerateWorkerDAO().random(RANDOM).workerNumber(workerNumber).getResult();
        Logger.info("Generate Worker Finish: %d", workerNumber);
    }
}
