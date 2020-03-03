package com.zenhomes.energy.management.model.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Request body for energy consumption data
 *
 */

@Data
public class ConsumptionRequest {
	
	@JsonProperty("counter_id")
	@NotBlank
    private String counterId;

    @JsonProperty
    @NotBlank
    private Double amount;

}
