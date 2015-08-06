package cn.edu.fudan.se.crowdservice.dao;

import sutd.edu.sg.CrowdWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class SelectWorkerDAO extends DAO<List<CrowdWorker>> {

    @Override
    protected List<CrowdWorker> processData(Connection connection) throws Exception {
        String sql = "SELECT * FROM worker";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<CrowdWorker> all = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            double cost = rs.getDouble(2);
            double reliability = rs.getDouble(3);
            long responseTime = rs.getInt(4);
            all.add(new CrowdWorker(cost, id, reliability, responseTime, false));
        }
        return all;
    }

}
