package com.zenhomes.energy.management.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zenhomes.energy.management.model.dto.ConsumptionRequest;
import com.zenhomes.energy.management.model.dto.ConsumptionResponse;
import com.zenhomes.energy.management.service.ConsumptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConsumptionController {
	
	private final ConsumptionService consumptionService;
	
	/**
	 * Receives the energy consumption details along with counter id of 
	 * corresponding village
	 * 
	 * @param request ConsumptionRequest with consumption details
	 * @return ConsumptionResponse with created consumption id
	 */
	@PostMapping(path="/counter_callback")
	public ConsumptionResponse receiveConsumptionData(@RequestBody ConsumptionRequest request){
		return consumptionService.acceptConsumptionData(request);
	}

}
