package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;
import cn.edu.fudan.se.crowdservice.datagen.Random;
import cn.edu.fudan.se.crowdservice.datagen.WorkerGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class GenerateWorkerDAO extends DAO<Boolean> {
    private int workerNumber, executeTimes;
    private Random random;

    public GenerateWorkerDAO executeTimes(int expTimes) {
        this.executeTimes = expTimes;
        return this;
    }

    public GenerateWorkerDAO workerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
        return this;
    }

    public GenerateWorkerDAO random(Random random) {
        this.random = random;
        return this;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        WorkerGenerator workerGen = new WorkerGenerator();

        PreparedStatement ps = connection.prepareStatement("INSERT INTO worker(cost, reliability, responseTime, success) VALUES (?,?,?,?)");
        for (int i = 0; i < workerNumber; i++) {
            CrowdWorker worker = workerGen.generate(random);
            ps.setDouble(1, worker.cost());
            ps.setDouble(2, worker.reliability());
            ps.setLong(3, worker.responseTime());
            ps.setString(4, worker.success());
            ps.addBatch();
        }
        int[] result = ps.executeBatch();
        for (int r : result) if (r != 1) return false;
        return true;
    }
}
