package cn.edu.fudan.se.crowdservice.datagen;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dawnwords on 2015/8/24.
 */
public class DataGenTest {

    @Test
    public void testWorkerGen() throws Exception {
        Random random = new Random(123);
        WorkerGenerator workerGen = new WorkerGenerator();
        for (int i = 0; i < 10000; i++) {
            double reliability = workerGen.generate(random).reliability();
            System.out.println(reliability);
            assertTrue(reliability < 1 && reliability > 0.4);
        }
    }
}