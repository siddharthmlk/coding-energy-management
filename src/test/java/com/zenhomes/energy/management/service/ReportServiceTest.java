package com.zenhomes.energy.management.service;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.zenhomes.energy.management.model.CounterRecord;
import com.zenhomes.energy.management.model.EnergyConsumptionRecord;
import com.zenhomes.energy.management.model.VillageRecord;
import com.zenhomes.energy.management.model.dto.ConsumptionReportResponse;
import com.zenhomes.energy.management.repository.EnergyConsumptionRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

	@Mock
	private EnergyConsumptionRepository repository;

	private ReportService service;

	@Before
	public void setup() {
		service = new ReportService(repository);
	}

	@Test
	public void test_generateConsumptionReportForLast10min() {
		EnergyConsumptionRecord ecr = EnergyConsumptionRecord.builder().amount(152.22).logDate(new Date())
				.counter(CounterRecord.builder().counterRequestId("2")
						.village(VillageRecord.builder().name("KBC").villageRequestId("100").build()).build())
				.build();

		EnergyConsumptionRecord ecr1 = EnergyConsumptionRecord.builder().amount(200.22).logDate(new Date())
				.counter(CounterRecord.builder().counterRequestId("2")
						.village(VillageRecord.builder().name("ABC").villageRequestId("100").build()).build())
				.build();

		EnergyConsumptionRecord ecr2 = EnergyConsumptionRecord.builder().amount(101.99).logDate(new Date())
				.counter(CounterRecord.builder().counterRequestId("1")
						.village(VillageRecord.builder().name("XYZ").villageRequestId("200").build()).build())
				.build();

		Mockito.when(repository.getEnergyConsumptionByDates(Mockito.any(), Mockito.any()))
				.thenReturn(Arrays.asList(ecr, ecr1, ecr2));

		ConsumptionReportResponse report = service.getVillageWiseConsumptionDetails("10m");
		Assert.assertThat("village count is wrong", report.getVillageReports().size(), equalTo(3));

	}

}
