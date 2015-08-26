package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ExpStatus {
    private int id;
    private int expid;
    private int workerid;
    private String cs;

    public int id() {
        return id;
    }

    public ExpStatus id(int id) {
        this.id = id;
        return this;
    }

    public int expid() {
        return expid;
    }

    public ExpStatus expid(int expid) {
        this.expid = expid;
        return this;
    }

    public int workerid() {
        return workerid;
    }

    public ExpStatus workerid(int workerid) {
        this.workerid = workerid;
        return this;
    }

    public String cs() {
        return cs;
    }

    public ExpStatus cs(String cs) {
        this.cs = cs;
        return this;
    }
}
