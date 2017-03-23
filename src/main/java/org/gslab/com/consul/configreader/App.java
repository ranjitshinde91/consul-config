package org.gslab.com.consul.configreader;

public class App {

	public static void main(String[] args) {
		
		ConfigReader reader = new ConfigReader();
		Config config = reader.readConfigurationFromConsul("com.gslab.conf.test");
		System.out.println(config.firstName()+" - "+config.lastName());
	}
}
