package cn.edu.fudan.se.crowdservice.algorithm;

import cn.edu.fudan.se.crowdservice.bean.GlobalOptimizeResult;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;
import sutd.edu.sg.CrowdWorker;

import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public interface Algroithm {
    GlobalOptimizeResult globalOptimize(String compositeServiceXML, int deadline, int cost, List<CSWorker> workers, List<CSResultNum> resultNums);

    List<CrowdWorker> workerSelection(String compositeServiceXML, int deadline, int cost, List<CSWorker> workers, List<CSResultNum> resultNums);
}
