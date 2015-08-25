package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ExperimentInput {
    //TODO + settingId
    private int expId, settingId;
    private int cs1ResultNum, cs2ResultNum, cs3ResultNum;
    private int cs1GroupNum, cs2GroupNum, cs3GroupNum;
    private TimeCost timeCost;

    public int expId() {
        return expId;
    }

    public ExperimentInput expId(int expId) {
        this.expId = expId;
        return this;
    }

    public int settingId() {
        return settingId;
    }

    public ExperimentInput settingId(int settingId) {
        this.settingId = settingId;
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

    public int cs3ResultNum() {
        return cs3ResultNum;
    }

    public ExperimentInput cs3ResultNum(int cs3ResultNum) {
        this.cs3ResultNum = cs3ResultNum;
        return this;
    }

    public int cs3GroupNum() {
        return cs3GroupNum;
    }

    public ExperimentInput cs3GroupNum(int cs3GroupNum) {
        this.cs3GroupNum = cs3GroupNum;
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
                "expId=" + expId +
                ", cs1ResultNum=" + cs1ResultNum +
                ", cs2ResultNum=" + cs2ResultNum +
                ", cs3ResultNum=" + cs3ResultNum +
                ", cs1GroupNum=" + cs1GroupNum +
                ", cs2GroupNum=" + cs2GroupNum +
                ", cs3GroupNum=" + cs3GroupNum +
                ", timeCost=" + timeCost +
                '}';
    }
}
