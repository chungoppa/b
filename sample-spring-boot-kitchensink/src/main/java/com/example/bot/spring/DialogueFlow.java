package com.example.bot.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.*;
/**
* Text client reads requests line by line from stdandart input.
*/


public class DialogueFlow {

	/**
	 * Private access code
	 */
	private static String token="4380fc3bfc8a441a9e38adea08a0369f";
	private static AIConfiguration configuration = new AIConfiguration(token);
	private static AIDataService dataService = new AIDataService(configuration);
	public static String getToken() {return token;}
	
 /**
  * @param args List of parameters:<br>
  *        Parameters String inputText: text input that is used to sent to API.AI<br>
  */	
 public static String api_get_intent(String inputText) {

	  String res="";
	  try {
         AIRequest request = new AIRequest(inputText);
         AIResponse response = dataService.request(request);
         Result result=response.getResult();
         
         if (response.getStatus().getCode() == 200) { 
        	 res= result.getMetadata().getIntentName();
         } else {
        	 res= "fallback";
         }
       } catch (Exception ex) {
         //ex.printStackTrace();
       }
	  return res;
 }
}
