package com.zenhomes.energy.management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Response body for Consumption record 
 *
 */

@Data
public class ConsumptionResponse {
	
	@JsonProperty
	private String id;

}
