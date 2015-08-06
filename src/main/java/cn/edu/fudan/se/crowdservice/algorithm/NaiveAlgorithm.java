package cn.edu.fudan.se.crowdservice.algorithm;

import java.util.List;

import sutd.edu.sg.CrowdWorker;
import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;
import cn.edu.fudan.se.crowdservice.bean.GlobalOptimizeResult;

public abstract class NaiveAlgorithm implements Algorithm {
	private int time;
    private int cost;
	@Override
	public GlobalOptimizeResult globalOptimize(AlgorithmParameter parameter) {
		// TODO Auto-generated method stub
		List<CrowdWorker> cwList = rank(parameter);
		long time = cwList.get(0).getResponseTime();
		double cost = 0;
		for(CrowdWorker cw : cwList) {
			if(time < cw.getResponseTime()) time = cw.getResponseTime();
			cost += cw.getCost();
		}
		GlobalOptimizeResult retVal = new GlobalOptimizeResult();
		retVal.cost(cost);
		retVal.time(time);
		return retVal;
	}

	@Override
	public List<CrowdWorker> workerSelection(AlgorithmParameter parameter) {
		// TODO Auto-generated method stub
		return rank(parameter);
	}
	
	abstract public List<CrowdWorker> rank(AlgorithmParameter parameter);

}
