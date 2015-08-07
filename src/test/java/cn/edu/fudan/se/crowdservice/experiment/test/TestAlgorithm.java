package cn.edu.fudan.se.crowdservice.experiment.test;

import cn.edu.fudan.se.crowdservice.algorithm.Algorithm;
import cn.edu.fudan.se.crowdservice.algorithm.OnlyCostNaiveAlgorithm;
import cn.edu.fudan.se.crowdservice.algorithm.TianHuatAlgorithm;
import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.CrowdWorkerGroups;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerDAO;
import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerGroupDAO;
import cn.edu.fudan.se.crowdservice.dao.ResetDBDAO;
import cn.edu.fudan.se.crowdservice.explogic.BPELXml;
import cn.edu.fudan.se.crowdservice.util.Logger;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;
import org.junit.Test;
import sutd.edu.sg.CrowdWorker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dawnwords on 2015/8/7.
 */
public class TestAlgorithm {
    static {
        System.setProperty("Debug", "true");
    }

    @Test
    public void testNaiveAlgorithm() {
        testAlgorithm(new OnlyCostNaiveAlgorithm());
    }

    @Test
    public void testTianHuatAlgorithm() {
        testAlgorithm(new TianHuatAlgorithm());
    }

    private void testAlgorithm(Algorithm algorithm) {
        Random random = new Random(1235);
        new ResetDBDAO().getResult();
        new GenerateWorkerDAO().random(random).workerNumber(100).getResult();
        CrowdWorkerGroups groups = new GenerateWorkerGroupDAO().random(random).cs1GroupNum(40).cs2GroupNum(60).getResult();
        Long deadline = 1000L;//new DeadlineGen().generate(random);
        Double cost = 300d;//new CostGen().generate(random);

        AlgorithmParameter parameter = new AlgorithmParameter()
                .compositeServiceXML(BPELXml.CS1_CS2)
                .deadline(deadline)
                .cost(cost)
                .resultNums(Arrays.asList(
                        new CSResultNum(BPELXml.CS1_NAME, 3),
                        new CSResultNum(BPELXml.CS2_NAME, 2)
                ))
                .workers(Arrays.asList(
                        new CSWorker(BPELXml.CS1_NAME, groups.cs1GroupArray()),
                        new CSWorker(BPELXml.CS2_NAME, groups.cs2GroupArray())
                ));
        Logger.info("Global Optimization Input:" + parameter);
        TimeCost timeCost = algorithm.globalOptimize(parameter);

        assertTrue(timeCost.cost() < cost);
        assertTrue(timeCost.time() < deadline);

        parameter = new AlgorithmParameter()
                .compositeServiceXML(BPELXml.CS1)
                .deadline(timeCost.time())
                .cost(timeCost.cost())
                .resultNums(Collections.singletonList(new CSResultNum(BPELXml.CS1_NAME, 3)))
                .workers(Collections.singletonList(new CSWorker(BPELXml.CS1_NAME, groups.cs1GroupArray())));

        Logger.info("Worker Selection Input:" + parameter);

        List<CrowdWorker> selectedWorkers = algorithm.workerSelection(parameter);
        Logger.info("Selected Worker:");
        for (CrowdWorker crowdWorker : selectedWorkers) {
            System.out.println(crowdWorker);
        }
    }

}
