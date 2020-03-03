package com.zenhomes.energy.management.service;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * This is a utility class to convert the duration to actual date 
 *
 */
public class ReportUtils {
	
	private static final Map<String, Function<Long, Duration>> durationMapper = 
			new HashMap<String, Function<Long, Duration>>() {
		{
			put("d", Duration::ofDays);
			put("h", Duration::ofHours);
			put("m", Duration::ofMinutes);
			put("s", Duration::ofSeconds);
		}
	};
	
	public static Optional<Date> getStartDate(String duration){
		String durationValue = duration.substring(0, duration.length()-1);
		String durationUnit = duration.substring(duration.length()-1);
		Function<Long, Duration> function = durationMapper.get(durationUnit);
		if(null != function){
			Long seconds = function.apply(Long.parseLong(durationValue)).getSeconds();
			LocalDateTime minusSeconds = LocalDateTime.now().minusSeconds(seconds);
			return of(Date.from(minusSeconds.atZone(ZoneId.systemDefault()).toInstant()));
		}
		return empty();
		
	}

}
