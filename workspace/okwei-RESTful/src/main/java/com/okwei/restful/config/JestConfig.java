package com.okwei.restful.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.okwei.restful.common.PropManager;

@Configuration
public class JestConfig {
	@Bean
	public JestClient jestClient() throws Exception {

		String connectionUrl1 = PropManager.getInstance().getProperty("restful.indexurl.node1");
		String connectionUrl2 = PropManager.getInstance().getProperty("restful.indexurl.node2");
		String connectionUrl3 = PropManager.getInstance().getProperty("restful.indexurl.node3");

		LinkedHashSet<String> servers = new LinkedHashSet<String>();
		servers.add(connectionUrl1);
		servers.add(connectionUrl2);
		servers.add(connectionUrl3);

		// Configuration
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(servers).discoveryEnabled(true).discoveryFrequency(1l, TimeUnit.MINUTES).multiThreaded(true)
				.build());
		return factory.getObject();
	}
}
