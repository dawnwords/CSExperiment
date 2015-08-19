package cn.edu.fudan.se.crowdservice.bean;

/**
 * Created by Dawnwords on 2015/8/19.
 */
public class ServiceResultNum {
    private String service;
    private int resultNum;

    public String service() {
        return service;
    }

    public ServiceResultNum service(String service) {
        this.service = service;
        return this;
    }

    public int resultNum() {
        return resultNum;
    }

    public ServiceResultNum resultNum(int resultNum) {
        this.resultNum = resultNum;
        return this;
    }
}
