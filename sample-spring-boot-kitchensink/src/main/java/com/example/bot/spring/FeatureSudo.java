package com.example.bot.spring;
import java.lang.String;
/*
 * Testing feature 
 * 
 *  
*/
public class FeatureSudo implements Features {
	private String result=null;

	public String call(User user, String text) {
		switch(user.getContext()) {
		case "logined" :
			result="User ID: "+user.getUserID()	+ "\nAPI Token: " + DialogueFlow.getToken()	+ "\nDatabase_URL:" + System.getenv("DATABASE_URL");
			break;
		case "requestID":
			user.param.put("ID",text);
			user.setContext("requestPW");
			result="userID: "+text;
			break;		
		case "requestPW":
			user.param.put("PW",text);
			user.setContext("requestDone");
			result="userPW: "+text;
			break;
		
		default:
			if(text=="sudo login") {
				user.setContext("requestID");
			while(user.param.get("ID")==null && user.param.get("PW")==null) {
				
			}
			if (true) {
				user.setContext("logined");
				result="logined";
			}
			
		}
		}
		
		return result;
	}
	
	public FeatureSudo() {
		
	}
}
