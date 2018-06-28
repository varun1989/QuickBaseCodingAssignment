package com.quickbase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.quickbase.service.CountryPopulationService;
import com.quickbase.service.CountryPopulationServiceImpl;
import com.quickbase.util.ServiceUtil;

/**
 * The main method of the executable JAR generated from this repository. This is to let you
 * execute something from the command-line or IDE for the purposes of demonstration, but you can choose
 * to demonstrate in a different way (e.g. if you're using a framework)
 */
public class Main {
	
	CountryPopulationService countryPopulationService = new CountryPopulationServiceImpl();
	static Map<String, String> multipleCountryNamesMap = new HashMap<>(); 
    
	public static void main( String args[] ) {
    	ServiceUtil.populateAllMultipleCountryNames();
        Main main = new Main();
        main.startProcess();
        
    }

	private void startProcess() {
		
		Map<String,Integer> source1 = countryPopulationService.getPopulationFromDB();
		Map<String,Integer> source2 = countryPopulationService.getPopulationFromStat();
		Map<String,Integer> combinedSource = combineSource(source1,source2);
		System.out.println("Country Population from DB Source " + source1);
		System.out.println("Country Population from Stats Source API " + source2);
		System.out.println("Data after combining both sources ");
		printData(combinedSource);
	}

	private Map<String,Integer> combineSource(Map<String, Integer> dbSource, Map<String, Integer> statSource) {
		
		Set<String> countries = statSource.keySet();
		Set<String> sameCountryList = ServiceUtil.checkForDuplicates(countries, dbSource);
		Map<String,Integer> combinedSource = new HashMap<>(statSource);
		combinedSource.putAll(dbSource);
		combinedSource.keySet().removeAll(sameCountryList);
		
		return combinedSource;
	}
	
	
	public void printData(Map<String, Integer> data) {
		Set<Map.Entry<String, Integer>> entrySet = data.entrySet();
		Iterator<Entry<String, Integer>> entrySetIterator = entrySet.iterator();
		while (entrySetIterator.hasNext()) {
		   System.out.println("----------------------------------------------------------------");
		   Entry entry = entrySetIterator.next();
		   System.out.println("key: " + entry.getKey() + " |-> population: " + entry.getValue());
		}

	}

	
}