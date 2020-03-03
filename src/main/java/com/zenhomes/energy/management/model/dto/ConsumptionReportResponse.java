package com.zenhomes.energy.management.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConsumptionReportResponse {

	@JsonProperty(value = "villages")
	private List<VillageDetails> villageReports;

}
