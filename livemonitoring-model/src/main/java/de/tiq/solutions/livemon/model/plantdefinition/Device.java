package de.tiq.solutions.livemon.model.plantdefinition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
 

public class Device extends AbstractDevice {
	
	private Map<String,NodeValues> nodeSubjected=new HashMap<String,NodeValues>();
	private Set<String> colIdentifier=new HashSet<String>();
	public Device(String plantUUID,String standort, String plantTopologyCode,  long maxCapacity) {
		super();
		this.plantUUID=plantUUID;
		this.location=standort;
		this.plantTopologyCode=plantTopologyCode;
		this.plantMaxCapacity=maxCapacity;
	}

	@Override
	public void addNode(String deviceSerial,String customType, long myMaxCap, Set<String> suitableEntries) {
		nodeSubjected.put(deviceSerial, new NodeValues(customType,myMaxCap,suitableEntries));
		
	}

	public Map<String,NodeValues> getNodeSubjected() {
		return nodeSubjected;
	}

	public Set<String> getColIdentifier() {
		return colIdentifier;
	}

	public void setColIdentifier(Set<String> colIdentifier) {
		this.colIdentifier = colIdentifier;
	}

}

