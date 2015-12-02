package de.tiq.solutions.livemon.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.tiq.solutions.livemon.definitions.TypeDefinitions;

public class LMData {

	// Possible to collect the Childs to get information of the amount of childs
	@JsonIgnore
	private Set<LMData> childs = new HashSet<LMData>();

	// zu einem Parent existieren x Childs hier kann childabhängige berechnungen
	// durchgeführt werden.
	public boolean updateFoundetParent(LMData outgoingChild) {
		childs.add(outgoingChild);
		if (allChildsSameTime()) {
			setParentValues(outgoingChild);
			return true;
		} else
			return false;
	}

	public void fillParentNode(LMData outgoingChild) {
		childs.add(outgoingChild);
		setParentValues(outgoingChild);
	}

	// for product code implementation of complete state
	public boolean allChildsSameTime() {

		@SuppressWarnings("unused")
		boolean equal = false;
		// TODO hier die Implementierung einer konkreten Strategie für
		// Vollstaendigkeitsueberpruefung
		// Knoten werden nie gleichen Zeitstempel haben siehe Importtabelle
		// long timestamp = 0;
		// int i = 0;
		// for (LMData child : childs) {
		// if (i == 0)
		// timestamp = child.getTimestamp();
		// else if (timestamp != child.getTimestamp())
		// equal = false;
		// i++;
		// }
		// return equal;
		return true;
	}

	private void setParentValues(LMData outgoingChild) {
		double currentPower = 0;
		double relativePower = 0;
		double day3Earning = 0;
		double day2Earning = 0;
		double day1Earning = 0;
		double currentEarning = 0;
		double currentWind = 0;
		double currentTemp = 0;

		for (LMData child : childs) {
			currentPower += child.getPower().getCurrent();
			relativePower = relativePower + child.getPower().getRelative();
			day3Earning += child.getEarning().getDay3ago();
			day2Earning += child.getEarning().getDay2ago();
			day1Earning += child.getEarning().getDay1ago();
			currentEarning += child.getEarning().getCurrentValue();
			currentWind += child.getEnviroments().getWindOrIrradiation();
			currentTemp += child.getEnviroments().getTemperature();

		}
		relativePower = (currentPower * 100000) / TypeDefinitions.getDefined().get(outgoingChild.getPlantData().getPlantUUID()).getPlantMaxCapacity();
		this.getPower().setCurrent(currentPower);
		this.getPower().setRelative(relativePower);
		this.getEarning().setDay3ago(day3Earning);
		this.getEarning().setDay2ago(day2Earning);
		this.getEarning().setDay1ago(day1Earning);
		this.getEarning().setCurrentValue(currentEarning);
		this.getEnviroments().setTemperature(currentTemp / childs.size());
		this.getEnviroments().setWindOrIrradiation(currentWind / childs.size());
		this.setTimestamp(outgoingChild.getTimestamp());
		collectEvents();
		// not sure
		// this.getEvents().setCode(outgoingChild.getEvents().getCode());
	}

	private void collectEvents() {
		int info = 0;
		int warn = 0;
		int alarm = 0;
		for (LMData child : childs) {
			if (child.getEvents() != null) {
				info += child.getEvents().getInfo();
				warn += child.getEvents().getWarn();
				alarm += child.getEvents().getAlarm();
			}
		}
		this.getEvents().setAlarm(alarm);
		this.getEvents().setWarn(warn);
		this.getEvents().setInfo(info);

	}

	public LMData() {
	}

	public LMData(String identifier, Long timestamp, PlantData plantData, Earning earning, Enviroments enviroments, Events events, Power power) {
		super();
		this.identifier = identifier;
		this.timestamp = timestamp;
		this.plantData = plantData;
		this.earning = earning;
		this.enviroments = enviroments;
		this.events = events;
		this.power = power;
	}

	private String parentIdentifier;

	private String identifier;
	private Long timestamp;
	private PlantData plantData;
	private Earning earning;
	private Enviroments enviroments;
	private Events events;
	private Power power;

	public static class PlantData {
		public PlantData() {
		}

		public PlantData(String[] plantTypeCode, String plantUUID, String plantName, DeviceDef device, String place) {
			super();
			this.plantTypeCode = plantTypeCode;
			this.plantUUID = plantUUID;
			this.plantName = plantName;
			this.device = device;
			this.place = place;
		}

		private String[] plantTypeCode;
		private String plantUUID;
		private String plantName;
		private DeviceDef device;
		private String place;

		public String[] getPlantTypeCode() {
			return plantTypeCode;
		}

		public void setPlantTypeCode(String[] plantTypeCode) {
			this.plantTypeCode = plantTypeCode;
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

		public DeviceDef getDevice() {
			return device;
		}

		public void setDevice(DeviceDef device) {
			this.device = device;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((device == null) ? 0 : device.hashCode());
			result = prime * result + ((plantName == null) ? 0 : plantName.hashCode());
			result = prime * result + Arrays.hashCode(plantTypeCode);
			result = prime * result + ((plantUUID == null) ? 0 : plantUUID.hashCode());
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
			PlantData other = (PlantData) obj;
			if (device == null) {
				if (other.device != null)
					return false;
			} else if (!device.equals(other.device))
				return false;
			if (plantName == null) {
				if (other.plantName != null)
					return false;
			} else if (!plantName.equals(other.plantName))
				return false;
			if (!Arrays.equals(plantTypeCode, other.plantTypeCode))
				return false;
			if (plantUUID == null) {
				if (other.plantUUID != null)
					return false;
			} else if (!plantUUID.equals(other.plantUUID))
				return false;
			return true;
		}

		public String getPlace() {
			return place;
		}

		public void setPlace(String place) {
			this.place = place;
		}
	}

	public static class DeviceDef {
		public DeviceDef() {
		}

		public DeviceDef(String deviceType, String deviceManufactureType, String deviceSerial, String measureLevel) {
			super();
			this.deviceType = deviceType;
			this.deviceManufactureType = deviceManufactureType;
			this.deviceSerial = deviceSerial;
			this.measureLevel = measureLevel;
		}

		private String deviceType;
		private String deviceManufactureType;
		private String deviceSerial;
		private String measureLevel;

		public String getDeviceType() {
			return deviceType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
		}

		public String getDeviceManufactureType() {
			return deviceManufactureType;
		}

		public void setDeviceManufactureType(String deviceManufactureType) {
			this.deviceManufactureType = deviceManufactureType;
		}

		public String getDeviceSerial() {
			return deviceSerial;
		}

		public void setDeviceSerial(String deviceSerial) {
			this.deviceSerial = deviceSerial;
		}

		public String getMeasureLevel() {
			return measureLevel;
		}

		public void setMeasureLevel(String measureLevel) {
			this.measureLevel = measureLevel;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((deviceManufactureType == null) ? 0 : deviceManufactureType.hashCode());
			result = prime * result + ((deviceSerial == null) ? 0 : deviceSerial.hashCode());
			result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
			result = prime * result + ((measureLevel == null) ? 0 : measureLevel.hashCode());
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
			DeviceDef other = (DeviceDef) obj;
			if (deviceManufactureType == null) {
				if (other.deviceManufactureType != null)
					return false;
			} else if (!deviceManufactureType.equals(other.deviceManufactureType))
				return false;
			if (deviceSerial == null) {
				if (other.deviceSerial != null)
					return false;
			} else if (!deviceSerial.equals(other.deviceSerial))
				return false;
			if (deviceType == null) {
				if (other.deviceType != null)
					return false;
			} else if (!deviceType.equals(other.deviceType))
				return false;
			if (measureLevel == null) {
				if (other.measureLevel != null)
					return false;
			} else if (!measureLevel.equals(other.measureLevel))
				return false;
			return true;
		}

	}

	public static class Earning {
		public Earning() {
		}

		public Earning(double currentValue, double day1ago, double day2ago, double day3ago) {
			super();
			this.currentValue = currentValue;
			this.day1ago = day1ago;
			this.day2ago = day2ago;
			this.day3ago = day3ago;
		}

		private double currentValue;
		private double day1ago;
		private double day2ago;
		private double day3ago;

		public double getCurrentValue() {
			return currentValue;
		}

		public void setCurrentValue(double currentValue) {
			this.currentValue = currentValue;
		}

		public double getDay1ago() {
			return day1ago;
		}

		public void setDay1ago(double day1ago) {
			this.day1ago = day1ago;
		}

		public double getDay2ago() {
			return day2ago;
		}

		public void setDay2ago(double day2ago) {
			this.day2ago = day2ago;
		}

		public double getDay3ago() {
			return day3ago;
		}

		public void setDay3ago(double day3ago) {
			this.day3ago = day3ago;
		}

	}

	public static class Enviroments {
		public Enviroments() {
		}

		public Enviroments(double windOrIrradiation, double temperature) {
			super();
			this.windOrIrradiation = windOrIrradiation;
			this.temperature = temperature;
		}

		private double windOrIrradiation;
		private double temperature;

		public double getWindOrIrradiation() {
			return windOrIrradiation;
		}

		public void setWindOrIrradiation(double windOrIrradiation) {
			this.windOrIrradiation = windOrIrradiation;
		}

		public double getTemperature() {
			return temperature;
		}

		public void setTemperature(double temperature) {
			this.temperature = temperature;
		}

	}

	public static class Events {
		public Events() {
		}

		public Events(int info, int warn, int alarm, String code) {
			super();
			this.info = info;
			this.warn = warn;
			this.alarm = alarm;
			this.code = code;
		}

		private int info;
		private int warn;
		private int alarm;
		private String code = "";

		public int getInfo() {
			return info;
		}

		public void setInfo(int info) {
			this.info = info;
		}

		public int getWarn() {
			return warn;
		}

		public void setWarn(int warn) {
			this.warn = warn;
		}

		public int getAlarm() {
			return alarm;
		}

		public void setAlarm(int alarm) {
			this.alarm = alarm;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}

	public static class Power {
		public Power() {
		}

		public Power(double relative, double current) {
			super();
			this.relative = relative;
			this.current = current;
		}

		private double relative;
		private double current;

		public double getRelative() {
			return relative;
		}

		public void setRelative(double relative) {
			this.relative = relative;
		}

		public double getCurrent() {
			return current;
		}

		public void setCurrent(double current) {
			this.current = current;
		}
	}

	@Override
	public String toString() {
		return "LMData [identifier=" + identifier + ", timestamp=" + timestamp + ", plantData=" + plantData + ", earning=" + earning
				+ ", enviroments=" + enviroments + ", events=" + events
				+ ", power=" + power + "]";
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public PlantData getPlantData() {
		return plantData;
	}

	public void setPlantData(PlantData plantData) {
		this.plantData = plantData;
	}

	public Earning getEarning() {
		return earning;
	}

	public void setEarning(Earning earning) {
		this.earning = earning;
	}

	public Enviroments getEnviroments() {
		return enviroments;
	}

	public void setEnviroments(Enviroments enviroments) {
		this.enviroments = enviroments;
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public String getParentIdentifier() {
		return parentIdentifier;
	}

	public void setParentIdentifier(String parentIdentifier) {
		this.parentIdentifier = parentIdentifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((parentIdentifier == null) ? 0 : parentIdentifier.hashCode());
		result = prime * result + ((plantData == null) ? 0 : plantData.hashCode());
		// result = prime * result + ((timestamp == null) ? 0 :
		// timestamp.hashCode());
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
		LMData other = (LMData) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		if (parentIdentifier == null) {
			if (other.parentIdentifier != null)
				return false;
		} else if (!parentIdentifier.equals(other.parentIdentifier))
			return false;
		if (plantData == null) {
			if (other.plantData != null)
				return false;
		} else if (!plantData.equals(other.plantData))
			return false;
		// if (timestamp == null) {
		// if (other.timestamp != null)
		// return false;
		// } else if (!timestamp.equals(other.timestamp))
		// return false;
		return true;
	}

	// public LMData getParent() {
	// return parent;
	// }
	//
	// public void setParent(LMData parent) {
	// this.parent = parent;
	// }

}
