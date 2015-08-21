package cn.edu.fudan.se.crowdservice;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Parameter {

    private static Parameter instance = new Parameter();
    private final Properties properties;

    private Parameter() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Parameter instance() {
        return instance;
    }

    public String dbUrl() {
        String host = properties.getProperty("db_host");
        int port = Integer.parseInt(properties.getProperty("db_port", "3306"));
        String dbName = properties.getProperty("db_name");
        return String.format("jdbc:mysql://%s:%d/%s", host, port, dbName);
    }

    public String dbUser() {
        return properties.getProperty("db_user");
    }

    public String dbPass() {
        return properties.getProperty("db_pass");
    }

    public String cs1Name() {
        return properties.getProperty("cs1_name");
    }

    public String cs2Name() {
        return properties.getProperty("cs2_name");
    }

    public String cs3Name() {
        return properties.getProperty("cs3_name");
    }

    public String bpelPath(){
        return properties.getProperty("bpel_path");
    }

    public String thServicePath() {
        return properties.getProperty("th_service_path");
    }

    public String ioPath() {
        return properties.getProperty("io_path");
    }

}
