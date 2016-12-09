package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import connection.*;

public class RepositoryLanguage extends Close {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/DBtest";
	static ConnectionManager manager = new ConnectionH2();
	
	public void deleteLannguage(String language){
		PreparedStatement preparedStatement = null;
		Connection conn = manager.open(jdbcUrl);
	
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM Languages WHERE language = ?");
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
	
	public  List<Language> listAllLanguages(){
		List<Language> listAllLanguages = new ArrayList<Language>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM Languages");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Language userInDatabase = new Language();
				userInDatabase.setLanguage(resultSet.getString(1));
				
				listAllLanguages.add(userInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(preparedStatement);
		}
	    manager.close(conn);
	    return listAllLanguages;
	}
   
	public  void insertNewLanguage(String language){
		PreparedStatement preparedStatement = null;
		Connection conn = manager.open(jdbcUrl);

		try {
			preparedStatement = conn.prepareStatement("REPLACE INTO Languages (language) VALUES (?)");
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
}