package com.example.bot.spring;
import java.lang.String;

/**
 * Handle Default message
 * @author matth
 *
 */
public class FeatureDefaultHandler extends Feature {
	@Override
	public String call(String text) {
		result="<default message>";
		return result;
	}
	public FeatureDefaultHandler(User user, String context) {
		super(user,context);
	}
	
	public FeatureDefaultHandler(User user) {
		super(user);
	}
	
}
