package cn.edu.fudan.se.crowdservice;

import cn.edu.fudan.se.crowdservice.algorithm.Algorithm;
import cn.edu.fudan.se.crowdservice.algorithm.OnlyCostNaiveAlgorithm;
import cn.edu.fudan.se.crowdservice.bean.ExpResult;
import cn.edu.fudan.se.crowdservice.bean.ExperimentInput;
import cn.edu.fudan.se.crowdservice.dao.GenerateInputDAO;
import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerDAO;
import cn.edu.fudan.se.crowdservice.dao.SelectExperimentInputDAO;
import cn.edu.fudan.se.crowdservice.explogic.Experiment;
import cn.edu.fudan.se.crowdservice.util.Logger;

import java.util.List;
import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Main {

    public static final Random RANDOM = new Random(807090336);
    public static final Algorithm ALGORITHM = new OnlyCostNaiveAlgorithm();

    public static void main(String[] args) {
        generateWorker(2000);
        for (ExperimentInput input : generateExperimentInput(3, 400, 600)) {
            ExpResult expResult = new Experiment(input.random(RANDOM).algorithm(ALGORITHM)).preform();
            Logger.info(expResult.toString());
        }
    }

    private static List<ExperimentInput> generateExperimentInput(int experimentInputNumber, int cs1GroupNumber, int cs2GroupNumber) {
//        new GenerateInputDAO().random(RANDOM).experimentTimes(experimentInputNumber).getResult();
        Logger.info("Generate Experiment Input Finish: %d", experimentInputNumber);
        return new SelectExperimentInputDAO().cs1GroupNum(cs1GroupNumber).cs2GroupNum(cs2GroupNumber).getResult();
    }

    private static void generateWorker(int workerNumber) {
//        new GenerateWorkerDAO().random(RANDOM).workerNumber(workerNumber).getResult();
        Logger.info("Generate Worker Finish: %d", workerNumber);
    }
}
