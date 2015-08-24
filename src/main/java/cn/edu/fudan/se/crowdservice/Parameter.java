package cn.edu.fudan.se.crowdservice;

import cn.edu.fudan.se.crowdservice.algorithm.Algorithm;
import cn.edu.fudan.se.crowdservice.bean.TimeCost;
import cn.edu.fudan.se.crowdservice.datagen.Random;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Parameter {

    private static Parameter instance = new Parameter();
    private final Properties properties;
    private final Random workerRandom;

    private Parameter() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/settings.properties"));
            File ioDir = new File(ioPath());
            ioDir.deleteOnExit();
            ioDir.mkdirs();
            workerRandom = new Random(Long.parseLong(properties.getProperty("worker_seed")));
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

    public String bpelPath() {
        return properties.getProperty("bpel_path");
    }

    public String thServicePath() {
        return properties.getProperty("th_service_path");
    }

    public String ioPath() {
        return properties.getProperty("io_path");
    }

    public int times(Algorithm.AlgorithmFactory algorithm) {
        return Integer.parseInt(properties.getProperty(algorithm.name() + "_times"));
    }

    public int workerSize() {
        return Integer.parseInt(properties.getProperty("worker_size"));
    }

    public Random workerRandom() {
        return workerRandom;
    }

    public double csAvg() {
        return Double.parseDouble(properties.getProperty("worker_cs_avg"));
    }

    public double csSd() {
        return Double.parseDouble(properties.getProperty("worker_cs_sd"));
    }

    public double rpAvg() {
        return Double.parseDouble(properties.getProperty("worker_rp_avg"));
    }

    public double rpSd() {
        return Double.parseDouble(properties.getProperty("worker_rp_sd"));
    }

    public long rtLow() {
        return Long.parseLong(properties.getProperty("worker_rt_low"));
    }

    public long rtRange() {
        return Long.parseLong(properties.getProperty("worker_rt_range"));
    }

    public boolean clearWorkers() {
        return Boolean.valueOf(properties.getProperty("clear_workers"));
    }

    public boolean clearExps() {
        return Boolean.valueOf(properties.getProperty("clear_exps"));
    }

    public int expTimes() {
        return Integer.parseInt(properties.getProperty("exp_times"));
    }

    public int executeTimes() {
        return Integer.parseInt(properties.getProperty("execute_times"));
    }

    public String resultNums() {
        return properties.getProperty("result_num");
    }

    public String workerGroupSize() {
        return properties.getProperty("worker_group");
    }

    public TimeCost totalTimeCost() {
        return new TimeCost()
                .cost(Double.parseDouble(properties.getProperty("total_cost")))
                .time(Long.parseLong(properties.getProperty("deadline")));
    }

    public Random workerGroupRandom() {
        return new Random(Long.parseLong(properties.getProperty("worker_group_seed")));
    }
}
