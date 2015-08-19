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
        ResultSet rs = statement.executeQuery("SELECT * FROM worker");
        ArrayList<CrowdWorker> all = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            double cost = rs.getDouble(2);
            double reliability = rs.getDouble(3);
            long responseTime = rs.getInt(4);
            all.add(new CrowdWorker().cost(cost).index(id).reliability(reliability).responseTime(responseTime).selected(false));
        }
        return all;
    }

}
