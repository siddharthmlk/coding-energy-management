package com.zenhomes.energy.management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CounterResponse {
	
	@JsonProperty
	private String id;
	
	@JsonProperty("village")
	private VillageDetails village;

}
