package com.example.txzq.bookclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RequestMapping("/book")
@RestController
@RefreshScope
public class BookController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient discoveryClient;
	
//	@Autowired
//    LoadBalancerClient loadBalancerClient;
	
	
	@RequestMapping("/getMessage")
	public String sayHello() 
	{
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("bookservice", false);
		
		String url = instance.getHomePageUrl();
		String port = instance.getPort() + "";
//		ServiceInstance serviceInstance = loadBalancerClient.choose("bookservice");
		ResponseEntity<String> response = restTemplate.getForEntity(url + "/rest/hello", String.class);
		return response.getBody();
	}
	

}

