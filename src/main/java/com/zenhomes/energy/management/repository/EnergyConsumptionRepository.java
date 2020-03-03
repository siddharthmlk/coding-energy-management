package com.zenhomes.energy.management.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zenhomes.energy.management.model.EnergyConsumptionRecord;


@Repository
public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumptionRecord, Long> {

	@Query("select e from EnergyConsumptionRecord e where e.logDate between :startDate and :endDate")
	List<EnergyConsumptionRecord> getEnergyConsumptionByDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
