package com.example.bot.spring;
import java.lang.String;
/*
 * Testing feature 
 * 
 *  
*/
public class FeatureSudo implements Features {
	private String result=null;

	private void login(User user) {
		String ID=user.param.get("ID");
		String PW=user.param.get("PW");
		result="logined, \n"+ID+"\n"+PW;

	}
	
	public String call(User user, String text) {
		switch(user.getContext()) {
		case "logined" :
			result="User ID: "+user.getUserID()	+ "\nAPI Token: " + DialogueFlow.getToken()	+ "\nDatabase_URL:" + System.getenv("DATABASE_URL");
			break;
		case "requestID":
			user.param.put("ID",text);
			user.setContext("requestPW");
			result="userPW: ";
			break;		
		case "requestPW":
			user.param.put("PW",text);
			user.setContext("requestDone");
			login(user);
			break;
		case "0":
			switch(text) {
			case "sudo login":
				user.setContext("requestID");
				result="userID: ";
				break;
			default:
					
			}
			break;
		default:
			result="rejected";
		}
		
		return result;
	}
	
	public FeatureSudo() {
		
	}
}
