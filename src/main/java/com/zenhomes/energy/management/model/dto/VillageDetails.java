package com.zenhomes.energy.management.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class VillageDetails {
	
	public VillageDetails(String villageName, Double amount){
		this.villageName = villageName;
		this.consumption = amount;
	}
	
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty("village_name")
	private String villageName;
	
	@JsonProperty
	private Double consumption;

}
