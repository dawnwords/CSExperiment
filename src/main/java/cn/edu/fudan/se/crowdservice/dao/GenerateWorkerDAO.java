package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;
import cn.edu.fudan.se.crowdservice.datagen.Random;
import cn.edu.fudan.se.crowdservice.datagen.WorkerGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

        PreparedStatement insertWorker = connection.prepareStatement(
                "INSERT INTO worker(cost, reliability, responseTime) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        PreparedStatement insertWorkerSuccess = connection.prepareStatement(
                "INSERT INTO workersuccess(workerid, expno, success) VALUES (?,?,?)");

        int count = 0;
        for (int i = 0; i < workerNumber; i++) {
            CrowdWorker worker = workerGen.generate(random);

            insertWorker.setDouble(1, worker.cost());
            insertWorker.setDouble(2, worker.reliability());
            insertWorker.setLong(3, worker.responseTime());
            insertWorker.executeUpdate();

            ResultSet rs = insertWorker.getGeneratedKeys();
            if (rs != null && rs.next()) {
                int workerId = rs.getInt(1);
                for (int j = 0; j < executeTimes; j++) {
                    insertWorkerSuccess.setInt(1, workerId);
                    insertWorkerSuccess.setInt(2, j);
                    insertWorkerSuccess.setBoolean(3, worker.reliability() > random.nextDouble());
                    insertWorkerSuccess.addBatch();
                }
                if (insertWorkerSuccess.executeUpdate() == executeTimes) {
                    count++;
                }
            }
        }
        return count == workerNumber;
    }
}
