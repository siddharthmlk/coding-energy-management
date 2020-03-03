package com.zenhomes.energy.management.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zenhomes.energy.management.model.EnergyConsumptionRecord;
import com.zenhomes.energy.management.model.dto.ConsumptionReportResponse;
import com.zenhomes.energy.management.model.dto.VillageDetails;
import com.zenhomes.energy.management.repository.EnergyConsumptionRepository;

import lombok.AllArgsConstructor;

/**
 * Service class for generating the summary report of per village energy consumption
 * based on the given duration timer. 
 *
 */
@Service
@AllArgsConstructor
public class ReportService {
	
	private EnergyConsumptionRepository repository;
	
	/**
	 * Gathers the consumption data and aggregates and maps as per 
	 * the specification
	 * @param duration
	 * @return
	 */
	public ConsumptionReportResponse getVillageWiseConsumptionDetails(String duration) {
		 Optional<Date> startDate = ReportUtils.getStartDate(duration);
		if(startDate.isPresent()){
			List<EnergyConsumptionRecord> records = repository.getEnergyConsumptionByDates(startDate.get(), new Date());
			return ConsumptionReportResponse.builder().villageReports(transformRecords(records)).build();
		}
		return null;
	}

	private List<VillageDetails> transformRecords(List<EnergyConsumptionRecord> records) {
		Map<String, Double> villageWiseMapping = new HashMap<>();
		records.stream().forEach(record -> {
			String villageName = record.getCounter().getVillage().getName();
			villageWiseMapping.merge(villageName, record.getAmount(), (a, b) -> a + b);
		});
		return villageWiseMapping.entrySet().stream()
				.map(village -> new VillageDetails(village.getKey(),village.getValue()))
				.collect(Collectors.toList());
	}
}
