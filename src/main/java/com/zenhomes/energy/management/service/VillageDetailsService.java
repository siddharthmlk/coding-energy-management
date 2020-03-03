package com.zenhomes.energy.management.service;

import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zenhomes.energy.management.model.dto.CounterResponse;

import lombok.NonNull;

/**
 * Service to call the third party api to fetch the village details 
 *
 */
@Service
public class VillageDetailsService {

	@Autowired
	private RestTemplate client;

	@Value("${village.url}")
	private String villageDetailsUrl;

	public Optional<CounterResponse> getVillageDetails(@NonNull String counterId) {
		Map<String, String> map = new HashMap<>();
		map.put("id", counterId);
		 CounterResponse response = client.getForObject(villageDetailsUrl, CounterResponse.class, map);
		 return ofNullable(response);
	}
}
