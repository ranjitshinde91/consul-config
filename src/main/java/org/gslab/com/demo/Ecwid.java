package org.gslab.com.demo;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.google.gson.JsonObject;

public class Ecwid {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		ConsulClient client = new ConsulClient("localhost");
		
		System.out.println(client.getDatacenters());

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("firstName", "Ranjit");
		jsonObject.addProperty("lastName", "Shinde");
		jsonObject.addProperty("company", "GSLab");
		jsonObject.addProperty("address", "3rd Floor Amar Arma Genesis, Baner Pune");
		jsonObject.addProperty("mobileNo", 1234567890L);

		client.setKVValue("json", jsonObject.toString());
		client.setKVValue("string", "Ranjit");

		// get single KV for key
		Response<GetValue> keyValueResponse = client.getKVValue("binary");
		System.out.println(keyValueResponse.getValue().getKey() + ": " + keyValueResponse.getValue().getDecodedValue()); // prints

		// register new service
		/**
		 * NewService newService = new NewService();
		 * newService.setId("myapp_01"); newService.setName("myapp");
		 * newService.setTags(Arrays.asList("EU-West", "EU-East"));
		 * newService.setPort(8080); client.agentServiceRegister(newService);
		 * 
		 * NewService.Check serviceCheck = new NewService.Check();
		 * serviceCheck.setScript("/usr/bin/some-check-script");
		 * serviceCheck.setInterval("10s"); newService.setCheck(serviceCheck);
		 * 
		 * // query for healthy services based on name (returns myapp_01 and
		 * myapp_02 if healthy) Response<List<HealthService>> healthyServices =
		 * client.getHealthServices("myapp", true, QueryParams.DEFAULT);
		 * System.out.println(healthyServices.getValue());
		 **/

	}
}
