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

	public void BorrarTabla(String language){
		Connection conn = null;
	    Statement stmt = null;
	
	    try {
	        Class.forName("org.h2.Driver");
	
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM Paises WHERE idiomaPaises = '" + language + "'";
			String sql2 = "DELETE FROM Idiomas WHERE idioma = '" + language + "'";
	
	        stmt.executeUpdate(sql);
	        stmt.executeUpdate(sql2);
	
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

	public  List<Country> listarPaises(){
		Connection conn = null;
		List<Country> listAllCountries= new ArrayList<Country>();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Statement stmt = null;
	
	    try {
		        Class.forName("org.h2.Driver");
		
			    conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   
			    stmt = conn.createStatement();
				
				prepareStatement = conn.prepareStatement("SELECT * FROM Paises");
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
            closeCon(conn);
            closeRs(resultSet);
        } 
	    return listAllCountries;
	}
	
	public  List<Language> listarIdiomas(){
    	Connection conn = null;
    	List<Language> listAllLanguages = new ArrayList<Language>();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");

		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   
		    stmt = conn.createStatement();
			
			prepareStatement = conn.prepareStatement("SELECT * FROM Idiomas");
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
            closeCon(conn);
            closeRs(resultSet);
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
    
   public void insertarTablaPaises(String country, String language){
    	Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");

		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  
		    stmt = conn.createStatement();
		
		    String sql = "REPLACE INTO Paises (pais,idiomaPaises) VALUES ('" + country + "', '" + language + "')";
                   
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
   
   public  void insertarTablaIdiomas(String language){
	   Connection conn = null;
       Statement stmt = null;

       try {
           Class.forName("org.h2.Driver");

		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 
		   stmt = conn.createStatement();
		
		   String sql = "REPLACE INTO Idiomas (idioma) VALUES ('" + language + "')";
                  
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
   
   public void crearTablaIdiomas(){
	   Connection conn = null;
       Statement stmt = null;

       try {
           Class.forName("org.h2.Driver");

		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  
		   stmt = conn.createStatement();
		
		   String sql = "CREATE TABLE IF NOT EXISTS Idiomas (idioma VARCHAR(255), PRIMARY KEY (idioma))";

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

   
   public void crearTablaPaises(){
	   Connection conn = null;
	   Statement stmt = null;
	
	   try {
	       Class.forName("org.h2.Driver");

		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		  
		   stmt = conn.createStatement();
		
		   String sql = "CREATE TABLE IF NOT EXISTS Paises(pais VARCHAR(255), idiomaPaises VARCHAR(255), PRIMARY KEY (pais))";
	
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
