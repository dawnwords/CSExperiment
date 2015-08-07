package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.algorithm.Algorithm;
import cn.edu.fudan.se.crowdservice.bean.ExperimentInput;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/7.
 */
public class SelectExperimentInputDAO extends DAO<List<ExperimentInput>> {

    private int cs1GroupNum, cs2GroupNum;
    private Random random;
    private Algorithm algorithm;

    public SelectExperimentInputDAO cs1GroupNum(int cs1GropuNum) {
        this.cs1GroupNum = cs1GropuNum;
        return this;
    }

    public SelectExperimentInputDAO cs2GroupNum(int cs2GroupNum) {
        this.cs2GroupNum = cs2GroupNum;
        return this;
    }

    public SelectExperimentInputDAO random(Random random) {
        this.random = random;
        return this;
    }

    public SelectExperimentInputDAO algorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    @Override
    protected List<ExperimentInput> processData(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id,cost,deadline,cs1resultNum,cs2resultNum FROM expinput");
        ArrayList<ExperimentInput> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new ExperimentInput()
                    .cs1GroupNum(cs1GroupNum)
                    .cs2GroupNum(cs2GroupNum)
                    .algorithm(algorithm)
                    .random(random)
                    .expId(rs.getInt(1))
                    .timeCost(new TimeCost()
                            .cost(rs.getDouble(2))
                            .time(rs.getLong(3)))
                    .cs1ResultNum(rs.getInt(4))
                    .cs2ResultNum(rs.getInt(5)));

        }
        return result;
    }
}
