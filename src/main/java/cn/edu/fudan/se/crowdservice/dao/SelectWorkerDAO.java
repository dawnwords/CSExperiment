package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.CrowdWorkerGroups;
import sutd.edu.sg.CrowdWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class SelectWorkerDAO extends DAO<CrowdWorkerGroups> {

    private int cs1GroupNum, cs2GroupNum;
    private Random random;

    public SelectWorkerDAO cs1GroupNum(int cs1GroupNum) {
        this.cs1GroupNum = cs1GroupNum;
        return this;
    }

    public SelectWorkerDAO cs2GroupNum(int cs2GroupNum) {
        this.cs2GroupNum = cs2GroupNum;
        return this;
    }

    public SelectWorkerDAO random(Random random) {
        this.random = random;
        return this;
    }

    @Override
    protected CrowdWorkerGroups processData(Connection connection) throws Exception {
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
        return randomSelect(all);
    }

    private CrowdWorkerGroups randomSelect(ArrayList<CrowdWorker> all) {
        int selectedNumber = cs1GroupNum + cs2GroupNum;
        int r, i;
        for (i = selectedNumber; i < all.size(); i++) {
            r = (int) (random.nextDouble() * (i + 1));
            if (r < selectedNumber) {
                Collections.swap(all, i, r);
            }
        }

        CrowdWorkerGroups result = new CrowdWorkerGroups();
        for (i = 0; i < cs1GroupNum; i++) {
            result.addCS1Worker(all.get(i));
        }
        for (; i < cs2GroupNum; i++) {
            result.addCS2Worker(all.get(i));
        }
        return result;
    }

}
