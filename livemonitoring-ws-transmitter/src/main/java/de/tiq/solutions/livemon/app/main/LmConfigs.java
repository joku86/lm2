package de.tiq.solutions.livemon.app.main;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


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

	private int s = 456;

	public String getConfig() {
		System.out.println("nachdem es gebaut wurde " + s);
		return "hier kommts";
	}

	{
		// versuch die config datei zu lesen wenn nicht dann ist die Instanz der
		// Config klasse null
		try {
			JAXBContext jc = JAXBContext.newInstance(Settings.class);
			File file = new File("../config/lm_config.xml");
			System.out.println(file.getAbsolutePath());
			Settings settings = (Settings)jc.createUnmarshaller().unmarshal(file);
 		System.out.println(settings.getSetting().get(0).getName());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("sdf " + s);
		LmConfigs.this.s = 1111;
		System.out.println("klasse wird genutzt " + s + "  ");
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
