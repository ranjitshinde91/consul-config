package org.gslab.com.consul.configreader;

import java.nio.file.Paths;
import java.util.Arrays;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.classpath.ClasspathConfigurationSource;
import org.cfg4j.source.context.filesprovider.*;
import org.cfg4j.source.consul.ConsulConfigurationSourceBuilder;
import org.cfg4j.source.files.FilesConfigurationSource;

public class ConfigReader {

	public static Config readConfigurationFromConsul(String mountPoint) {

		ConfigurationSource source = new ConsulConfigurationSourceBuilder().build();

		ConfigurationProvider provider = new ConfigurationProviderBuilder().withConfigurationSource(source).build();

		Config config = provider.bind(mountPoint, Config.class);
		
		return config;
	}

	public static Config readConfigurationFromFile(String configFileName) {

		ConfigFilesProvider fileProvider = () -> Arrays.asList(Paths.get(configFileName));

		ConfigurationSource source = new FilesConfigurationSource(fileProvider);

		ConfigurationProvider provider = new ConfigurationProviderBuilder().withConfigurationSource(source).build();

		Config config = provider.bind("", Config.class);
		
		return config;
	}

	public static Config readConfigurationFromClassPath(String fileName){

		ConfigFilesProvider configFilesProvider = () -> Arrays.asList(Paths.get(fileName));

		// Use classpath as configuration store
		ConfigurationSource source = new ClasspathConfigurationSource(configFilesProvider);

		// Create provider
		ConfigurationProvider provider = new ConfigurationProviderBuilder() .withConfigurationSource(source).build();
		
		Config config =  provider.bind("", Config.class);
		
		return config;
	}
}
