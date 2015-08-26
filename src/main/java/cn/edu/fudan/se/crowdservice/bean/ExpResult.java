package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ExpResult {
    private int settingId;
    private String algorithm;
    private TimeCost timeCost;
    private int cs1ResultNum, cs2ResultNum, cs3ResultNum;
    private double cs1successRate, cs2successRate, cs3successRate, successRate;

    public ExpResult settingId(int settingId) {
        this.settingId = settingId;
        return this;
    }

    public int settingId() {
        return settingId;
    }

    public ExpResult algorithm(String algoritm) {
        this.algorithm = algoritm;
        return this;
    }

    public String algorithm() {
        return algorithm;
    }

    public ExpResult timeCost(TimeCost timeCost) {
        this.timeCost = timeCost;
        return this;
    }

    public ExpResult cs1ResultNum(int cs1ResultNum) {
        this.cs1ResultNum = cs1ResultNum;
        return this;
    }

    public ExpResult cs2ResultNum(int cs2ResultNum) {
        this.cs2ResultNum = cs2ResultNum;
        return this;
    }

    public ExpResult cs3ResultNum(int cs3ResultNum) {
        this.cs3ResultNum = cs3ResultNum;
        return this;
    }

    public ExpResult successRate(double successRate) {
        this.successRate = successRate;
        return this;
    }

    public double successRate() {
        return successRate;
    }

    public double cs1successRate() {
        return cs1successRate;
    }

    public ExpResult cs1successRate(double cs1successRate) {
        this.cs1successRate = cs1successRate;
        return this;
    }

    public double cs2successRate() {
        return cs2successRate;
    }

    public ExpResult cs2successRate(double cs2successRate) {
        this.cs2successRate = cs2successRate;
        return this;
    }

    public double cs3successRate() {
        return cs3successRate;
    }

    public ExpResult cs3successRate(double cs3successRate) {
        this.cs3successRate = cs3successRate;
        return this;
    }

    public String setting() {
        return String.format("[%d]%d/%.2f/%d,%d,%d", settingId, timeCost.time(), timeCost.cost(), cs1ResultNum, cs2ResultNum, cs3ResultNum);
    }
}
