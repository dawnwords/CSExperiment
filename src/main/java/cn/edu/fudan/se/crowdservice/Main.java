package cn.edu.fudan.se.crowdservice;

import cn.edu.fudan.se.crowdservice.algorithm.Algorithm;
import cn.edu.fudan.se.crowdservice.algorithm.OnlyCostNaiveAlgorithm;
import cn.edu.fudan.se.crowdservice.bean.ExpResult;
import cn.edu.fudan.se.crowdservice.bean.ExperimentInput;
import cn.edu.fudan.se.crowdservice.dao.GenerateInputDAO;
import cn.edu.fudan.se.crowdservice.dao.SelectExperimentInputDAO;
import cn.edu.fudan.se.crowdservice.explogic.Experiment;

import java.util.List;
import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Main {
    public static void main(String[] args) {
        Random random = new Random(807090336);
        Algorithm algorithm = new OnlyCostNaiveAlgorithm();
        new GenerateInputDAO().random(random).experimentTimes(3);
        List<ExperimentInput> inputs = new SelectExperimentInputDAO().getResult();
        for (ExperimentInput input : inputs) {
            ExpResult expResult = new Experiment(input.random(random).algorithm(algorithm)).preform();
        }
    }
}
