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

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "UPDATE expinput SET " +
                cs + "deadline=?, " +
                cs + "cost=?, " +
                cs + "realtime=?, " +
                cs + "realcost=?, " +
                " WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, planTC.time());
        ps.setDouble(++i, planTC.cost());
        ps.setLong(++i, realTC.time());
        ps.setDouble(++i, realTC.cost());
        ps.setInt(++i, expId);
        return ps.executeUpdate() == 1;
    }
}
