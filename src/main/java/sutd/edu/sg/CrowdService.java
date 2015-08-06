/**
 * CrowdService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sutd.edu.sg;

import com.microsoft.schemas._2003._10.Serialization.Arrays.CSResultNum;
import com.microsoft.schemas._2003._10.Serialization.Arrays.CSWorker;

public interface CrowdService extends java.rmi.Remote {
	public String getData(Integer value)
			throws java.rmi.RemoteException;

	public sutd.edu.sg.CrowdOptimizationResult globalOptimize(
			String xml,
			Long globalTimeConstraint,
			Double globalCostConstraint,
			CSWorker[] workers,
			CSResultNum[] resultNums,
			Integer numOfGeneration) throws java.rmi.RemoteException;
}
