package cn.edu.fudan.se.crowdservice.dao;


import cn.edu.fudan.se.crowdservice.bean.CrowdWorker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class SelectWorkerDAO extends DAO<List<CrowdWorker>> {

    @Override
    protected List<CrowdWorker> processData(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id,cost,reliability,responseTime,success FROM worker");
        ArrayList<CrowdWorker> all = new ArrayList<>();
        while (rs.next()) {
            all.add(new CrowdWorker()
                    .index(rs.getInt(1))
                    .cost(rs.getDouble(2))
                    .reliability(rs.getDouble(3))
                    .responseTime(rs.getInt(4))
                    .success(rs.getString(5))
                    .selected(false));
        }
        return all;
    }

}
