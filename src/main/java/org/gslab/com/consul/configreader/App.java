package org.gslab.com.consul.configreader;

public class App {

	public static void main(String[] args) {
		
		ConfigReader reader = new ConfigReader();
		Config consulCnfig = reader.readConfigurationFromConsul("com.gslab.conf.test");
		System.out.println(consulCnfig.firstName()+" - "+consulCnfig.lastName());
		
		Config fileConfig = reader.readConfigurationFromFile("C://Users/GS-0639/ranjit/consul-demo/src/main/resources/test.yaml");
		System.out.println(fileConfig.firstName()+" - "+fileConfig.lastName());
		
		Config classpathConfig = reader.readConfigurationFromClassPath("test.yaml");
		System.out.println(classpathConfig.firstName()+" - "+classpathConfig.lastName());
	}
}
