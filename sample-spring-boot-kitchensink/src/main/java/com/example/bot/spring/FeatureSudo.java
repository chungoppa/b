package com.example.bot.spring;
import java.lang.String;
/**
 * Testing Features, a login simple login to get the environment variable
 * @author matth
 *
 */
public class FeatureSudo extends Feature {
	private void login(User user) {
		String ID=user.param.get("ID");
		String PW=user.param.get("PW");
		result="logined, \n"+ID+"\n"+PW;
		user.setContext("sudo_logined");
	}
	
	@Override
	public String call(String text) {
		switch(context) {
		case "none":
			switch(text) {
			case "sudo login":
				user.setContext("sudo_requestID");
				result="userID: ";
				break;
			default:
				result="no context - \n"+text;
			}
			break;
		case "logined" :
			switch(text) {
			case "info":
				result="User ID: "+user.getUserID()	+ "\nAPI Token: " + DialogueFlow.getToken()	+ "\nDatabase_URL:" + System.getenv("DATABASE_URL");
				break;
			case "logout":
				user.setContext("0");
				result="logouted";
			break;
			default:
				result="logined: command not found";
			}
			break;
		case "requestID":
			user.param.put("ID",text);
			user.setContext("sudo_requestPW");
			result="userPW: ";
			break;		
		case "requestPW":
			user.param.put("PW",text);
			user.setContext("sudo_requestDone");
			login(user);
			break;
		default:
			result="rejected";
		}
		
		return result;
	}
	
	public FeatureSudo(User user, String context) {
		super(user,context);
		
	}
	public FeatureSudo(User user) {
		super(user);
	}
}
