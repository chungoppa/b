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
	private static String access_token=System.getenv("API_TOKEN");
	private static AIConfiguration configuration = new AIConfiguration(access_token);
	private static AIDataService dataService = new AIDataService(configuration);
	
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
         
         //if (response.getStatus().getCode() == 200) {
         if(! response.isError()) {
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
