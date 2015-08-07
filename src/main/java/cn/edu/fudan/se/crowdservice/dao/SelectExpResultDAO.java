package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.bean.ExpResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class SelectExpResultDAO extends DAO<ExpResult> {
    @Override
    protected ExpResult processData(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT  * FROM (SELECT count(id) AS total FROM expinput) AS Total " +
                "JOIN (SELECT count(id) AS cs1success FROM expinput WHERE cs1success = 1 AND cs2success = 0) AS CS1 " +
                "JOIN (SELECT count(id) AS cs2success FROM expinput WHERE cs1success = 0 AND cs2success = 1) AS CS2 " +
                "JOIN (SELECT count(id) AS success FROM expinput WHERE cs1success = 1 AND cs2success = 1) AS SUCCESS");

        if (rs.next()) {
            return new ExpResult().total(rs.getInt(1)).cs1success(rs.getInt(2)).cs2success(rs.getInt(3)).success(rs.getInt(4));
        }
        return null;
    }
}
