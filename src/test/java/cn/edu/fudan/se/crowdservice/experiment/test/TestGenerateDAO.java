package cn.edu.fudan.se.crowdservice.experiment.test;

import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerDAO;
import cn.edu.fudan.se.crowdservice.dao.SelectWorkerDAO;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;


/**
 * Created by Dawnwords on 2015/8/6.
 */
public class TestGenerateDAO {
    @Test
    public void testGenerateWorkerDAO() {
        Random random = new Random(123);
        int workerNumber = 10;
        assertTrue(new GenerateWorkerDAO().random(random).workerNumber(workerNumber).getResult());
        assertEquals(workerNumber, new SelectWorkerDAO().getResult().size());
    }
}
