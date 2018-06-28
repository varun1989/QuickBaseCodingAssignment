package com.quickbase.devint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * This DBManager implementation provides a connection to the database containing population data.
 *
 * Created by ckeswani on 9/16/15.
 */
public class DBManagerImpl implements DBManager {
	
    public Connection getConnection() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:resources/data/citystatecountry.db");
            System.out.println("Opened database successfully");

        } catch (ClassNotFoundException cnf) {
            System.out.println("could not load driver");
        } catch (SQLException sqle) {
            System.out.println("sql exception:" + sqle.getStackTrace());
        }
        return c;
    }
    
    //TODO: Add a method (signature of your choosing) to query the db for population data by country
    
    @Override
    public Map<String,Integer> getAllCountryPopulation() {
        Map<String,Integer> countryInfoMap  = new HashMap<>();
		String sql = "select sum(population) as population, country.countryname from city c, State s, Country country where c.stateid = s.stateid and country.countryid = s.countryid and country.countryid in (select countryid from country) group by countryname";
        
        try (Connection conn = this.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                countryInfoMap.put(rs.getString("countryname"), rs.getInt("population"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return countryInfoMap;
    }

}
