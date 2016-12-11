package de.tiq.solutions.livemon.plugin;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceLoader;

import de.tiq.solutions.livemon.contentdivider.worker.ServiceLoaderInterface;

public class PluginLoader {

	public static void main(String[] args) throws IOException {
		File loc = new File("../ext");

		File[] flist = loc.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.getPath().toLowerCase().endsWith(".jar");
			}
		});
		URL[] urls = new URL[flist.length];
		for (int i = 0; i < flist.length; i++)
			urls[i] = flist[i].toURI().toURL();
		URLClassLoader ucl = new URLClassLoader(urls, PluginLoader.class.getClassLoader());

		System.out.println(urls.length);
		 //Funktion:Service-Provider als extra projekt welches von Extension und dem 

		ServiceLoader<ServiceLoaderInterface> sl = ServiceLoader.load(ServiceLoaderInterface.class, ucl);
		Iterator<ServiceLoaderInterface> apit = sl.iterator();
		while (apit.hasNext()) {
			ServiceLoaderInterface next = apit.next();
			System.out.println(next.getClass().getName());
			next.sayHello();
		}
	}

}
