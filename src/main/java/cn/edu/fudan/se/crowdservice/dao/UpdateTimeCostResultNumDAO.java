package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.TimeCost;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class UpdateTimeCostResultNumDAO extends DAO<Boolean> {
    private int expId;
    private TimeCost planTC, realTC;
    private String cs;
    private int realResultNum;

    public UpdateTimeCostResultNumDAO(int expId, TimeCost planTC, TimeCost realTC, int realResultNum, String cs) {
        this.expId = expId;
        this.planTC = planTC;
        this.realTC = realTC;
        this.realResultNum = realResultNum;
        this.cs = cs;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "UPDATE expinput SET ?=?, ?=?, ?=?, ?=?, ?=?, success=(? = ?) WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, cs + "deadline");
        ps.setLong(++i, planTC.time());
        ps.setString(++i, cs + "cost");
        ps.setDouble(++i, planTC.cost());
        ps.setString(++i, cs + "realtime");
        ps.setLong(++i, realTC.time());
        ps.setString(++i, cs + "realcost");
        ps.setDouble(++i, realTC.cost());
        ps.setString(++i, cs + "realResultNum");
        ps.setInt(++i, realResultNum);
        ps.setString(++i, cs + "resultNum");
        ps.setString(++i, cs + "realResultNum");
        ps.setInt(++i, expId);
        return ps.executeUpdate() == 1;
    }
}
