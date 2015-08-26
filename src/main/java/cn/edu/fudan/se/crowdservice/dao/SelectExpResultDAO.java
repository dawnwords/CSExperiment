package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.ExpResult;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/25.
 */
public class SelectExpResultDAO extends DAO<List<ExpResult>> {
    @Override
    protected List<ExpResult> processData(Connection connection) throws Exception {
        List<ExpResult> results = new LinkedList<>();
        String sql = "SELECT settingid,cost,deadline,cs1resultNum,cs2resultNum,cs3resultNum,cs1successrate,cs2successrate,cs3successrate,successrate,algorithm FROM successrate";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int i = 0;
            results.add(new ExpResult()
                            .settingId(rs.getInt(++i))
                            .timeCost(new TimeCost()
                                    .cost(rs.getDouble(++i))
                                    .time(rs.getInt(++i)))
                            .cs1ResultNum(rs.getInt(++i))
                            .cs2ResultNum(rs.getInt(++i))
                            .cs3ResultNum(rs.getInt(++i))
                            .cs1successRate(rs.getDouble(++i))
                            .cs2successRate(rs.getDouble(++i))
                            .cs3successRate(rs.getDouble(++i))
                            .successRate(rs.getDouble(++i))
                            .algorithm(rs.getString(++i))
            );
        }
        return results;
    }
}
