package com.example.bot.spring;
/*
 * Testing feature 
 * 
 *  
*/

public class FeatureFallback implements Features {
	private String result=null;
	public String call(User user, String text) {
		result="Fallback";
		return result;
	}
	
	public FeatureFallback() {
		
	}
}
