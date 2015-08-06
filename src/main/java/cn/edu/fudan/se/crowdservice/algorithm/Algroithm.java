package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.GlobalOptimizeResult;
import sutd.edu.sg.CrowdWorker;

import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public interface Algroithm {
    GlobalOptimizeResult globalOptimize(AlgorithmParameter parameter);

    List<CrowdWorker> workerSelection(AlgorithmParameter parameter);
}
