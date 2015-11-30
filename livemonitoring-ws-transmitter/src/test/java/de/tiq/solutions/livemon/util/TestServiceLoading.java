package de.tiq.solutions.livemon.util;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Assert;
import org.junit.Test;

import de.tiq.solutions.livemon.contentdivider.worker.ServiceLoaderInterface;

public class TestServiceLoading {
	@Test
	public void testServiceLoading() {
		Iterator<ServiceLoaderInterface> iter = ServiceLoader.load(ServiceLoaderInterface.class).iterator();

		while (iter.hasNext()) {
			ServiceLoaderInterface serviceLoaderInterface = (ServiceLoaderInterface) iter.next();
			serviceLoaderInterface.sayHello();

		}
		// boolean hasNext = iter.hasNext();
		// Assert.assertTrue(hasNext);
		// if (hasNext) {
		// ServiceLoaderInterface plugin = (ServiceLoaderInterface) iter.next();
		// plugin.sayHello();
		// Assert.assertNotNull(plugin);
		// }

	}
}
