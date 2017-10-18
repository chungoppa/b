package com.example.bot.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

/**
* Text client reads requests line by line from stdandart input.
*/


public class DialogueFlow {

 /**
  * @param args List of parameters:<br>
  *        First parameters should be valid api key<br>
  *        Second and the following args should be file names containing audio data.
  */
 public static String api_get_intent(String inputText) {
	  String access_token="067909a52c4e454596d1f3b5385c65fa";
	  AIConfiguration configuration = new AIConfiguration(access_token);
	  AIDataService dataService = new AIDataService(configuration);
	  String res="";
	  try {
         AIRequest request = new AIRequest(inputText);
         AIResponse response = dataService.request(request);
         if (response.getStatus().getCode() == 200) {
        	 res= response.getResult().getMetadata().getIntentName();
         } else {
        	 res= "fallback";
         }
       } catch (Exception ex) {
         //ex.printStackTrace();
       }
	  return res;
 }
}
