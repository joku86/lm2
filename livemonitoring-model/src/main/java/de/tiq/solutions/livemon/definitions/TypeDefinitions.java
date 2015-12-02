package de.tiq.solutions.livemon.definitions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.tiq.solutions.livemon.model.IncomingData;
import de.tiq.solutions.livemon.model.LMData;
import de.tiq.solutions.livemon.model.plantdefinition.Device;

public class TypeDefinitions {

	public static String WE_TABLE = "EUMONIS_WE";
	public static String PV_TABLE = "EUMONIS_PV";

	// PlantUUID
	public static Map<String, Device> getDefined() {
		Map<String, Device> nodes = new HashMap<String, Device>();
		HashSet<String> ownValues = new HashSet<String>(Arrays.asList("P.Active_W", "E.ActiveProduceSystem_Wh", "WindSpeed_m-s", "TempAmb_degC"));
		// HashSet<String> ownStationKeysSolarParkPSIA = new
		// HashSet<String>(Arrays.asList(new String[] { "MV AC active power",
		// "MV energy day", "Irradiation average", "AC active power", "Energy
		// day",
		// "INV1 AC active power", "INV2 AC active power", "INV3 AC active
		// power", "INV4 AC active power", "INV1 energy day", "INV2 energy day",
		// "INV3 energy day", "INV4 energy day ",
		// "INV1 energy day", "INV2 energy day", "Temperature module tracker2",
		// "Irradiation module tracker1", }));

		HashSet<String> ownStationKeysSolarParkPSIA = new HashSet<String>(Arrays.asList("AC active power", "Energy day",
				"Temperature module tracker2", "Temperature module tracker1", "Irradiation module tracker1", "Irradiation module tracker2"));
		HashSet<String> ownPlantKeysSolarParkPSIA = new HashSet<String>(
				Arrays.asList("MV AC active power", "MV energy day", "Irradiation average", "Temperature module tracker2"));

		Device device = new Device("WEONG000000001", "Geilenkirchen, Lindern(DE)", "ON", 1500000);
		device.addNode("70357", "R-70357", 1500000, ownValues);
		nodes.put(device.getPlantUUID(), device);
		device = new Device("WEONG000000002", "Erkelenz, Keyenberg (DE)", "ON", 3000000);
		device.addNode("65729", "NM-15729", 1000000, ownValues);
		device.addNode("65488", "NM-15488", 1000000, ownValues);
		device.addNode("65486", "NM-15486", 1000000, ownValues);
		nodes.put(device.getPlantUUID(), device);

		device = new Device("PVSPG000000001", "Rosário (PT)", "SP", 2150820);
		device.setColIdentifier(ownPlantKeysSolarParkPSIA);
		device.addNode("INVU1", "INVU1", 1740000, ownStationKeysSolarParkPSIA);
		device.addNode("101927231", "INVU1-INV1", 435000, new HashSet<String>(Arrays.asList("INV1 energy day", "INV1 AC active power")));
		device.addNode("101927230", "INVU1-INV2", 435000, new HashSet<String>(Arrays.asList("INV2 energy day", "INV2 AC active power")));
		device.addNode("101927229", "INVU1-INV3", 435000, new HashSet<String>(Arrays.asList("INV3 energy day", "INV3 AC active power")));
		device.addNode("101927228", "INVU1-INV4", 435000, new HashSet<String>(Arrays.asList("INV4 energy day", "INV4 AC active power")));
		device.addNode("101963215", "INVU2-INV1", 435000, new HashSet<String>(Arrays.asList("INV1 energy day", "INV1 AC active power")));
		device.addNode("101963216", "INVU2-INV2", 435000, new HashSet<String>(Arrays.asList("INV2 energy day", "INV2 AC active power")));
		device.addNode("INVU2", "INVU2", 870000, ownStationKeysSolarParkPSIA);
		nodes.put(device.getPlantUUID(), device);

		device = new Device("PVRTG000000001", "Fuchshain Bauabschnitt I", "PV", 39480);
		device.setColIdentifier(null);
		HashSet<String> suitableEntries = new HashSet<String>(Arrays.asList("Pac", "TmpAmb C", "E-Total", "Log"));
		device.addNode("2110319822", "STP 15TL", 15000, suitableEntries);
		device.addNode("2110144057", "STP 17TL", 17000, suitableEntries);
		device.addNode("2110595689", "STP 15TL", 15000, suitableEntries);
		nodes.put(device.getPlantUUID(), device);
		return nodes;
	}

	// public static HashMap<String, String> getAbbreviation() {
	// HashMap<String, String> values = new HashMap<String, String>();
	// values.put("SP", "Solarpark");
	// values.put("RT", "Aufdach");
	// values.put("ON", "Onshore");
	// values.put("OF", "Offshore");
	// return values;
	// }
	//
	// private static HashMap<String, String> getDeviceTypeDefinition() {
	// HashMap<String, String> values = new HashMap<String, String>();
	// values.put("Wechselrichter", "PVI");
	// values.put("Wechselrichterstation", "PVS");
	// values.put("Photovoltaikanlage", "PV");
	// values.put("Windenergieanlage", "WT");
	// values.put("Datenlogger", "DL");
	// values.put("Photovoltaikmodul", "PVM");
	// values.put("Umgebungstemperatursensor", "TAMB");
	// values.put("Modultemperatursensor", "TPVM");
	// values.put("Einstrahlungssensor", "IRRA");
	// values.put("Wetterstation", "WEAT");
	// values.put("Transformator", "TR");
	// return values;
	// }

	// entweder so oder in jedem contentworker einzeln
	public static int columnMapping(String measuredId) {
		if (isPower(measuredId))
			return 4;
		else if (isEarning(measuredId))
			return 5;
		else if (isTemperature(measuredId))
			return 9;
		else if (isWindOrIrradiation(measuredId))
			return 8;
		else if (isEvent(measuredId))
			return 10;

		return 0;
	}

	private static boolean isWindOrIrradiation(String measuredId) {
		return WIND_IRRADIATION.contains(measuredId);
	}

	private static boolean isEvent(String measuredId) {
		return EVENTS.contains(measuredId);
	}

	private static boolean isPower(String measuredId) {
		return POWER.contains(measuredId);
	}

	private static boolean isEarning(String measuredId) {
		return EARNING.contains(measuredId);
	}

	private static boolean isTemperature(String measuredId) {
		return TEMPERATURE.contains(measuredId);
	}

	private static Set<String> WIND_IRRADIATION = new HashSet<String>(
			Arrays.asList("WindSpeed_m-s", "Irradiation average", "Irradiation module tracker1", "Irradiation module tracker2"));
	private static Set<String> TEMPERATURE = new HashSet<String>(Arrays.asList("Temperature module tracker2", "TempAmb_degC"));
	private static Set<String> EARNING = new HashSet<String>(
			Arrays.asList("E.ActiveProduceSystem_Wh", "MV energy day", "Energy day", "INV1 energy day", "INV2 energy day",
					"INV3 energy day", "INV4 energy day", "E-Total"));

	private static Set<String> POWER = new HashSet<String>(Arrays.asList("P.Active_W", "MV AC active power",
			"AC active power", "INV1 AC active power", "INV2 AC active power", "INV3 AC active power", "INV4 AC active power", "Pac"));

	public static Set<String> EVENTS = new HashSet<String>(
			Arrays.asList("S.CurrentStatusCode1", "S.CurrentStatusCode2", "S.SystemCodes1", "S.SystemCodes2", "S.SystemCodes3", "S.SystemCodes4",
					"S.SystemCodes5", "S.StatusListCode1", "S.EventListCode1", "Log"));

	public static String getIdentifier(IncomingData incoming) {
		String identifier = incoming.getTypeCode() + "::" + incoming.getPlantUUID() + "::"
				+ incoming.getPlantName() + "::" + incoming.getDeviceType() + "::"
				+ incoming.getManufactType() + "::" + incoming.getSerial() + "::"
				+ incoming.getMeasureLevel();
		return identifier;
	}

	// WEONG000000002::Keyenberg::Wndenergieanlage::NM
	// 60/1000::65729::Antriebsstrang::5270008::TempBearing1_degC

	public static boolean isPlant(String incoming) {
		return incoming.equals("PVSPG000000001") || incoming.equals("WEONG000000001") || incoming.equals("PVRTG000000001");
	}

	public static boolean isStation(String incoming) {
		return incoming.equals("INVU1") || incoming.equals("INVU2");
	}

	public static String getRowKeyforPSIATempMEasure(IncomingData incoming) {
		return incoming.getPlantUUID() + "::" + incoming.getPlantName()
				+ "::Wechselrichterstation::Rosario Ost (A area)::INVU2::Wechselrichterstation";
	}

	public static String getPSIAParentRowKey(IncomingData incoming) {
		return incoming.getPlantUUID() + "::" + incoming.getPlantName() + "::Photovoltaikanlage::Rosario::" + incoming.getPlantUUID()
				+ "::Photovoltaikanlage";
	}

	public static String getPSIAParentIdentifier(IncomingData incoming) {
		return incoming.getTypeCode() + "::" + incoming.getPlantUUID() + "::" + incoming.getPlantName() + "::Photovoltaikanlage::Rosario::"
				+ incoming.getPlantUUID() + "::Photovoltaikanlage";
	}

	public static String getKayenberLinderParentIdentifier(IncomingData incoming) {
		return incoming.getTypeCode() + "::" + incoming.getPlantUUID() + "::" + incoming.getPlantName() + "::Windenergieanlage::Windenergieanlage::"
				+ incoming.getPlantUUID() + "::Windenergieanlage";
	}

	public static String getIdentifierString(LMData vdm) {
		return vdm.getPlantData().getPlantTypeCode()[0] + "::" + vdm.getPlantData().getPlantUUID() + "::" + vdm.getPlantData().getPlantName() + "::"
				+ vdm.getPlantData().getDevice().getDeviceType() + "::"
				+ vdm.getPlantData().getDevice().getDeviceManufactureType() + "::" + vdm.getPlantData().getDevice().getDeviceSerial() + "::"
				+ vdm.getPlantData().getDevice().getMeasureLevel();
	}

	public static String getPSIA_ChangerIdentifier(IncomingData incoming) {
		return incoming.getTypeCode() + "::" + incoming.getPlantUUID() + "::" + incoming.getPlantName() + "::Wechselrichterstation::"
				+ getPSIASerialOrientation().get(incoming.getSerial()) + "::" + getPSIALevelOrientation().get(incoming.getSerial())
				+ "::Wechselrichterstation";

	}

	private static HashMap<String, String> getPSIALevelOrientation() {
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("101927231", "INVU1");
		values.put("101927230", "INVU1");
		values.put("101927229", "INVU1");
		values.put("101927228", "INVU1");
		values.put("101963215", "INVU2");
		values.put("101963216", "INVU2");

		return values;
	}

	private static HashMap<String, String> getPSIASerialOrientation() {
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("101927231", "Rosario West (B area)");
		values.put("101927230", "Rosario West (B area)");
		values.put("101927229", "Rosario West (B area)");
		values.put("101927228", "Rosario West (B area)");
		values.put("101963215", "Rosario West (A area)");
		values.put("101963216", "Rosario West (A area)");

		return values;
	}

	public static String getPvFuchshain1ParentIdentifier(IncomingData incoming) {
		return incoming.getTypeCode() + "::" + incoming.getPlantUUID() + "::" + incoming.getPlantName()
				+ "::Photovoltaikanlage::Photovoltaikanlage::"
				+ incoming.getPlantUUID() + "::Photovoltaikanlage";

	}

}
