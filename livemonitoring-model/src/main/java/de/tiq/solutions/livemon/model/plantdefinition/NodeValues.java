package de.tiq.solutions.livemon.model.plantdefinition;

import java.util.Set;

 

public class NodeValues{
	private long myMaxCapacity;
	private String customType;	 
	private Set<String> ownValues;
 
	public NodeValues(String customType,long myMaxCap, Set<String> suitableEntries){
		myMaxCapacity=myMaxCap;
		this.customType=customType;
		ownValues=suitableEntries;
	}
	public long getMyMaxCapacity() {
		return myMaxCapacity;
	}
	public void setMyMaxCapacity(long myMaxCapacity) {
		this.myMaxCapacity = myMaxCapacity;
	}
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String customType) {
		this.customType = customType;
	}
	public Set<String> getOwnValues() {
		return ownValues;
	}
	public void setOwnValues(Set<String> ownValues) {
		this.ownValues = ownValues;
	}
	 
}