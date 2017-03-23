package org.gslab.com.consul.configreader;

public class App {

	public static void main(String[] args) {
		
		ConfigReader reader = new ConfigReader();
		Config config = reader.readConfigurationFromConsul("com.gslab.conf.test");
		System.out.println(config.firstName()+" - "+config.lastName());
		
		Config config2 = reader.readConfigurationFromFile("C://Users/GS-0639/ranjit/consul-demo/src/main/resources/test.yaml");
		System.out.println(config2.firstName()+" - "+config2.lastName());
		
	}
}
