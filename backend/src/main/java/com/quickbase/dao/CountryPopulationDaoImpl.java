package com.quickbase.dao;

import java.sql.Connection;
import java.util.Map;

import com.quickbase.devint.DBManager;
import com.quickbase.devint.DBManagerImpl;

public class CountryPopulationDaoImpl implements CountryPopulationDao{
	
	@Override
	public Map<String, Integer> getAllCountryPopulationFromDB() {
		
		DBManager dbm = new DBManagerImpl();
		return dbm.getAllCountryPopulation();
	}


}
