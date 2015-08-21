package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class ExpResult {
    private int total;
    private int cs1success;
    private int cs2success;
    private int success;

    public ExpResult total(int total) {
        this.total = total;
        return this;
    }

    public ExpResult cs1success(int cs1success) {
        this.cs1success = cs1success;
        return this;
    }

    public ExpResult cs2success(int cs2success) {
        this.cs2success = cs2success;
        return this;
    }

    public ExpResult success(int success) {
        this.success = success;
        return this;
    }

    @Override
    public String toString() {
        return "ExpResult{" +
                "total=" + total +
                ", cs1success=" + cs1success +
                ", cs2success=" + cs2success +
                ", success=" + success +
                '}';
    }
}
