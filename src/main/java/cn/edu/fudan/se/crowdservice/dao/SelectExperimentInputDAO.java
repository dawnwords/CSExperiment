package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.algorithm.Algorithm;
import cn.edu.fudan.se.crowdservice.bean.ExperimentInput;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/7.
 */
public class SelectExperimentInputDAO extends DAO<List<ExperimentInput>> {

    private int cs1GroupNum, cs2GroupNum, cs3GroupNum;
    private Algorithm algorithm;

    public SelectExperimentInputDAO cs1GroupNum(int cs1GropuNum) {
        this.cs1GroupNum = cs1GropuNum;
        return this;
    }

    public SelectExperimentInputDAO cs2GroupNum(int cs2GroupNum) {
        this.cs2GroupNum = cs2GroupNum;
        return this;
    }

    public SelectExperimentInputDAO cs3GroupNum(int cs3GroupNum) {
        this.cs3GroupNum = cs3GroupNum;
        return this;
    }

    public SelectExperimentInputDAO algorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    @Override
    protected List<ExperimentInput> processData(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id,cost,deadline,cs1resultNum,cs2resultNum,cs3resultNum FROM expinput");
        ArrayList<ExperimentInput> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new ExperimentInput()
                    .cs1GroupNum(cs1GroupNum)
                    .cs2GroupNum(cs2GroupNum)
                    .cs3GroupNum(cs3GroupNum)
                    .algorithm(algorithm)
                    .expId(rs.getInt(1))
                    .timeCost(new TimeCost()
                            .cost(rs.getDouble(2))
                            .time(rs.getLong(3)))
                    .cs1ResultNum(rs.getInt(4))
                    .cs2ResultNum(rs.getInt(5))
                    .cs3ResultNum(rs.getInt(6)));
        }
        return result;
    }
}
