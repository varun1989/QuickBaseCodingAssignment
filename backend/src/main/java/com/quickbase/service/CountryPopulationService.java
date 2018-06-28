package com.quickbase.service;

import java.util.Map;

public interface CountryPopulationService {
	Map<String,Integer> getPopulationFromDB();
	Map<String,Integer> getPopulationFromStat();
}
