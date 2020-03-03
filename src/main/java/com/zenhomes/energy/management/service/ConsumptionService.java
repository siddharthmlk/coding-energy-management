package com.zenhomes.energy.management.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zenhomes.energy.management.model.CounterRecord;
import com.zenhomes.energy.management.model.EnergyConsumptionRecord;
import com.zenhomes.energy.management.model.VillageRecord;
import com.zenhomes.energy.management.model.dto.ConsumptionRequest;
import com.zenhomes.energy.management.model.dto.ConsumptionResponse;
import com.zenhomes.energy.management.model.dto.CounterResponse;
import com.zenhomes.energy.management.repository.EnergyConsumptionRepository;

import lombok.AllArgsConstructor;

/**
 * Service layer for handling energy consumption data
 *
 */
@AllArgsConstructor
@Service
public class ConsumptionService {

	private VillageDetailsService villageDetailsService;
	private EnergyConsumptionRepository repository;

	/**
	 * This service method accepts the energy consumption details and persist
	 * them in database
	 * 
	 * @param request
	 * @return ConsumptionResponse
	 * @throws Exception
	 */
	public ConsumptionResponse acceptConsumptionData(ConsumptionRequest request) {
		EnergyConsumptionRecord record = null;
		Optional<CounterResponse> villageDetails = villageDetailsService.getVillageDetails(request.getCounterId());
		if (villageDetails.isPresent()) {
			record = repository.save(buildEntity(request, villageDetails.get()));
			return buildResponse(record);
		}
		return null;
	}

	/**
	 * Creates data model object to be persisted
	 * @param request
	 * @param counterResponse
	 * @return
	 */
	private EnergyConsumptionRecord buildEntity(ConsumptionRequest request, CounterResponse counterResponse) {
		return EnergyConsumptionRecord.builder().amount(request.getAmount()).logDate(new Date())
				.counter(CounterRecord.builder().counterRequestId(request.getCounterId())
						.village(VillageRecord.builder().villageRequestId(counterResponse.getVillage().getId())
								.name(counterResponse.getVillage().getName()).build())
						.build())
				.build();
	}
	
	private ConsumptionResponse buildResponse(EnergyConsumptionRecord record) {
		ConsumptionResponse response = new ConsumptionResponse();
		 response.setId(record.getId().toString());
		 return response;
	}

}
