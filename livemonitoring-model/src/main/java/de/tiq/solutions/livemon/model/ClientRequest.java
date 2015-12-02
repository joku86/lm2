package de.tiq.solutions.livemon.model;


public class ClientRequest {
	private String table;
	private String plantUUID;
	private String plantName;
	private String deviceType;
	private String manufactType;
	private String serial;
	private String measureLevel;
	private long timestamp;
	public ClientRequest(String table, String plantUUID, String plantName, String deviceType, String manufactType, String serial, String measureLevel, long timestamp) {
		this.table = table;
		this.plantUUID = plantUUID;
		this.plantName = plantName;
		this.deviceType = deviceType;
		this.manufactType = manufactType;
		this.serial = serial;
		this.measureLevel = measureLevel;
		this.timestamp = timestamp;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getPlantUUID() {
		return plantUUID;
	}
	public void setPlantUUID(String plantUUID) {
		this.plantUUID = plantUUID;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getManufactType() {
		return manufactType;
	}
	public void setManufactType(String manufactType) {
		this.manufactType = manufactType;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getMeasureLevel() {
		return measureLevel;
	}
	public void setMeasureLevel(String measureLevel) {
		this.measureLevel = measureLevel;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
