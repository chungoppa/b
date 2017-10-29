/*
* Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
//package ai.api.example;
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
public class TextClientApplication {

  public static void api_call() {
	  String access_token="53bb2989ca424468a365921b5dd10d00";
	  AIConfiguration configuration = new AIConfiguration(access_token);
	  AIDataService dataService = new AIDataService(configuration);
	  java.util.Scanner sc=new java.util.Scanner(System.in);
	  String inputLine="about us";
	  inputLine=sc.next();
	  try {
          AIRequest request = new AIRequest(inputLine);
          AIResponse response = dataService.request(request);
          if (response.getStatus().getCode() == 200) {
        	  System.out.println(response.getResult().getMetadata().getIntentName());
        	  System.out.println(response.getResult().getFulfillment().getSpeech());
            //System.out.println(response.getResult().getFulfillment().getSpeech());
          } else {
            System.err.println(response.getStatus().getErrorDetails());
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
  }
  
}