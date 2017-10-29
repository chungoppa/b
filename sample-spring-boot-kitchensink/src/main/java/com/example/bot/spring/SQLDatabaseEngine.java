package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLDatabaseEngine extends DatabaseEngine {
	private static boolean LOCAL=false;
	
	//@Override
	String search(String text,String dbName) throws Exception {
		switch(dbName)
		{
		/*case "chatbotDBTable":
		{*//*
		String result;
		Connection connection=getConnection();
		
		PreparedStatement stmt=connection.prepareStatement("SELECT response FROM chatbotDBTable "
				+ "WHERE LOWER(request) LIKE LOWER( CONCAT('%',?,'%') )");
		
		stmt.setString(1,text);
		
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) {	// not found
			rs.close();
			stmt.close();
			connection.close();
			throw new Exception("NOT FOUND");
		}else {				//found
			result=rs.getString(1);
		}
		rs.close();
		stmt.close();
		connection.close();
		return result;
		}*/
		
		case "faq":
		{
			String result="";
			String[] keywords = text.split(" ");
			Connection connection=getConnection();
			for(String keyword:keywords)
			{
				PreparedStatement stmt=connection.prepareStatement("SELECT * FROM faq "
				+ "WHERE LOWER(keyword) LIKE LOWER( CONCAT('%',?,'%') )");
				stmt.setString(1,keyword);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String[] keySentences = rs.getString(1).split("%");
					for(String keySentence:keySentences)
					{
						WagnerFischer WF = new WagnerFischer(text,keySentence);
						if(WF.getDistance()<(keySentence.length()/2))
						{
							result= rs.getString(2);
							return result;
						}
					}
				}
				rs.close();
				stmt.close();
			}
			connection.close();
		}
		}
		return "I am not sure about what you mean, but I will forward your question to our agency.";
	}
	
	
	
	
	private Connection getConnection() throws URISyntaxException, SQLException {
		/*Connection connection;
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		
		String username;
		String password;
		String dbUrl;
		if(LOCAL) {	//LOCAL HOST
			username = "tlkoo";
			password = "1234";
			dbUrl = "jdbc:postgresql://localhost:5432/chatbotDB";
		}else {//SERVER on HEROKU
			username = dbUri.getUserInfo().split(":")[0];
			password = dbUri.getUserInfo().split(":")[1];
			dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		}
				
		log.info("Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);
		
		connection = DriverManager.getConnection(dbUrl, username, password);

		return connection;
		*/
		
		Connection connection;
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		log.info("Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);
		
		connection = DriverManager.getConnection(dbUrl, username, password);

		return connection;
		
	}

		
}
