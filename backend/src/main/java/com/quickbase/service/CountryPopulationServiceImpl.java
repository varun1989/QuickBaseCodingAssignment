package com.quickbase.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.quickbase.dao.CountryPopulationDao;
import com.quickbase.dao.CountryPopulationDaoImpl;
import com.quickbase.devint.ConcreteStatService;

public class CountryPopulationServiceImpl implements CountryPopulationService{
	
	CountryPopulationDao countryPopulationDao = new CountryPopulationDaoImpl();
	ConcreteStatService statService = new ConcreteStatService();
	
	@Override
	public Map<String, Integer> getPopulationFromDB() {
		
		return countryPopulationDao.getAllCountryPopulationFromDB();
	}

	@Override
	public Map<String, Integer> getPopulationFromStat() {
		
		List<Pair<String, Integer>> countryInfoStats =  statService.GetCountryPopulations();
		Map<String, Integer> countryInfoStatsMap = new HashMap<>();
		for(Pair<String,Integer> country: countryInfoStats) {
			countryInfoStatsMap.put(country.getKey(), country.getValue());
		}
		if(!countryInfoStatsMap.isEmpty()) {
			return countryInfoStatsMap;
		}
		return null;
	}
	
	

}
