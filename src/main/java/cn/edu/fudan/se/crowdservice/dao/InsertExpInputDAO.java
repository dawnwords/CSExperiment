package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.ExperimentInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/8/24.
 */
public class InsertExpInputDAO extends DAO<ExperimentInput> {

    private ExperimentInput input;
    private int expTimes;

    public InsertExpInputDAO input(ExperimentInput input) {
        this.input = input;
        return this;
    }

    public InsertExpInputDAO expTimes(int expTimes) {
        this.expTimes = expTimes;
        return this;
    }

    @Override
    protected ExperimentInput processData(Connection connection) throws Exception {
        String sql = "INSERT INTO expinput(exptimes, cost, deadline, cs1resultNum, cs2resultNum, cs3resultNum) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, expTimes);
        ps.setDouble(2, input.timeCost().cost());
        ps.setLong(3, input.timeCost().time());
        ps.setInt(4, input.cs1ResultNum());
        ps.setInt(5, input.cs2ResultNum());
        ps.setInt(6, input.cs3ResultNum());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs == null || !rs.next()) {
            throw new RuntimeException("insert expinput fail");
        }
        return input.expId(rs.getInt(1));
    }
}
