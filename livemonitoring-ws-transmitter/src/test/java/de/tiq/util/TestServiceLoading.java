package de.tiq.util;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Assert;
import org.junit.Test;

import de.tiq.contentdivider.worker.ServiceLoaderInterface;

public class TestServiceLoading {
	@Test
	public void testServiceLoading() {
		Iterator<ServiceLoaderInterface> iter = ServiceLoader.load(ServiceLoaderInterface.class).iterator();
		boolean hasNext = iter.hasNext();
		Assert.assertTrue(hasNext);
		if (hasNext) {
			ServiceLoaderInterface plugin = (ServiceLoaderInterface) iter.next();
			Assert.assertNotNull(plugin);
		}

	}
}
