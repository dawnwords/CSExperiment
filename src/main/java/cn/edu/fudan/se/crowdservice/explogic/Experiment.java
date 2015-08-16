package cn.edu.fudan.se.crowdservice.explogic;

import cn.edu.fudan.se.crowdservice.bean.*;
import cn.edu.fudan.se.crowdservice.dao.GenerateWorkerGroupDAO;
import cn.edu.fudan.se.crowdservice.dao.InsertExpStatusDAO;
import cn.edu.fudan.se.crowdservice.dao.UpdateTimeCostResultNumDAO;
import cn.edu.fudan.se.crowdservice.util.Logger;
import com.microsoft.schemas._2003._10.serialization.arrays.CSResultNumList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSResultNumList.CSResultNum;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList;
import com.microsoft.schemas._2003._10.serialization.arrays.CSWorkerList.CSWorker;
import sutd.edu.sg.CrowdWorker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/8/6.
 */
public class Experiment {

    private ExperimentInput input;

    public Experiment(ExperimentInput input) {
        this.input = input;
    }

    public void preform() {
        CrowdWorkerGroups workerGroups = new GenerateWorkerGroupDAO()
                .cs1GroupNum(input.cs1GroupNum())
                .cs2GroupNum(input.cs2GroupNum())
                .random(input.random()).getResult();
        AlgorithmParameter cs1GP = new AlgorithmParameter()
                .compositeServiceXML(BPELXml.CS1_CS2)
                .workers(new CSWorkerList()
                        .add(new CSWorker().setKey(BPELXml.CS1_NAME).setValue(workerGroups.cs1GroupArray()))
                        .add(new CSWorker().setKey(BPELXml.CS2_NAME).setValue(workerGroups.cs2GroupArray())
                        ))
                .resultNums(new CSResultNumList()
                                .add(new CSResultNum().setKey(BPELXml.CS1_NAME).setValue(input.cs1ResultNum()))
                                .add(new CSResultNum().setKey(BPELXml.CS2_NAME).setValue(input.cs2ResultNum()))
                );
        AlgorithmParameter cs1WS = new AlgorithmParameter()
                .compositeServiceXML(BPELXml.CS1)
                .workers(new CSWorkerList().add(new CSWorker().setKey(BPELXml.CS1_NAME).setValue(workerGroups.cs1GroupArray())))
                .resultNums(new CSResultNumList().add(new CSResultNum().setKey(BPELXml.CS1_NAME).setValue(input.cs1ResultNum())));
        AlgorithmParameter cs2GP = new AlgorithmParameter()
                .compositeServiceXML(BPELXml.CS2)
                .workers(new CSWorkerList().add(new CSWorker().setKey(BPELXml.CS2_NAME).setValue(workerGroups.cs2GroupArray())))
                .resultNums(new CSResultNumList().add(new CSResultNum().setKey(BPELXml.CS2_NAME).setValue(input.cs2ResultNum())));

        try {
            input.random().logCount();
            executeCS("CS1", cs1GP, cs1WS);
            input.random().logCount();
            executeCS("CS2", cs2GP, cs2GP);
        } catch (Exception e) {
            Logger.info(e.getMessage());
        }
    }

    private void executeCS(String cs, AlgorithmParameter globalPlanningPara, AlgorithmParameter workerSelectionPara) {
        Logger.info("Executing %s", cs);
        globalPlanningPara.timeCost(input.timeCost());
        Logger.info("Global Optimization Input:" + globalPlanningPara);
        TimeCost planTC = input.algorithm().globalOptimize(globalPlanningPara);
        Logger.info("Finish Global Optimize:" + planTC);

        workerSelectionPara.timeCost(planTC);
        Logger.info("Worker Selection Input:" + workerSelectionPara);
        List<CrowdWorker> selectedWorkers = input.algorithm().workerSelection(workerSelectionPara);
        Logger.info("Finish Worker Selection:");
        for (CrowdWorker worker : selectedWorkers) {
            Logger.info("%s", worker);
        }

        List<ExpStatus> expStatus = new ArrayList<>();
        TimeCost realTC = new TimeCost();
        int realResultNum = 0;
        for (CrowdWorker worker : selectedWorkers) {
            boolean success = worker.getReliability() > input.reliability() &&
                    worker.getResponseTime() < planTC.time();
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

        new InsertExpStatusDAO().expStatus(expStatus).getResult();
        new UpdateTimeCostResultNumDAO()
                .expId(input.expId())
                .planTC(planTC)
                .realTC(realTC)
                .realResultNum(realResultNum)
                .cs(cs).getResult();
        Logger.info("Finish Executing: %s", realTC);
        input.timeCost().minus(realTC);
    }
}
