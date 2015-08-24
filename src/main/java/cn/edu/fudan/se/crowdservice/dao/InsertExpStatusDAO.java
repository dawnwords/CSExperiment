package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.ExpStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class InsertExpStatusDAO extends DAO<Boolean> {
    private List<ExpStatus> expStatus;

    public InsertExpStatusDAO expStatus(List<ExpStatus> expStatus) {
        this.expStatus = expStatus;
        return this;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "INSERT INTO expstatus(expid,workerid,cs,algorithm) VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (ExpStatus status : expStatus) {
            ps.setInt(1, status.expid());
            ps.setInt(2, status.workerid());
            ps.setString(3, status.cs());
            ps.setString(4, status.algorithm());
            ps.addBatch();
        }
        ps.executeBatch();
        return true;
    }
}
