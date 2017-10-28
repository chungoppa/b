package com.example.bot.spring;

public abstract class Feature {
	protected String result="<<default fallback here>>";
	protected String context;
	protected User user;
	
	
	public abstract String call(String text);
	
	public Feature(User user, String context) {
		this.user=user;
		this.context=context;
		
	}
	public Feature(User user) {
		this.user=user;
		this.context=user.getContext();
		
	}
}