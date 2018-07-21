package com.wisdom.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.wisdom.core.Config;

public class ConfigUtil {
	
	public static List<String> getResources(String resourceBase) throws URISyntaxException, IOException{
		
		String stripped = resourceBase.startsWith("/") ? resourceBase.substring(1) : resourceBase;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		List<String> resources = null;
		if(classLoader != null) {
			resources = getResourceListing(classLoader, stripped);
		}
		if (resources == null) {
			resources = getResourceListing(Config.class.getClassLoader(), stripped);
		}
		
		if (resources == null) {
			throw new IOException(resourceBase + " not found");
		}
		return resources;
	}
	
	public static InputStream getResourceAsStream(String resource) throws IOException {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream stream = null;
		
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		
		if (stream == null) {
			stream = Config.class.getResourceAsStream(resource);
		}
		
		if (stream == null) {
			stream = Config.class.getClassLoader().getResourceAsStream(stripped);
		}
		
		if (stream == null) {
			throw new IOException(resource + " not found");
		}
		
		return stream;
	}
	
	public static List<String> getResourceListing(ClassLoader cl, String path) throws URISyntaxException, IOException {
		URL dirUrl = cl.getResource(path);
		
		if (dirUrl != null && dirUrl.getProtocol().equals("file")) {
			/* A file path: easy enough */
			return Arrays.asList(new File(dirUrl.toURI()).list());
		}
		
		if (dirUrl == null) {
	        // String me = clazz.getName().replace(".", "/")+".class";
	        // dirUrl = clazz.getClassLoader().getResource(me);
	      }
		
		if (dirUrl != null && dirUrl.getProtocol().equals("jar")) {
			/* A JAR path */
			String jarPath = dirUrl.getPath().substring(5, dirUrl.getPath().indexOf("!")); // strip out only the JAR
																							// file
			JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
			Enumeration<JarEntry> entries = jar.entries(); // gives ALL entries in jar
			Set<String> result = new HashSet<String>(); // avoid duplicates in case it is a subdirectory
			
			while (entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				
				if (name.startsWith(path)) { // filter according to the path
					String entry = name.substring(path.length());
					int checkSubdir = entry.indexOf("/");
					
					if (checkSubdir >= 0) {
						// if it is a subdirectory, we just return the directory name
						entry = entry.substring(0, checkSubdir);
					}
					
					result.add(entry);
				}
			}
			
			return new ArrayList<String>(result);
		}
		
		throw new UnsupportedOperationException("Cannot list files for URL " + dirUrl);
	}

}
