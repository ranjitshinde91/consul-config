package org.gslab.com.consul.configreader;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.consul.ConsulConfigurationSourceBuilder;

public class ConfigReader {

	public static Config readConfigurationFromConsul(String mountPoint) {
		
		ConfigurationSource source = new ConsulConfigurationSourceBuilder().build();

		ConfigurationProvider provider = new ConfigurationProviderBuilder().withConfigurationSource(source)
				.build();
		
		 Config config = provider.bind(mountPoint, Config.class);
		 return config;
	}

}
