package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.datagen.WorkerCostGen;
import cn.edu.fudan.se.crowdservice.datagen.WorkerReliabilityGen;
import cn.edu.fudan.se.crowdservice.datagen.WorkerResponseTimeGen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class GenerateWorkerDAO extends DAO<Boolean> {
    private int workerNumber;
    private Random random;

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
        String sql = "INSERT INTO worker(cost, reliability, responseTime) VALUES (?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        WorkerCostGen cost = new WorkerCostGen();
        WorkerReliabilityGen reliability = new WorkerReliabilityGen();
        WorkerResponseTimeGen responseTime = new WorkerResponseTimeGen();

        int count = 0;
        for (int i = 0; i < workerNumber; i++) {
            ps.setDouble(1, cost.generate(random));
            ps.setDouble(2, reliability.generate(random));
            ps.setLong(3, responseTime.generate(random));
            if (ps.executeUpdate() == 1) {
                count++;
            }
        }
        return count == workerNumber;
    }
}
