package com.example.bot.spring;
import java.lang.String;
/*
 * Testing feature 
 * 
 *  
*/

public class FeatureFallback implements Features {
	private String result=null;
	public String call(User user, String text) {
		result="Fallback";
		return "Fallback";
	}
	
	public FeatureFallback() {
		
	}
}
