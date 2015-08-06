package cn.edu.fudan.se.crowdservice.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;

import sutd.edu.sg.CrowdWorker;
import cn.edu.fudan.se.crowdservice.bean.AlgorithmParameter;

public class OnlyResponseTimeNaiveAlgorithm extends NaiveAlgorithm {

	@Override
	public List<CrowdWorker> rank(AlgorithmParameter parameter) { 
		List<CSResultNum> retNums = parameter.resultNums();
		List<CSWorker> csw = parameter.workers();
		List<CrowdWorker> retCrowdWorker = new ArrayList<CrowdWorker>();

		for(int i = 0; i < retNums.size() ; i++){
			CSWorker csWorker = csw.get(i);
			CrowdWorker[] cw = csWorker.getValue();
			Arrays.sort(cw, new Comparator<CrowdWorker>(){ 
	            public int compare(CrowdWorker arg0, CrowdWorker arg1) { 
	                return arg0.getResponseTime().compareTo(arg1.getResponseTime()); 
	            } 
	        });
			
			for(int j = 0; j < retNums.get(i).getValue() ; j++){
				retCrowdWorker.add(cw[j]);
			}
		}
		return retCrowdWorker;
	}

}
