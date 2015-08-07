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

    public UpdateTimeCostResultNumDAO expId(int expId) {
        this.expId = expId;
        return this;
    }

    public UpdateTimeCostResultNumDAO planTC(TimeCost planTC) {
        this.planTC = planTC;
        return this;
    }

    public UpdateTimeCostResultNumDAO realTC(TimeCost realTC) {
        this.realTC = realTC;
        return this;
    }

    public UpdateTimeCostResultNumDAO cs(String cs) {
        this.cs = cs.toLowerCase();
        return this;
    }

    public UpdateTimeCostResultNumDAO realResultNum(int realResultNum) {
        this.realResultNum = realResultNum;
        return this;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "UPDATE expinput SET " +
                cs + "deadline=?, " +
                cs + "cost=?, " +
                cs + "realtime=?, " +
                cs + "realcost=?, " +
                cs + "realResultNum=?, " +
                cs + "success=(" +
                cs + "resultNum = ?) WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, planTC.time());
        ps.setDouble(++i, planTC.cost());
        ps.setLong(++i, realTC.time());
        ps.setDouble(++i, realTC.cost());
        ps.setInt(++i, realResultNum);
        ps.setInt(++i, realResultNum);
        ps.setInt(++i, expId);
        return ps.executeUpdate() == 1;
    }
}
