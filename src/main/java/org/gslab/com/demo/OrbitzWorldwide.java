package org.gslab.com.demo;

import com.google.gson.JsonObject;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.NotRegisteredException;

public class OrbitzWorldwide {
	public static void main(String[] args) throws NotRegisteredException {
		System.out.println("Hello World!");
		
		Consul consul = Consul.builder().build(); // connect to Consul on localhost
		
		KeyValueClient kvClient = consul.keyValueClient();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "Ranjit");
		jsonObject.addProperty("lastName", "Shinde");

		
		kvClient.putValue("tradeRepo", jsonObject.toString()	);

		String value = kvClient.getValueAsString("tradeRepo").get(); 
		
		System.out.println(value);
		
	}
}
