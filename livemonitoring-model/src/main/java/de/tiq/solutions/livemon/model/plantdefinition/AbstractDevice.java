package de.tiq.solutions.livemon.model.plantdefinition;

import java.util.Set;


public abstract class AbstractDevice {
	String plantUUID;
	String typ;
	String location;
	String plantTopologyCode;
	long plantMaxCapacity;
	Set<String> plantDevices;
 
	public abstract void addNode(String deviceSerial,String customType,long myMaxCap, Set<String> suitableEntries);
	public String getPlantUUID() {
		return plantUUID;
	}
	public void setPlantUUID(String plantUUID) {
		this.plantUUID = plantUUID;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPlantTopologyCode() {
		return plantTopologyCode;
	}
	public void setPlantTopologyCode(String plantTopologyCode) {
		this.plantTopologyCode = plantTopologyCode;
	}
	public long getPlantMaxCapacity() {
		return plantMaxCapacity;
	}
	public void setPlantMaxCapacity(long plantMaxCapacity) {
		this.plantMaxCapacity = plantMaxCapacity;
	}
	public Set<String> getPlantDevices() {
		return plantDevices;
	}
	public void setPlantDevices(Set<String> plantDevices) {
		this.plantDevices = plantDevices;
	}
 
}
