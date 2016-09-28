package de.tiq.solutions.livemon.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import de.tiq.solutions.livemon.app.main.LmConfigs;

public class TestServer {
	@Test
	public void testName() throws Exception {
		LmConfigs l = LmConfigs.getInsance();
		Assert.assertEquals("127.0.0.1", l.getConfigValue("server.ip"));
	}

}
