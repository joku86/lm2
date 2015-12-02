package de.tiq.solutions.livemon.definitions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.tiq.solutions.livemon.model.auth.UserModel;
import de.tiq.solutions.livemon.model.event.EventType;

public class BasicDefinitionsLoader {
	private Properties properties;

	public Set<String> getFilterDefinitions() throws IOException {
		Properties properties = getPropertiesFile();

		String str = (String) properties.get("Accepting");
		Set<String> accepting = new HashSet<String>(Arrays.asList(str.split("\\s*,\\s*")));
		return accepting;
	}

	private Properties getPropertiesFile() throws IOException {
		if (properties == null) {
			properties = new Properties();
			BufferedInputStream stream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("BasicDefinitions.properties"));
			properties.load(stream);
			return properties;
		}
		else
			return properties;
	}

	public EventType getEventTypeForCode(String code) throws IOException {
		Properties properties = getPropertiesFile();

		String str = (String) properties.get(code);
		return EventType.valueOf(EventType.class, str);

	}

	public static UserModel readAuthorisationFile() throws JAXBException, FileNotFoundException {
		JAXBContext jaxbContext = JAXBContext.newInstance(UserModel.class);
		File file = new File(System.getProperty("user.dir") + "/clients.xml");
		if (!file.exists())
			throw new FileNotFoundException();
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		UserModel customer = (UserModel) jaxbUnmarshaller.unmarshal(file);
		return customer;
	}
}
