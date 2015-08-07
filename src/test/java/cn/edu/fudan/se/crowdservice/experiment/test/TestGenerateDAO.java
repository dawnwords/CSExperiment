package cn.edu.fudan.se.crowdservice.experiment.test;

import cn.edu.fudan.se.crowdservice.bean.CrowdWorkerGroups;
import cn.edu.fudan.se.crowdservice.dao.*;
import org.junit.Before;
import org.junit.Test;
import sutd.edu.sg.CrowdWorker;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Dawnwords on 2015/8/6.
 */
public class TestGenerateDAO {
    Random random = new Random(123);

    @Before
    public void resetDB() {
        new ResetDBDAO().getResult();
    }

    @Test
    public void testGenerateWorkerDAO() {
        int workerNumber = 10;
        assertTrue(new GenerateWorkerDAO().random(random).workerNumber(workerNumber).getResult());
        assertEquals(workerNumber, new SelectWorkerDAO().getResult().size());
    }

    @Test
    public void testGenerateInputDAO() {
        int experimentTimes = 10;
        assertTrue(new GenerateInputDAO().random(random).experimentTimes(experimentTimes).getResult());
        assertEquals(experimentTimes, new SelectExperimentInputDAO().getResult().size());
    }

    @Test
    public void testGenerateWorkerGroupDAO() {
        int cs1Num = 10;
        int cs2Num = 20;
        int total = 100;

        assertTrue(new GenerateWorkerDAO().random(random).workerNumber(total).getResult());
        CrowdWorkerGroups result = new GenerateWorkerGroupDAO().random(random)
                .cs1GroupNum(cs1Num).cs2GroupNum(cs2Num).getResult();
        assertEquals(cs1Num, result.cs1Group().size());
        assertEquals(cs2Num, result.cs2Group().size());

        System.out.println("---------- cs1 ----------");
        for (CrowdWorker worker : result.cs1Group()) {
            System.out.println(worker);
        }

        System.out.println("---------- cs2 ----------");
        for (CrowdWorker worker : result.cs2Group()) {
            System.out.println(worker);
        }
    }
}
