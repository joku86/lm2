package de.tiq.solutions.livemon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncomingData {

	@JsonProperty("plantTypeCode")
	private String typeCode;

	@JsonProperty("plantUUID")
	private String plantUUID;

	@JsonProperty("plantName")
	private String plantName;

	@JsonProperty("deviceType")
	private String deviceType;

	@JsonProperty("manufactType")
	private String manufactType;

	@JsonProperty("serialNo")
	private String serial;

	@JsonProperty("measureLevel")
	private String measureLevel;

	@JsonProperty("measureID")
	private String measureID;

	@JsonProperty("measureValue")
	private String measureValue;

	@JsonProperty("measureTime")
	private Date timestamp;

	public IncomingData() {
	}

	public IncomingData(String plantTypeCode, String plantUUID, String plantName, String deviceType, String manufactType, String serial,
			String measureLevel, String measureID,
			String measureValue, Date timestamp) {
		this.typeCode = plantTypeCode;
		this.plantUUID = plantUUID;
		this.plantName = plantName;
		this.deviceType = deviceType;
		this.manufactType = manufactType;
		this.serial = serial;
		this.measureLevel = measureLevel;
		this.measureID = measureID;
		this.measureValue = measureValue;
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
		result = prime * result + ((manufactType == null) ? 0 : manufactType.hashCode());
		result = prime * result + ((measureID == null) ? 0 : measureID.hashCode());
		result = prime * result + ((measureLevel == null) ? 0 : measureLevel.hashCode());
		result = prime * result + ((measureValue == null) ? 0 : measureValue.hashCode());

		result = prime * result + ((plantName == null) ? 0 : plantName.hashCode());
		result = prime * result + ((plantUUID == null) ? 0 : plantUUID.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncomingData other = (IncomingData) obj;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (manufactType == null) {
			if (other.manufactType != null)
				return false;
		} else if (!manufactType.equals(other.manufactType))
			return false;
		if (measureID == null) {
			if (other.measureID != null)
				return false;
		} else if (!measureID.equals(other.measureID))
			return false;
		if (measureLevel == null) {
			if (other.measureLevel != null)
				return false;
		} else if (!measureLevel.equals(other.measureLevel))
			return false;
		if (measureValue == null) {
			if (other.measureValue != null)
				return false;
		} else if (!measureValue.equals(other.measureValue))
			return false;

		if (plantName == null) {
			if (other.plantName != null)
				return false;
		} else if (!plantName.equals(other.plantName))
			return false;
		if (plantUUID == null) {
			if (other.plantUUID != null)
				return false;
		} else if (!plantUUID.equals(other.plantUUID))
			return false;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		return true;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public String getMeasureID() {
		return measureID;
	}

	public void setMeasureID(String measureID) {
		this.measureID = measureID;
	}

	public String getMeasureValue() {
		return measureValue;
	}

	public void setMeasureValue(String measureValue) {
		this.measureValue = measureValue;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "IncomingData [ typeCode=" + typeCode + ", plantUUID=" + plantUUID + ", plantName=" + plantName + ", deviceType=" + deviceType
				+ ", manufactType="
				+ manufactType + ", serial=" + serial + ", measureLevel=" + measureLevel + ", measureID=" + measureID + ", measureValue="
				+ measureValue + ", timestamp=" + timestamp + "]";
	}

}
