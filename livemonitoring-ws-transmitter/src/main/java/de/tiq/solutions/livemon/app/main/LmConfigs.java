package de.tiq.solutions.livemon.app.main;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
@SuppressWarnings("unused")
public class LmConfigs {
	@XmlRootElement(name = "settings")
	private static class Settings {
		private List<Setting> setting;

		public List<Setting> getSetting() {
			return setting;
		}
		
		public void setSetting(List<Setting> setting) {
			this.setting = setting;
		}

		public static class Setting {
			private String name;
			private String value;
			private String desc;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}

			public String getDesc() {
				return desc;
			}

			public void setDesc(String desc) {
				this.desc = desc;
			}

		}

	}

	private Map<String, Settings.Setting> settingsMap=new HashMap<>();

	public Settings.Setting getSetting(String name){
		return settingsMap.get(name);
	}
	public String getConfigValue(String name) {
		return settingsMap.get(name).getValue();
	}

	{
		try {
			JAXBContext jc = JAXBContext.newInstance(Settings.class);
			File file = new File("../config/lm_config.xml");
			System.out.println(file.getAbsolutePath());
			Settings settings = (Settings)jc.createUnmarshaller().unmarshal(file);
			for (de.tiq.solutions.livemon.app.main.LmConfigs.Settings.Setting setting : settings.getSetting()){
				settingsMap.put(setting.getName(), setting);
			}
		} catch (JAXBException e) {
			//TODO Log
			e.printStackTrace();
		}

	  
	}

	private LmConfigs() {
	}

	private static LmConfigs instance;

	public static synchronized LmConfigs getInsance() {
		if (LmConfigs.instance == null)
			LmConfigs.instance = new LmConfigs();
		return LmConfigs.instance;
	}

}
