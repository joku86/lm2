package de.tiq.solutions.livemon.app.main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class LmConfigs {
	private static class LmConfig{
		private 
		
	}
	private int s=456;
	public String getConfig(){
		System.out.println("nachdem es gebaut wurde "+s);
		return "hier kommts";
	}
	{
		//versuch die config datei zu lesen wenn nicht dann ist die Instanz der Config klasse null
		try {
			JAXBContext jc = JAXBContext.newInstance(LmConfig.class);
			jc.createUnmarshaller().unmarshal(new File("../config/lm_config.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("sdf "+s);
		LmConfigs.this.s=1111;
		System.out.println("klasse wird genutzt "+s+  "  ");
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
