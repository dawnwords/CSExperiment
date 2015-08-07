package cn.edu.fudan.se.crowdservice.bean;

import cn.edu.fudan.se.crowdservice.algorithm.Algorithm;

import java.util.Random;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ExperimentInput {
    private Algorithm algorithm;
    private int expId;
    private int cs1ResultNum, cs2ResultNum;
    private int cs1GroupNum, cs2GroupNum;
    private Random random;
    private TimeCost timeCost;

    public int expId() {
        return expId;
    }

    public ExperimentInput expId(int expId) {
        this.expId = expId;
        return this;
    }

    public Algorithm algorithm() {
        return algorithm;
    }

    public ExperimentInput algorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public int cs1GroupNum() {
        return cs1GroupNum;
    }

    public ExperimentInput cs1GroupNum(int cs1GroupNum) {
        this.cs1GroupNum = cs1GroupNum;
        return this;
    }

    public int cs2GroupNum() {
        return cs2GroupNum;
    }

    public ExperimentInput cs2GroupNum(int cs2GroupNum) {
        this.cs2GroupNum = cs2GroupNum;
        return this;
    }

    public int cs1ResultNum() {
        return cs1ResultNum;
    }

    public ExperimentInput cs1ResultNum(int cs1Number) {
        this.cs1ResultNum = cs1Number;
        return this;
    }

    public int cs2ResultNum() {
        return cs2ResultNum;
    }

    public ExperimentInput cs2ResultNum(int cs2Number) {
        this.cs2ResultNum = cs2Number;
        return this;
    }

    public Random random() {
        return random;
    }

    public ExperimentInput random(Random random) {
        this.random = random;
        return this;
    }

    public TimeCost timeCost() {
        return timeCost;
    }

    public ExperimentInput timeCost(TimeCost timeCost) {
        this.timeCost = timeCost;
        return this;
    }

    @Override
    public String toString() {
        return "ExperimentInput{" +
                "algorithm=" + algorithm.getClass().getSimpleName() +
                ", expId=" + expId +
                ", cs1ResultNum=" + cs1ResultNum +
                ", cs2ResultNum=" + cs2ResultNum +
                ", cs1GroupNum=" + cs1GroupNum +
                ", cs2GroupNum=" + cs2GroupNum +
                ", timeCost=" + timeCost +
                '}';
    }
}
