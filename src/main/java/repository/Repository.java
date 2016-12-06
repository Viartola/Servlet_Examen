package repository;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class Repository {
static File archivo = Paths.get(".").toAbsolutePath().normalize().toFile();
static String ruta = archivo.toString() + "/src/main/resources/";

static final String JDBC_DRIVER = "org.h2.Driver"; 
static final String DB_URL = "jdbc:h2:" + ruta + "DBtest";

static final String USER = "sa";
static final String PASS = "";


	public void deleteCountry(String language){
		Connection conn = null;
	    Statement stmt = null;
	
	    try {
	        Class.forName("org.h2.Driver");
	
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM Countries WHERE language = '" + language + "'";
	
	        stmt.executeUpdate(sql);
	
	    } catch (SQLException se) {
	        se.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeStm(conn, stmt);
	        closeCon(conn);
	    } 
	} 

	public void deleteLannguage(String language){
		Connection conn = null;
	    Statement stmt = null;
	
	    try {
	        Class.forName("org.h2.Driver");
	
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM Languages WHERE language = '" + language + "'";
	
	        stmt.executeUpdate(sql);
	
	    } catch (SQLException se) {
	        se.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeStm(conn, stmt);
	        closeCon(conn);
	    } 
	} 

	private void closeCon(Connection conn) {
		try {
		    if (conn!= null)
		        conn.close();
		} catch (SQLException se) {
		    se.printStackTrace();
		} 
	}

	private void closeStm(Connection conn, Statement stmt) {
		try {
		    if (stmt!=null)
		        conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
	}

	public  List<Country> listAllCountries(){
		Connection conn = null;
		List<Country> listAllCountries= new ArrayList<Country>();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Statement stmt = null;
	
	    try {
		        Class.forName("org.h2.Driver");
		
			    conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   
			    stmt = conn.createStatement();
				
				prepareStatement = conn.prepareStatement("SELECT * FROM Countries");
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next()){
					Country userInDatabase = new Country();
					userInDatabase.setCountry(resultSet.getString(1));
					userInDatabase.setLanguage(resultSet.getString(2));
					
					listAllCountries.add(userInDatabase);
				}
	
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStm(conn, stmt);
            closeRs(resultSet);
            closeCon(conn);
        } 
	    return listAllCountries;
	}
	
	public  List<Language> listAllLanguages(){
    	Connection conn = null;
    	List<Language> listAllLanguages = new ArrayList<Language>();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");

		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   
		    stmt = conn.createStatement();
			
			prepareStatement = conn.prepareStatement("SELECT * FROM Languages");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Language userInDatabase = new Language();
				userInDatabase.setLanguage(resultSet.getString(1));
				
				listAllLanguages.add(userInDatabase);
			}

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStm(conn, stmt);
            closeRs(resultSet);
            closeCon(conn);
        } 
        return listAllLanguages;
	}

	private void closeRs(ResultSet resultSet) {
		if(
			resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
    
	public void insertNewCountry(String country, String language){
	    	Connection conn = null;
	        Statement stmt = null;
	
	        try {
	            Class.forName("org.h2.Driver");
	
			    conn = DriverManager.getConnection(DB_URL, USER, PASS);
			  
			    stmt = conn.createStatement();
			
			    String sql = "REPLACE INTO Countries (country,language) VALUES ('" + country + "', '" + language + "')";
	               
	        stmt.executeUpdate(sql);
	    } catch (SQLException se) {            
	        se.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeStm(conn, stmt);
	        closeCon(conn);
	    } 
	}
   
	public  void insertNewLanguage(String language){
	   Connection conn = null;
	   Statement stmt = null;
	
	   try {
	       Class.forName("org.h2.Driver");
	
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 
		   stmt = conn.createStatement();
		
		   String sql = "REPLACE INTO Languages (language) VALUES ('" + language + "')";
	                  
	           stmt.executeUpdate(sql);
       } catch (SQLException se) {            
           se.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           closeStm(conn, stmt);
           closeCon(conn);
       } 
	}
   
	public void createLanguagesTable(){
	   Connection conn = null;
       Statement stmt = null;

       try {
           Class.forName("org.h2.Driver");

		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  
		   stmt = conn.createStatement();
		
		   String sql = "CREATE TABLE IF NOT EXISTS Languages (language VARCHAR(255), PRIMARY KEY (language))";

           stmt.executeUpdate(sql);
       } catch (SQLException se) {
           se.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
    	   closeStm(conn, stmt);
           closeCon(conn);
       } 
	}

   
	public void createCountriesTable(){
	   Connection conn = null;
	   Statement stmt = null;
	
	   try {
	       Class.forName("org.h2.Driver");

		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  
		   stmt = conn.createStatement();
		
		   String sql = "CREATE TABLE IF NOT EXISTS Countries(country VARCHAR(255), language VARCHAR(255), PRIMARY KEY (country))";
	
	       stmt.executeUpdate(sql);
	   } catch (SQLException se) {
	       se.printStackTrace();
	   } catch (Exception e) {
	       e.printStackTrace();
	   } finally {
		   closeStm(conn, stmt);
	       closeCon(conn);
	   } 
	}  
}
