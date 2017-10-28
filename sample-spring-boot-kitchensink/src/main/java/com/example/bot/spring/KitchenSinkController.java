/*
 * Controller of the application
 */

package com.example.bot.spring;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import com.linecorp.bot.model.profile.UserProfileResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.io.ByteStreams;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.ImagemapMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.imagemap.ImagemapArea;
import com.linecorp.bot.model.message.imagemap.ImagemapBaseSize;
import com.linecorp.bot.model.message.imagemap.MessageImagemapAction;
import com.linecorp.bot.model.message.imagemap.URIImagemapAction;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.HashMap;
@Slf4j
@LineMessageHandler
public class KitchenSinkController {
	public static final boolean DEBUG=true;


	@Autowired
	private LineMessagingClient lineMessagingClient;

	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
		log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		log.info("This is your entry point:");
		log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		TextMessageContent message = event.getMessage();
		handleTextContent(event.getReplyToken(), event, message);
	}
/*
	@EventMapping
	public void handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
		handleSticker(event.getReplyToken(), event.getMessage());
	}

	@EventMapping
	public void handleLocationMessageEvent(MessageEvent<LocationMessageContent> event) {
		LocationMessageContent locationMessage = event.getMessage();
		reply(event.getReplyToken(), new LocationMessage(locationMessage.getTitle(), locationMessage.getAddress(),
				locationMessage.getLatitude(), locationMessage.getLongitude()));
	}

	@EventMapping
	public void handleImageMessageEvent(MessageEvent<ImageMessageContent> event) throws IOException {
		final MessageContentResponse response;
		String replyToken = event.getReplyToken();
		String messageId = event.getMessage().getId();
		try {
			response = lineMessagingClient.getMessageContent(messageId).get();
		} catch (InterruptedException | ExecutionException e) {
			reply(replyToken, new TextMessage("Cannot get image: " + e.getMessage()));
			throw new RuntimeException(e);
		}
		DownloadedContent jpg = saveContent("jpg", response);
		reply(((MessageEvent) event).getReplyToken(), new ImageMessage(jpg.getUri(), jpg.getUri()));

	}

	@EventMapping
	public void handleAudioMessageEvent(MessageEvent<AudioMessageContent> event) throws IOException {
		final MessageContentResponse response;
		String replyToken = event.getReplyToken();
		String messageId = event.getMessage().getId();
		try {
			response = lineMessagingClient.getMessageContent(messageId).get();
		} catch (InterruptedException | ExecutionException e) {
			reply(replyToken, new TextMessage("Cannot get image: " + e.getMessage()));
			throw new RuntimeException(e);
		}
		DownloadedContent mp4 = saveContent("mp4", response);
		reply(event.getReplyToken(), new AudioMessage(mp4.getUri(), 100));
	}

	@EventMapping
	public void handleUnfollowEvent(UnfollowEvent event) {
		log.info("unfollowed this bot: {}", event);
	}

	@EventMapping
	public void handleFollowEvent(FollowEvent event) {
		String replyToken = event.getReplyToken();
		this.replyText(replyToken, "Got followed event");
	}

	@EventMapping
	public void handleJoinEvent(JoinEvent event) {
		String replyToken = event.getReplyToken();
		this.replyText(replyToken, "Joined " + event.getSource());
	}

	@EventMapping
	public void handlePostbackEvent(PostbackEvent event) {
		String replyToken = event.getReplyToken();
		this.replyText(replyToken, "Got postback " + event.getPostbackContent().getData());
	}

	@EventMapping
	public void handleBeaconEvent(BeaconEvent event) {
		String replyToken = event.getReplyToken();
		this.replyText(replyToken, "Got beacon message " + event.getBeacon().getHwid());
	}

	@EventMapping
	public void handleOtherEvent(Event event) {
		log.info("Received message(Ignored): {}", event);
	}
*/
	private void reply(@NonNull String replyToken, @NonNull Message message) {
		reply(replyToken, Collections.singletonList(message));
	}

	private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
		try {
			BotApiResponse apiResponse = lineMessagingClient.replyMessage(new ReplyMessage(replyToken, messages)).get();
			log.info("Sent messages: {}", apiResponse);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	private void replyText(@NonNull String replyToken, @NonNull String message) {
		if (replyToken.isEmpty()) {
			throw new IllegalArgumentException("replyToken must not be empty");
		}
		if (message.length() > 1000) {
			message = message.substring(0, 1000 - 2) + "..";
		}
		this.reply(replyToken, new TextMessage(message));
	}


/*
 	private void handleSticker(String replyToken, StickerMessageContent content) {
		reply(replyToken, new StickerMessage(content.getPackageId(), content.getStickerId()));
	}
*/	
/**
 * Handler of text, but all the structure and changed to String for easier handling
 * 
 * @param replyToken	the token to reply
 * @param text	the text user inputted
 * @param user 	the info of a specif user.
 * 
 * 
 */
	private String texttextHandler(String replyToken, String text,User user) { 
		Features feature=null;
/* Analysis the message */       
        String APIresponse=DialogueFlow.api_get_intent(text);

/*test responsing*/        
        if(text.equals("test")) {
        	this.replyText(replyToken,"ID=\n"+user.getUserID()+"\nText=\n"+text+"\nContext=\n"+user.getContext());
        }        
/*in case of pre-context*/
        
        if(!user.getContext().equals("0")) {
        	String context=user.getContext();
        	String contextFromFeature = context.substring(0, context.indexOf("_"));
        	String contextInFeature = 	context.substring(context.indexOf("_")+1 , context.length());
    		if(DEBUG)lineMessagingClient.pushMessage(new PushMessage(user.getUserID(),new TextMessage("DEBUG:feature\n"+ contextFromFeature+"\nDEBUG:context\n"+contextInFeature)));
        	switch(contextFromFeature) {
        	case "sudo":
        		feature=new FeatureSudo(user,contextInFeature);
        	}
        }else {
/*selecting features from the text context*/
        	if(DEBUG)lineMessagingClient.pushMessage(new PushMessage(user.getUserID(),new TextMessage("DEBUG:APIresponse\n"+ APIresponse)));
	        switch(APIresponse) {
	    	case "sudo":
	    		feature= new FeatureSudo(user);
	    		break;
	    	default:
	    		feature = new FeatureFallback(user);
	        }
        }    
	    return feature.call(text);

	}

/**
 * Directly call replyText() to reply text. anything after replyText() execute will not be run as   
 * the thread will terminate
 * 
 * @param replyToken	toekn from the event
 * @param event	info of event
 * @param content	TextMessageContent: line Struct
 * 
 * 
 * 
 */
	
	private void handleTextContent(String replyToken, Event event, TextMessageContent content)
            throws Exception {
/* Convert TextMessageContent to lowercase text*/
		String text = content.getText();
        text=text.toLowerCase();
        log.info("Got text message from {}: {}", replyToken, text);
        
/* Get the Previous user record or make a new user */        
        String userID = event.getSource().getUserId();
        User user=allUser.get(userID);
        if(user==null){
        	user=new User(userID);
        	allUser.put(userID,user);
        }
        
        String replyStatement=texttextHandler(replyToken, text,user);
        replyText(replyToken,replyStatement);

///* action call to deal with the string get*/        
//       
//        
//        switch(APIresponse) {
//        	case "sudo":
//        		feature= new FeatureSudo();
//        		break;
//        	default:
//        		feature = new FeatureFallback();
//        }
//        
//        replyText(replyToken,feature.call(user,text));
//        
//        switch (text) {
//        	case "sudo login":
//        		break;
//        	case "sudo" :
//        		String para="API Token: "+ DialogueFlow.getToken()
//        				+ "\nDatabase_URL:" + System.getenv("DATABASE_URL");
//        		this.replyText(replyToken, para);
//        		break;
//            case "profile": {
//                String userId = event.getSource().getUserId();
//                if (userId != null) {
//                    lineMessagingClient
//                            .getProfile(userId)
//                            .whenComplete(new ProfileGetter (this, replyToken));
//                } else {
//                    this.replyText(replyToken, "Bot can't use profile API without user ID");
//                }
//                break;
//            }
//            case "confirm": {
//                ConfirmTemplate confirmTemplate = new ConfirmTemplate(
//                        "Do it?",
//                        new MessageAction("Yes", "Yes!"),
//                        new MessageAction("No", "No!")
//                );
//                TemplateMessage templateMessage = new TemplateMessage("Confirm alt text", confirmTemplate);
//                this.reply(replyToken, templateMessage);
//                break;
//            }
//            case "carousel": {
//                String imageUrl = createUri("/static/buttons/1040.jpg");
//                CarouselTemplate carouselTemplate = new CarouselTemplate(
//                        Arrays.asList(
//                                new CarouselColumn(imageUrl, "hoge", "fuga", Arrays.asList(
//                                        new URIAction("Go to line.me",
//                                                      "https://line.me"),
//                                        new PostbackAction("Say hello1",
//                                                           "hello u")
//                                )),
//                                new CarouselColumn(imageUrl, "hoge", "fuga", Arrays.asList(
//                                        new PostbackAction("hello2",
//                                                           "hello 123",
//                                                           "hello 456"),
//                                        new MessageAction("Say message",
//                                                          "Rice=on")
//                                ))
//                        ));
//                TemplateMessage templateMessage = new TemplateMessage("Carousel alt text", carouselTemplate);
//                this.reply(replyToken, templateMessage);
//                break;
//            }
//
//            default:
//                
//              String APIresponse=DialogueFlow.api_get_intent(text);
//              this.replyText(replyToken, APIresponse);
/*              
            	String reply = null;
            	try {
            		reply = database.search(text);
            	} catch (Exception e) {
            		reply = text;
            	}
                log.info("Returns echo message {}: {}", replyToken, reply);
                this.replyText(
                        replyToken,
                        itscLOGIN + " says " + reply
                );
                break;
                
        }
*/	
    }

	static String createUri(String path) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).build().toUriString();
	}

	private void system(String... args) {
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		try {
			Process start = processBuilder.start();
			int i = start.waitFor();
			log.info("result: {} =>  {}", Arrays.toString(args), i);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			log.info("Interrupted", e);
			Thread.currentThread().interrupt();
		}
	}

	private static DownloadedContent saveContent(String ext, MessageContentResponse responseBody) {
		log.info("Got content-type: {}", responseBody);

		DownloadedContent tempFile = createTempFile(ext);
		try (OutputStream outputStream = Files.newOutputStream(tempFile.path)) {
			ByteStreams.copy(responseBody.getStream(), outputStream);
			log.info("Saved {}: {}", ext, tempFile);
			return tempFile;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private static DownloadedContent createTempFile(String ext) {
		String fileName = LocalDateTime.now().toString() + '-' + UUID.randomUUID().toString() + '.' + ext;
		Path tempFile = KitchenSinkApplication.downloadedContentDir.resolve(fileName);
		tempFile.toFile().deleteOnExit();
		return new DownloadedContent(tempFile, createUri("/downloaded/" + tempFile.getFileName()));
	}


	


	public KitchenSinkController() {
		//database = new DatabaseEngine(); // COMMENT_TLKOO
		database = new SQLDatabaseEngine();
		itscLOGIN = System.getenv("ITSC_LOGIN");
	}

	public static HashMap<String,User> allUser=new HashMap<String,User>();
	private DatabaseEngine database;
	private String itscLOGIN;
/**
 * testing interface for texttetHandler
 */
	public String testtexttextHandler(String text,User user) {
		return texttextHandler(null,text,user);
	}
	

	//The annontation @Value is from the package lombok.Value
	//Basically what it does is to generate constructor and getter for the class below
	//See https://projectlombok.org/features/Value
	@Value
	public static class DownloadedContent {
		Path path;
		String uri;
	}


	//an inner class that gets the user profile and status message
	class ProfileGetter implements BiConsumer<UserProfileResponse, Throwable> {
		private KitchenSinkController ksc;
		private String replyToken;
		
		public ProfileGetter(KitchenSinkController ksc, String replyToken) {
			this.ksc = ksc;
			this.replyToken = replyToken;
		}
		@Override
    	public void accept(UserProfileResponse profile, Throwable throwable) {
    		if (throwable != null) {
            	ksc.replyText(replyToken, throwable.getMessage());
            	return;
        	}
        	ksc.reply(
                	replyToken,
                	Arrays.asList(new TextMessage(
                		"Display name: " + profile.getDisplayName()),
                              	new TextMessage("Status message: "
                            		  + profile.getStatusMessage()))
        	);
    	}
    }
	
	

}
