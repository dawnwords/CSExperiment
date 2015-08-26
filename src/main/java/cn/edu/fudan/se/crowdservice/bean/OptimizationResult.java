package cn.edu.fudan.se.crowdservice.bean;

import cn.edu.fudan.se.crowdservice.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dawnwords on 2015/8/19.
 */
public class OptimizationResult {
    private double totalReliability;
    private Map<String, WorkerSelectionResult> selectionResult;

    public OptimizationResult build(Map<Integer, String> workerSuccess, BufferedReader input) {
        try {
            String line = input.readLine();
            if (line == null) {
                throw new RuntimeException("OptimizationResult Format error: null");
            }

            this.totalReliability = (Double) Parser.toDouble.parse(getValue(line, "Total Reliability={double}"));
            this.selectionResult = new HashMap<>();

            boolean selectionResultStart = false;
            boolean allocTimeCostStart = false;
            WorkerSelectionResult result = new WorkerSelectionResult();
            while ((line = input.readLine()) != null) {
                if (line.matches("(\\s)*")) {
                    continue;
                }
                if (line.contains("Allocated Time and Cost")) {
                    allocTimeCostStart = true;
                    continue;
                }
                if (line.startsWith("=")) {
                    if (allocTimeCostStart) {
                        allocTimeCostStart = false;
                        continue;
                    }
                    if (selectionResultStart) {
                        selectionResultStart = false;
                        this.selectionResult.put(result.service(), result);
                        result = new WorkerSelectionResult();
                    } else {
                        selectionResultStart = true;
                    }
                    continue;
                }
                if (selectionResultStart) {
                    if (line.startsWith("key")) {
                        result.service(line.substring(4, line.length()));
                    } else if (line.startsWith("CrowdWorker")) {
                        result.addWorker(CrowdWorker.toBean(workerSuccess, line));
                    } else if (line.startsWith("Total Cost")) {
                        result.executeCost((Double) Parser.toDouble.parse(getValue(line, "Total Cost={double}")));
                    } else if (line.startsWith("Max Response Time")) {
                        result.executeTime((Long) Parser.toLong.parse(getValue(line, "Max Response Time={long}")));
                    }
                } else if (allocTimeCostStart) {
                    String key = line.substring(4, line.indexOf('{') - 1);
                    String time = line.substring(line.indexOf('{') + 6, line.indexOf(','));
                    String cost = line.substring(line.indexOf(',') + 7, line.indexOf('}'));
                    WorkerSelectionResult wsr = selectionResult.get(key);
                    if (wsr == null) {
                        throw new RuntimeException("key not exists:" + key);
                    }
                    wsr.planTime((Long) Parser.toLong.parse(time)).planCost((Double) Parser.toDouble.parse(cost));
                }
            }
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    private String getValue(String keyValue, String expect) {
        String[] tokens = keyValue.split("=");
        if (tokens.length != 2) {
            throw new RuntimeException("OptimizationResult Format error: Expect '" + expect + "', but " + keyValue);
        }
        return tokens[1];
    }

    public double totalReliability() {
        return totalReliability;
    }

    public Map<String, WorkerSelectionResult> selectionResult() {
        return selectionResult;
    }
}
