//package de.tiq.solutions.livemon.contentdivider.worker;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//import de.tiq.solutions.livemon.definitions.BasicDefinitionsLoader;
//import de.tiq.solutions.livemon.definitions.TypeDefinitions;
//import de.tiq.solutions.livemon.model.IncomingData;
//import de.tiq.solutions.livemon.model.LMData;
//import de.tiq.solutions.livemon.model.LMData.DeviceDef;
//import de.tiq.solutions.livemon.model.LMData.PlantData;
//
//public abstract class ContentDivider {
//	public abstract boolean matchPlant(String plantUUid);
//
//	public abstract List<LMData> getOutgoing(IncomingData incoming);
//
//	protected abstract void updateExisting(IncomingData incominigEntry, LMData outgoingEntry);
//
//	protected abstract LMData createNewLMData(IncomingData incoming);
//
//	protected BasicDefinitionsLoader loader = new BasicDefinitionsLoader();
//
//	public static DeviceDef getDeviceDef(IncomingData incoming) {
//		DeviceDef devDef = new DeviceDef(incoming.getDeviceType(), incoming.getManufactType(), incoming.getSerial(), incoming.getMeasureLevel());
//		return devDef;
//	}
//
//	public static PlantData getPlantDefinition(IncomingData incoming, DeviceDef devDef) {
//		PlantData plantData = new PlantData(new String[] { incoming.getTypeCode() }, incoming.getPlantUUID(), incoming.getPlantName(), devDef,
//				TypeDefinitions.getDefined().get(incoming.getPlantUUID()).getLocation());
//		return plantData;
//	}
//
//	final double getAsDoubleValue(String measureValue) {
//		try {
//			return Double.parseDouble(measureValue);
//		} catch (NumberFormatException nfx) {
//			return 0;
//		}
//	}
//
//	final double getPositiveIfNot(double d) {
//		if (d >= 0)
//			return d;
//		else
//			return 0;
//	}
//
//	public boolean currday(Date unsortable) {
//		return unsortable.before(getEndOfDayBefore(0)) && unsortable.after(getStartOfDayBefore(0));
//	}
//
//	public boolean day1(Date unsortable) {
//		return getEndOfDayBefore(-1).compareTo(unsortable) * unsortable.compareTo(getStartOfDayBefore(-1)) > 0;
//	}
//
//	public boolean day2(Date unsortable) {
//
//		return getEndOfDayBefore(-2).compareTo(unsortable) * unsortable.compareTo(getStartOfDayBefore(-2)) > 0;
//	}
//
//	public boolean day3(Date unsortable) {
//		return getEndOfDayBefore(-3).compareTo(unsortable) * unsortable.compareTo(getStartOfDayBefore(-3)) > 0;
//	}
//
//	public Date getEndOfDayBefore(int days) {
//		Calendar cal = new GregorianCalendar();
//		cal.setTime(new Date());
//		cal.add(Calendar.DATE, days);
//		cal.set(Calendar.HOUR_OF_DAY, 23);
//		cal.set(Calendar.MINUTE, 59);
//		cal.set(Calendar.SECOND, 59);
//		cal.set(Calendar.MILLISECOND, 999);
//		return cal.getTime();
//	}
//
//	public Date getStartOfDayBefore(int days) {
//		Calendar cal = new GregorianCalendar();
//		cal.setTime(new Date());
//		cal.add(Calendar.DATE, days);
//		cal.set(Calendar.HOUR_OF_DAY, 00);
//		cal.set(Calendar.MINUTE, 00);
//		cal.set(Calendar.SECOND, 00);
//		cal.set(Calendar.MILLISECOND, 000);
//		return cal.getTime();
//	}
//
//}
