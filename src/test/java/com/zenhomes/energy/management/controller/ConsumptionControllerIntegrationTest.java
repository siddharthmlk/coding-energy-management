package com.zenhomes.energy.management.controller;

import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumptionControllerIntegrationTest {
	
	@LocalServerPort
    private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void test_createConsumptionRecord() throws JSONException{
		//CREATE RECORD WITH COUNTER_ID 1
		JSONObject request = new JSONObject();
		request.put("counter_id","1");
		request.put("amount",600.11);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:"+port+"/counter_callback", 
				HttpMethod.POST, entity, String.class);
		assertThat("response code is incorrect", response.getStatusCode(), 
				CoreMatchers.equalTo(HttpStatus.OK));
		assertEquals("{\"id\":\"1\"}", response.getBody(), false);
		
		//CREATE RECORD WITH COUNTER_ID 1 
		JSONObject request1 = new JSONObject();
		request1.put("counter_id","1");
		request1.put("amount",200.110);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity1 = new HttpEntity<String>(request1.toString(), headers);
		ResponseEntity<String> response1 = restTemplate.exchange("http://localhost:"+port+"/counter_callback", 
				HttpMethod.POST, entity1, String.class);
		assertThat("response code is incorrect", response1.getStatusCode(), 
				CoreMatchers.equalTo(HttpStatus.OK));
		assertEquals("{\"id\":\"2\"}", response1.getBody(), false);
		
		
		//GENERATE REPORT FOR LAST 10 MIN
		HttpEntity<String> getEntity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange("http://localhost:"+port+"/consumption_report?duration=10m", 
				HttpMethod.GET, getEntity, String.class);
		
		String expected = "{\"villages\":[{\"consumption\":800.22,\"village_name\":\"Villarriba\"}]}";
		assertThat("response code is incorrect", getResponse.getStatusCode(), 
				CoreMatchers.equalTo(HttpStatus.OK));
		assertEquals(expected, getResponse.getBody(), false);
	}
}
