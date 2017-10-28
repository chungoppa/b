package com.example.bot.spring;
import java.lang.String;
/*
 * Testing feature 
 * 
 *  
*/

public class FeatureFallback extends Features {
	@Override
	public String call(String text) {
		result="Fallback";
		return "Fallback";
	}
	public FeatureFallback(User user, String context) {
		super(user,context);
	}
	
	public FeatureFallback(User user) {
		super(user);
	}
	
}
