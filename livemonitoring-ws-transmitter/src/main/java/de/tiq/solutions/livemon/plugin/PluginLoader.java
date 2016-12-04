package de.tiq.solutions.livemon.plugin;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceLoader;

import de.ext.Component;
import de.test.ManagePlugins;

public class PluginLoader {

	public void getAllFilePlugins() {
		File loc = new File("ext");

		File[] flist = loc.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.getPath().toLowerCase().endsWith(".jar");
			}
		});
		URL[] urls = new URL[flist.length];
		for (int i = 0; i < flist.length; i++)
			urls[i] = flist[i].toURI().toURL();
		URLClassLoader ucl = new URLClassLoader(urls, ManagePlugins.class.getClassLoader());

		System.out.println(urls.length);
		// entweder Component interface mit vollqualifiezierten namen hier im
		// projekt oder als extra projekt welches dann auch von den Plugins
		// eingebunden wird

		ServiceLoader<Component> sl = ServiceLoader.load(Component.class, ucl);
		Iterator<Component> apit = sl.iterator();
		while (apit.hasNext()) {
			Component next = apit.next();
			System.out.println(next.getClass().getName());
			System.out.println(next.start());
		}
	}

}
