package com.github.somi92.hsms.webservice;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface="com.github.somi92.hsms.webservice.HSMSServices")
public class HSMSWebService implements HSMSServices {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private static String DATABASE_URL;
	private static String USER;
	private static String PASSWORD;
	
	public HSMSWebService() {
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("config.properties");

			prop.load(input);
			
			DATABASE_URL = prop.getProperty("DB_URL");
			USER = prop.getProperty("DB_USER");
			PASSWORD = prop.getProperty("DB_PASS");
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	@WebMethod
	public HSMS[] listAllActions() {
		// TODO Auto-generated method stub
		
		Connection connection = connectToDatabase();
		
		if(connection != null) {
			String query = "select HB.hb_id, HB.opis, HB.broj, HB.cena, ORG.naziv, ORG.website, HB.prioritet, HB.napomena " +
					"from HUMANITARNI_BROJ HB join ORGANIZACIJA ORG using (org_id) " +
					"order by HB.prioritet;";
			LinkedList<HSMS> allActions = new LinkedList<>();
			PreparedStatement statement = null;
			ResultSet queryResult = null;
			
			try {
				
				statement = connection.prepareStatement(query);
				queryResult = statement.executeQuery();
				
				while(queryResult.next()) {
					HSMS hsms = new HSMS();
					hsms.setId(queryResult.getInt("hb_id"));
					hsms.setDesc(queryResult.getString("opis"));
					hsms.setNumber(queryResult.getString("broj"));
					hsms.setPrice(queryResult.getString("cena"));
					hsms.setOrganisation(queryResult.getString("naziv"));
					hsms.setWebPage(queryResult.getString("website"));
					hsms.setPriority(queryResult.getInt("prioritet"));
					hsms.setRemark(queryResult.getString("napomena"));
					allActions.add(hsms);
//					System.out.println(hsms);
				}
				return (HSMS[]) allActions.toArray(new HSMS[allActions.size()]);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	@WebMethod
	public HSMS[] listActionsByPriority(int priority) {
		// TODO Auto-generated method stub
		
Connection connection = connectToDatabase();
		
		if(connection != null) {
			String query = "select HB.hb_id, HB.opis, HB.broj, HB.cena, ORG.naziv, ORG.website, HB.prioritet, HB.napomena " +
					"from HUMANITARNI_BROJ HB join ORGANIZACIJA ORG using (org_id) " +
					"where HB.prioritet = ?;";
			LinkedList<HSMS> allActions = new LinkedList<>();
			PreparedStatement statement = null;
			ResultSet queryResult = null;
			
			try {
				
				statement = connection.prepareStatement(query);
				statement.setInt(1, priority);
				queryResult = statement.executeQuery();
				
				while(queryResult.next()) {
					HSMS hsms = new HSMS();
					hsms.setId(queryResult.getInt("hb_id"));
					hsms.setDesc(queryResult.getString("opis"));
					hsms.setNumber(queryResult.getString("broj"));
					hsms.setPrice(queryResult.getString("cena"));
					hsms.setOrganisation(queryResult.getString("naziv"));
					hsms.setWebPage(queryResult.getString("website"));
					hsms.setPriority(queryResult.getInt("prioritet"));
					hsms.setRemark(queryResult.getString("napomena"));
					allActions.add(hsms);
//					System.out.println(hsms);
				}
				return (HSMS[]) allActions.toArray(new HSMS[allActions.size()]);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private Connection connectToDatabase() {
		try {
			
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
			return connection;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int test(int r) {
		try {
			if(r == 0) {
				System.out.println("OK");
			} else {
				throw new Exception();
			}
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("In catch!");
		} finally {
			System.out.println("In finally!");
		}
		System.out.println("End of method.");
		return 1;
	}
}
