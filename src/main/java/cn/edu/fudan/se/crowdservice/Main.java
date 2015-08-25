package cn.edu.fudan.se.crowdservice;

import cn.edu.fudan.se.crowdservice.bean.ExpResult;
import cn.edu.fudan.se.crowdservice.bean.ExperimentInput;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerDAO;
import cn.edu.fudan.se.crowdservice.dao.ResetDBDAO;
import cn.edu.fudan.se.crowdservice.dao.SelectExpResultDAO;
import cn.edu.fudan.se.crowdservice.explogic.Experiment;
import cn.edu.fudan.se.crowdservice.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        printResult();
    }

    private static void printResult() {
        List<ExpResult> results = new SelectExpResultDAO().getResult();
        Map<String, ArrayList<ExpResult>> table = new HashMap<>();
        for (ExpResult result : results) {
            ArrayList<ExpResult> er = table.get(result.algoritm());
            if (er == null) {
                er = new ArrayList<>();
                table.put(result.algoritm(), er);
            }
            er.add(result.settingId(), result);
        }

        boolean printHeader = true;
        StringBuilder sb = new StringBuilder();
        for (String algorithm : table.keySet()) {
            ArrayList<ExpResult> array = table.get(algorithm);
            if (printHeader) {
                sb.append("Algorithm\t");
                for (ExpResult result : array) {
                    sb.append(result.setting());
                    sb.append("\t");
                }
                sb.append('\n');
                printHeader = false;
            }
            sb.append(algorithm);
            sb.append('\t');
            for (ExpResult result : array) {
                sb.append(result.successRate());
                sb.append("\t");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
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

        int settingId = 0;
        for (String token : Parameter.instance().resultNums().split("\\|")) {
            String[] resultNums = token.split(",");
            result.add(new ExperimentInput()
                    .settingId(settingId++)
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
