package com.zenhomes.energy.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenhomes.energy.management.model.CounterRecord;

public interface CounterRepository extends JpaRepository<CounterRecord, Long> {

}
