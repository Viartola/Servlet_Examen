package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import connection.*;

public class RepositoryCountries extends Close{
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/DBtest";
	static ConnectionManager manager = new ConnectionH2();

	public void deleteCountry(String language){
		PreparedStatement preparedStatement = null;
		Connection conn = manager.open(jdbcUrl);
	
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM Countries WHERE language = ?");
			preparedStatement.setString(1, language);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		manager.close(conn);
	} 
	
	public  List<Country> listAllCountries(){
		List<Country> listAllCountries= new ArrayList<Country>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
	
	    try {
	    	preparedStatement = conn.prepareStatement("SELECT * FROM Countries");
	    	resultSet = preparedStatement.executeQuery();
	    	while(resultSet.next()){
	    		Country userInDatabase = new Country();
	    		userInDatabase.setCountry(resultSet.getString(1));
	    		userInDatabase.setLanguage(resultSet.getString(2));
	    		listAllCountries.add(userInDatabase);
	    	}
	    } catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
	    }finally {
			close(resultSet);
			close(preparedStatement);
	    }
	    manager.close(conn);
	    return listAllCountries;
	}
	
	public void insertNewCountry(String country, String language){
		PreparedStatement preparedStatement = null;
		Connection conn = manager.open(jdbcUrl);

		try {
			preparedStatement = conn.prepareStatement("REPLACE INTO Countries (country,language) VALUES (?,?)");
			preparedStatement.setString(1, country);
			preparedStatement.setString(2, language);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		manager.close(conn);
	} 
}