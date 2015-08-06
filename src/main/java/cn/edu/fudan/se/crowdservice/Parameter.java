package cn.edu.fudan.se.crowdservice;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Parameter {
    private static final String DB_HOST = "10.131.252.156";
    private static final int DB_PORT = 3306;
    private static final String DB_NAME = "csexperiment";
    public static final String DB_URL = String.format("jdbc:mysql://%s:%d/%s", DB_HOST, DB_PORT, DB_NAME);
    public static final String DB_USER = "root";
    public static final String DB_PASS = "cloudfdse";
}
