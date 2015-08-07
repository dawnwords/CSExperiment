package cn.edu.fudan.se.crowdservice.dao;

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
    @Override
    protected List<ExperimentInput> processData(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id,cost,deadline,cs1realResultNum,cs2resultNum FROM expinput");
        ArrayList<ExperimentInput> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new ExperimentInput()
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
