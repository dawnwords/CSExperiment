package cn.edu.fudan.se.crowdservice.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;

import sutd.edu.sg.CrowdWorker;
import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;

public class MixedNaiveAlgorithm extends NaiveAlgorithm {

	private double coe_rep = 1.0; //co-efficiency of reputation
	private double coe_res = 1.0; //co-efficiency of response time
	private double coe_cost = 1.0;//co-efficiency of cost
	
	public MixedNaiveAlgorithm(double coe_rep,double coe_res,double coe_cost){
		this.coe_cost = coe_cost;
		this.coe_rep = coe_rep;
		this.coe_res = coe_res;
	}
	
	@Override
	public List<CrowdWorker> rank(AlgorithmParameter parameter) {
		// TODO Auto-generated method stub

		List<CSResultNum> retNums = parameter.resultNums();
		List<CSWorker> csw = parameter.workers();
		List<CrowdWorker> retCrowdWorker = new ArrayList<CrowdWorker>();

		for(int i = 0; i < retNums.size() ; i++){
			CSWorker csWorker = csw.get(i);
			CrowdWorker[] cw = csWorker.getValue();
			Arrays.sort(cw, new Comparator<CrowdWorker>(){ 
	            public int compare(CrowdWorker arg0, CrowdWorker arg1) { 
	            	Double d1 = arg0.getReliability()*coe_rep - arg0.getResponseTime()*coe_res - arg0.getCost()*coe_cost;
	            	Double d2 = arg1.getReliability()*coe_rep - arg1.getResponseTime()*coe_res - arg1.getCost()*coe_cost;
	            	return -d1.compareTo(d2);
	            } 
	        });
			for(int j = 0; j < retNums.get(i).getValue() ; j++){
				retCrowdWorker.add(cw[j]);
			}
		}
		return retCrowdWorker;
	}

}
