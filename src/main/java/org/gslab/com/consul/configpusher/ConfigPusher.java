package org.gslab.com.consul.configpusher;

import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;

import org.cfg4j.source.context.propertiesprovider.JsonBasedPropertiesProvider;
import org.cfg4j.source.context.propertiesprovider.PropertiesProvider;
import org.cfg4j.source.context.propertiesprovider.PropertiesProviderSelector;
import org.cfg4j.source.context.propertiesprovider.PropertyBasedPropertiesProvider;
import org.cfg4j.source.context.propertiesprovider.YamlBasedPropertiesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.Properties;

public class ConfigPusher {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigPusher.class);

	private String configPath = "C://Users/GS-0639/ranjit/consul-demo/src/main/resources/";

	private String globalPrefix = "com/gslab/conf";

	private String host;

	private int port;

	private KeyValueClient kvClient;

	public void push() throws Exception {

		LOG.info("Connecting to Consul client at " + host + ":" + port);

		Consul consul = Consul.builder().build();
		kvClient = consul.keyValueClient();

		PropertiesProviderSelector propertiesProviderSelector = new PropertiesProviderSelector(new PropertyBasedPropertiesProvider(),
				new YamlBasedPropertiesProvider(), new JsonBasedPropertiesProvider());

		Path start = Paths.get(configPath);
		Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				if (file.toString().endsWith(".yaml") || file.toString().endsWith("properties")) {
					LOG.info("found config file: " + file.getFileName().toString() + " in context " + start.relativize(file.getParent()).toString());

					try (InputStream input = new FileInputStream(file.toFile())) {

						Properties properties = new Properties();
						PropertiesProvider provider = propertiesProviderSelector.getProvider(file.getFileName().toString());
						properties.putAll(provider.getProperties(input));

						String prefix = file.getFileName().toString().substring(0, file.getFileName().toString().indexOf("."));

						for (Map.Entry<Object, Object> prop : properties.entrySet()) {
							System.out.println(globalPrefix + "/" + prefix + "/" + prop.getKey().toString()+"-"+ prop.getValue().toString());
							kvClient.putValue(globalPrefix + "/" + prefix + "/" + prop.getKey().toString(), prop.getValue().toString());
						}

					} catch (IOException e) {
						throw new IllegalStateException("Unable to load properties from file: " + file, e);
					}
				}

				return FileVisitResult.CONTINUE;
			}
		});
		System.out.println("Complete");
	}

	public static void main(String[] args) throws Exception {
		ConfigPusher configPusher = new ConfigPusher();
		configPusher.push();
	}

}