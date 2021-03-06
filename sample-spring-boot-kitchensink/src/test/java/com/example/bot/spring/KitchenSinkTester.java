package com.example.bot.spring;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.io.ByteStreams;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.example.bot.spring.DatabaseEngine;
import com.example.bot.spring.User;
import com.example.bot.spring.KitchenSinkController;

//Project code
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { KitchenSinkTester.class, DialogueFlow.class,SQLDatabaseEngine.class})
public class KitchenSinkTester {
//	@Autowired
//	private KitchenSinkController ksc;
	@Test
	public void testFeatures() throws Exception {
		boolean thrown = false;
		String result = null;
		User user=new User("123");
		Feature feature=new FeatureDefaultHandler(user);
		result=feature.call("whatever");
		assertThat(result).isEqualTo("<default message>");
	}
	
	@Test
	public void testText() throws Exception {
		String inputText="Sudo";
		User user=new User("Koo Tin Lok");
		String result="nu";
//		result=ksc.testtexttextHandler(inputText, user);
//		assertThat(result).isEqualTo("userID: ");
	}
	
	@Test
	public void testAPI1() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = "Greetings";
			result = DialogueFlow.api_get_intent("sudo login");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("sudo");
	}

/*	
	@Test
	public void testAPI2() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = "Greetings";
			result = DialogueFlow.api_get_intent("fuck");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("test.slang");
	}
*/	
//}
	
	

//LAB2 - code static
/*
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { KitchenSinkTester.class, DatabaseEngine.class })
public class KitchenSinkTester {
	@Autowired
	private DatabaseEngine databaseEngine;
	
	@Test
	public void testNotFound() throws Exception {
		boolean thrown = false;
		try {
			this.databaseEngine.search("no");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isEqualTo(true);
	}
	
	@Test
	public void testFound1() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("abc");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("def");
	}
	@Test
	public void testFound2() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("Hi");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("Hey, how things going?");
	}
}
*/

//LAB3 - code -SQL
	
/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = { KitchenSinkTester.class, SQLDatabaseEngine.class})
public class KitchenSinkTester {*/
	@Autowired
	private SQLDatabaseEngine databaseEngine;
	/*
	@Test
	public void testSQLFound1() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("test");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("remote database");
	}
	
	@Test
	public void testSQLFound2() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("test2access");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("auto-deploy");
	}
	
	@Test
	public void testSQLFound3() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("hi");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("Hey, how things going?");
	}

	@Test
	public void testSQLFound4() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("Hi");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("Hey, how things going?");
	}

	@Test
	public void testSQLFound5() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("HI");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("Hey, how things going?");
	}
	
	@Test
	public void testSQLFound6() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("Request");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("Response");
	}
*/
	
/*	@Test
	public void testSQLNotFound() throws Exception {
		boolean thrown = false;
		try {
			this.databaseEngine.search("this statement");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isEqualTo(true);
	}*/
	
	@Test
	public void keyword1a() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("What is the function of this app?","faq");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("This is a Chatbot developed by 3111 Travel Agency. We provide services including enquiries answering and tour booking through this Chatbot. If you have further opinions about this Chatbot, please contact us by sending email to comp3111gp22@gmail.com. Thank you.");
	}

	@Test
	public void keyword1b() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("I want to know the function of this app.","faq");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("This is a Chatbot developed by 3111 Travel Agency. We provide services including enquiries answering and tour booking through this Chatbot. If you have further opinions about this Chatbot, please contact us by sending email to comp3111gp22@gmail.com. Thank you.");
	}
	
	@Test
	public void keyword1c() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("What is your function?","faq");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("This is a Chatbot developed by 3111 Travel Agency. We provide services including enquiries answering and tour booking through this Chatbot. If you have further opinions about this Chatbot, please contact us by sending email to comp3111gp22@gmail.com. Thank you.");
	}
	
	@Test
	public void keyword1d() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("function?","faq");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("I am not sure about what you mean, but I will forward your question to our agency.");
	}
	
	@Test
	public void keywordFailure() throws Exception {
		boolean thrown = false;
		String result = null;
		try {
			result = this.databaseEngine.search("dsfl,a","faq");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(!thrown).isEqualTo(true);
		assertThat(result).isEqualTo("I am not sure about what you mean, but I will forward your question to our agency.");
	}
}
