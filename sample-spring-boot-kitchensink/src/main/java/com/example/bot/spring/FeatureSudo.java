package com.example.bot.spring;
/*
 * Testing feature 
 * 
 *  
*/
public class FeatureSudo implements Features {
	private String result=null;
	public String call(User user, String text) {
		result="sudo";
		return result;
	}
	
	public FeatureSudo() {
		
	}
}
