package com.example.bot.spring;
/*
 * Testing feature 
 * 
 *  
*/
public class FeatureSudo implements Features {
	private String result=null;
	public String call(User user, String text) {
		result="User ID: "+user.getUserID()	+ "\nAPI Token: " + DialogueFlow.getToken()	+ "\nDatabase_URL:" + System.getenv("DATABASE_URL");
		return result;
	}
	
	public FeatureSudo() {
		
	}
}
