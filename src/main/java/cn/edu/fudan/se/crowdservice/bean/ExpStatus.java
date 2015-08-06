package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ExpStatus {
    private int id;
    private int expid;
    private int workerid;
    private String cs;
    private boolean selected;
    private boolean success;

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

    public boolean selected() {
        return selected;
    }

    public ExpStatus selected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public boolean success() {
        return success;
    }

    public ExpStatus success(boolean success) {
        this.success = success;
        return this;
    }
}
