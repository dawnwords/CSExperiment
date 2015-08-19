package cn.edu.fudan.se.crowdservice.bean;

import java.util.List;

/**
 * Created by Dawnwords on 2015/8/19.
 */
public class OptimizationResult {
    private int totalReliability;
    private List<WorkerSelectionResult> selectionResult;

    public OptimizationResult build(String input) {
        //TODO + input parser
        return this;
    }

    public int totalReliability() {
        return totalReliability;
    }

    public List<WorkerSelectionResult> selectionResult() {
        return selectionResult;
    }
}
