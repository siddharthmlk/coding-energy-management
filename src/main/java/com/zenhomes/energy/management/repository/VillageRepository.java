package com.zenhomes.energy.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenhomes.energy.management.model.VillageRecord;

public interface VillageRepository extends JpaRepository<VillageRecord, Long>{

}
