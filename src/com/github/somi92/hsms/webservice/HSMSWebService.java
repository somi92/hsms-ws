package com.github.somi92.hsms.webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface="com.github.somi92.hsms.webservice.HSMSServices")
public class HSMSWebService implements HSMSServices {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost/hsms_database";
	private static final String USER = "milos";
	private static final String PASSWORD = "Skydiving@1992";
	
	
	@Override
	@WebMethod
	public String helloWorld(String name) {
		// TODO Auto-generated method stub
		return "Hello "+name+"!";
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
			
			try {
				
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet queryResult = statement.executeQuery();
				
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
				connection.close();
				return (HSMS[]) allActions.toArray(new HSMS[allActions.size()]);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
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
	
}
