package com.quickbase.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServiceUtil {
	
	static Map<String, String> multipleCountryNamesMap = new HashMap<>(); 
	
	/**
	 * This method checks for multiple countryNames and
	 * returns a List of countryNames which needs to be removed.
	 * 
	 * @param statsCountries
	 * @param dbSource
	 * @return
	 */
	public static Set<String> checkForDuplicates(Set<String> statsCountries, Map<String, Integer> dbSource) {
		Set<String> duplicateList = new HashSet<>();
		for(String country: statsCountries) {
			if(multipleCountryNamesMap.containsKey(country)) { //multiple names found.
				String val = multipleCountryNamesMap.get(country); //get the other countryName.
				if(dbSource.containsKey(val)) { //try to find other countryName in dbSource.
					duplicateList.add(country); //add the statsCountry to the duplicate list.
				}
			}
		}
		return duplicateList;
		
	}
	
	/**
	 * This method populates all the country names which has multiple names.
	 */
	public static void populateAllMultipleCountryNames() {
		multipleCountryNamesMap.put("United States of America", "U.S.A.");
		multipleCountryNamesMap.put("U.S.A.", "United States of America");
		multipleCountryNamesMap.put("United Kingdom", "U.K.");
		multipleCountryNamesMap.put("U.K.", "United Kingdom");
		
	}
}
