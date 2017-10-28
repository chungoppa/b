package com.example.bot.spring;

public abstract class Features {
	protected String result="<<default fallback here>>";
	protected String context;
	protected User user;
	
	
	public abstract String call(String text);
	
	public Features(User user, String context) {
		this.user=user;
		this.context=context;
		
	}
	public Features(User user) {
		this.user=user;
		this.context=user.getContext();
		
	}
}