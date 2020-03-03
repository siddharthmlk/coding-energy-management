package com.zenhomes.energy.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zenhomes.energy.management.model.dto.ConsumptionReportResponse;
import com.zenhomes.energy.management.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReportController {
	
	private final ReportService reportService;
	
	/**
     * generates on demand consumption report per village for given duration of 
     * time, for e.g. last 24h, 10h, 10m etc. Here "h", "m" refers 
     * to hour and minutes
     *
     * @param duration of the time period along with time unit ("h", "m", "s")
     * @return consumption report which contains village wise energy consumption data
     */
    @GetMapping(path = "/consumption_report")
    public ConsumptionReportResponse fetchEnergyConsumption(@RequestParam String duration) {
        return reportService.getVillageWiseConsumptionDetails(duration);
    }

}
