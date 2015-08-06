package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.ExpStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class InsertExpStatusDAO extends DAO<Boolean> {
    private final List<ExpStatus> expStatus;

    public InsertExpStatusDAO(List<ExpStatus> expStatus) {
        this.expStatus = expStatus;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "INSERT INTO expstatus(expid,workerid,cs,selected,success) VALUES (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        int updateCount = 0;
        for (ExpStatus status : expStatus) {
            ps.setInt(1, status.expid());
            ps.setInt(2, status.workerid());
            ps.setString(3, status.cs());
            ps.setBoolean(4, status.selected());
            ps.setBoolean(5, status.success());
            if (ps.executeUpdate() == 1) {
                updateCount++;
            }
        }
        return updateCount == expStatus.size();
    }
}
