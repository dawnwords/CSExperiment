package cn.edu.fudan.se.crowdservice.explogic;

import cn.edu.fudan.se.crowdservice.bean.*;
import cn.edu.fudan.se.crowdservice.dao.InsertExpStatusDAO;
import cn.edu.fudan.se.crowdservice.dao.UpdateTimeCostResultNumDAO;
import cn.edu.fudan.se.crowdservice.dao.SelectWorkerDAO;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;
import sutd.edu.sg.CrowdWorker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Experiment {

    private ExperimentInput input;

    public Experiment(ExperimentInput input) {
        this.input = input;
    }

    public void start() {
        CrowdWorkerGroups workerGroups = new SelectWorkerDAO(input.cs1ResultNum(), input.cs2ResultNum(), input.random()).getResult();
        AlgorithmParameter cs1GP = new AlgorithmParameter()
                .compositeServiceXML(BPELXml.CS1_CS2)
                .workers(Arrays.asList(
                        new CSWorker(BPELXml.CS1_NAME, workerGroups.cs1GroupArray()),
                        new CSWorker(BPELXml.CS2_NAME, workerGroups.cs2GroupArray())
                ))
                .resultNums(Arrays.asList(
                        new CSResultNum(BPELXml.CS1_NAME, input.cs1ResultNum()),
                        new CSResultNum(BPELXml.CS2_NAME, input.cs2ResultNum())
                ));
        AlgorithmParameter cs1WS = new AlgorithmParameter()
                .compositeServiceXML(BPELXml.CS1)
                .workers(Collections.singletonList(new CSWorker(BPELXml.CS1_NAME, workerGroups.cs1GroupArray())))
                .resultNums(Collections.singletonList(new CSResultNum(BPELXml.CS1_NAME, input.cs1ResultNum())));
        AlgorithmParameter cs2GP = new AlgorithmParameter()
                .compositeServiceXML(BPELXml.CS2)
                .workers(Collections.singletonList(new CSWorker(BPELXml.CS2_NAME, workerGroups.cs2GroupArray())))
                .resultNums(Collections.singletonList(new CSResultNum(BPELXml.CS2_NAME, input.cs2ResultNum())));

        executeCS("CS1", cs1GP, cs1WS);
        executeCS("CS2", cs2GP, cs2GP);
    }

    private void executeCS(String cs, AlgorithmParameter globalPlanningPara, AlgorithmParameter workerSelectionPara) {
        TimeCost planTC = input.algorithm().globalOptimize(globalPlanningPara.cost(input.cost()).deadline(input.time()));

        List<CrowdWorker> selectedWorkers = input.algorithm().workerSelection(workerSelectionPara.cost(planTC.cost()).deadline(planTC.time()));

        List<ExpStatus> expStatus = new ArrayList<>();
        TimeCost realTC = new TimeCost();
        int realResultNum = 0;
        for (CrowdWorker worker : selectedWorkers) {
            boolean success = worker.getReliability() > input.random().nextDouble();
            expStatus.add(new ExpStatus()
                    .expid(input.expId())
                    .workerid(worker.getIndex())
                    .cs(cs)
                    .selected(true)
                    .success(success));
            if (success) {
                realTC.aggregate(worker.getResponseTime(), worker.getCost());
                realResultNum++;
            }
        }

        new InsertExpStatusDAO(expStatus).getResult();
        new UpdateTimeCostResultNumDAO(input.expId(), planTC, realTC, realResultNum, cs).getResult();
        input.timeCost().minus(realTC);
    }
}
