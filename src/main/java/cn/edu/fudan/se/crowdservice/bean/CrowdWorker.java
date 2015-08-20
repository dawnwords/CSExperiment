package cn.edu.fudan.se.crowdservice.bean;

import cn.edu.fudan.se.crowdservice.util.Parser;

import java.lang.reflect.Field;

/**
 * Created by Dawnwords on 2015/8/19.
 */
public class CrowdWorker {
    private boolean selected;
    private long responseTime;
    private double reliability;
    private int index;
    private double cost;

    public static CrowdWorker toBean(String s) {
        CrowdWorker result = new CrowdWorker();
        if (!s.matches("CrowdWorker\\{[^\\}]+\\}")) {
            throw new RuntimeException("Crowd Worker Format error:" + s);
        }
        String[] tokens = s.substring(12, s.length() - 1).split(", ");
        if (tokens.length != 5) {
            throw new RuntimeException("Crowd Worker Parameter length error:" + tokens.length);
        }
        for (String token : tokens) {
            String[] field = token.split("=");
            if (field.length != 2) {
                throw new RuntimeException("Crowd Worker Field error:" + token);
            }
            FieldEnum.valueOf(field[0]).set(result, field[1]);
        }
        return result;
    }

    public boolean selected() {
        return selected;
    }

    public CrowdWorker selected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public long responseTime() {
        return responseTime;
    }

    public CrowdWorker responseTime(long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public double reliability() {
        return reliability;
    }

    public CrowdWorker reliability(double reliability) {
        this.reliability = reliability;
        return this;
    }

    public int index() {
        return index;
    }

    public CrowdWorker index(int index) {
        this.index = index;
        return this;
    }

    public double cost() {
        return cost;
    }

    public CrowdWorker cost(double cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public String toString() {
        return "CrowdWorker{" +
                "selected=" + selected +
                ", responseTime=" + responseTime +
                ", reliability=" + reliability +
                ", index=" + index +
                ", cost=" + cost +
                '}';
    }

    enum FieldEnum {
        selected(Parser.toBoolean),
        responseTime(Parser.toLong),
        reliability(Parser.toDouble),
        index(Parser.toInt),
        cost(Parser.toDouble);

        private Parser parser;

        FieldEnum(Parser parser) {
            this.parser = parser;
        }

        void set(CrowdWorker worker, String value) {
            try {
                Field field = CrowdWorker.class.getDeclaredField(this.name());
                field.setAccessible(true);
                field.set(worker, parser.parse(value));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
