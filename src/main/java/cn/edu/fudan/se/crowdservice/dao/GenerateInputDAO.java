package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.datagen.CostGen;
import cn.edu.fudan.se.crowdservice.datagen.DeadlineGen;
import cn.edu.fudan.se.crowdservice.datagen.Random;
import cn.edu.fudan.se.crowdservice.datagen.ResultNumGen;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class GenerateInputDAO extends DAO<Boolean> {
    private Random random;
    private int experimentTimes;

    public GenerateInputDAO random(Random random) {
        this.random = random;
        return this;
    }

    public GenerateInputDAO experimentTimes(int experimentTimes) {
        this.experimentTimes = experimentTimes;
        return this;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "INSERT INTO expinput(cost, deadline, cs1resultNum, cs2resultNum,cs3resultNum) VALUES(?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        CostGen cost = new CostGen();
        DeadlineGen deadline = new DeadlineGen();
        ResultNumGen resultNum = new ResultNumGen();

        int count = 0;
        for (int i = 0; i < experimentTimes; i++) {
            ps.setDouble(1, cost.generate(random));
            ps.setLong(2, deadline.generate(random));
            ps.setInt(3, resultNum.generate(random));
            ps.setInt(4, resultNum.generate(random));
            ps.setInt(5, resultNum.generate(random));
            if (ps.executeUpdate() == 1) {
                count++;
            }
        }
        return count == experimentTimes;
    }
}
