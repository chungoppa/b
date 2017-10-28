package com.example.bot.spring;
import java.util.HashMap;
import java.lang.String;

public class User {
//	public static UserCollection allUser=new UserCollection();
	private String context="none";
	private String intent="none";
	private String userID;
	private String userName="0";
	public HashMap<String,String> param=new HashMap<String,String>();
	
	public String getContext() {return this.context;}
	public String getIntent() {return this. intent;}
	public String getUserID() {return this. userID;}
	public String getUserName() {return this. userName;}
	
	public void setContext(String context) {this.context=context;}
	public void setIntent(String intent) {this.intent=intent;}
	
	public User(String userID){
		this.userID=userID;
		this.context="none";
	}
}