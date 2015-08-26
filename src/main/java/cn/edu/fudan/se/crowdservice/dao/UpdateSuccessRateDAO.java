package cn.edu.fudan.se.crowdservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Dawnwords on 2015/8/26.
 */
public class UpdateSuccessRateDAO extends DAO<Boolean> {

    private int expid;
    private double cs1successRate, cs2successRate, cs3successRate, successRate;

    public UpdateSuccessRateDAO expid(int expid) {
        this.expid = expid;
        return this;
    }

    public UpdateSuccessRateDAO successRate(double successRate) {
        this.successRate = successRate;
        return this;
    }

    public UpdateSuccessRateDAO cs1successRate(double cs1successRate) {
        this.cs1successRate = cs1successRate;
        return this;
    }

    public UpdateSuccessRateDAO cs2successRate(double cs2successRate) {
        this.cs2successRate = cs2successRate;
        return this;
    }

    public UpdateSuccessRateDAO cs3successRate(double cs3successRate) {
        this.cs3successRate = cs3successRate;
        return this;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "UPDATE expinput SET cs1successrate=?, cs2successrate=?, cs3successrate=?, successrate=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = 0;
        ps.setDouble(++i, cs1successRate);
        ps.setDouble(++i, cs2successRate);
        ps.setDouble(++i, cs3successRate);
        ps.setDouble(++i, successRate);
        ps.setInt(++i, expid);
        return ps.executeUpdate() == 1;
    }
}
