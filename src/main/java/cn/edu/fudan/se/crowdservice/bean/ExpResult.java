package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ExpResult {
    private int total;
    private int cs1success;
    private int cs2success;
    private int success;

    public int total() {
        return total;
    }

    public ExpResult total(int total) {
        this.total = total;
        return this;
    }

    public int cs1success() {
        return cs1success;
    }

    public ExpResult cs1success(int cs1success) {
        this.cs1success = cs1success;
        return this;
    }

    public int cs2success() {
        return cs2success;
    }

    public ExpResult cs2success(int cs2success) {
        this.cs2success = cs2success;
        return this;
    }

    public int success() {
        return success;
    }

    public ExpResult success(int success) {
        this.success = success;
        return this;
    }
}
