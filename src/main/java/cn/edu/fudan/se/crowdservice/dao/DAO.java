package cn.edu.fudan.se.crowdservice.dao;

import cn.edu.fudan.se.crowdservice.Parameter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public abstract class DAO<T> {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(Parameter.DB_URL, Parameter.DB_USER, Parameter.DB_PASS);
    }

    public T getResult() {
        Connection conn = null;
        T result = null;

        try {
            conn = connect();
            result = processData(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    protected abstract T processData(Connection connection) throws Exception;
}
