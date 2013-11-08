package com.automatedTest.engine.Utilities;

import com.automatedTest.engine.Result;
import com.automatedTest.engine.Runner;
import com.automatedTest.engine.Test;
import com.jayway.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: KirstyDarragh
 * Date: 03/11/13
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class httpAction extends Runner {
    public Result run(WebDriver d, HashMap<String,String> params) throws Exception {
        HashMap <String,String> actionParams = new HashMap<String,String>();

        Result res = new Result();
        Result endResult = new Result();
        endResult.setErrorMessage("");
        endResult.setOutcome("PASS");
        boolean bContinue = true;

        //Unpack parameters
        String action = "get";   //default to get
        if (params.containsKey("action")){
            action = params.get("action").toUpperCase();
        }

        String url = "";
        if (params.containsKey("url")){
            url = params.get("url");
        }
//        String baseUrl = "http://192.168.6.120:9000/";
//        String paramUrl="media";
        String body = "";
        if (params.containsKey("bodyasjson")){
            String jsonName = params.get("bodyasjson");
            JSONObject json = Test.getGlobalJSONObject(jsonName);
            body = json.toString();
        }
        else if (params.containsKey("bodyasstring")){
            body = params.get("bodyasstring");
        }

        String contentType = "";
        if (params.containsKey("contenttype")){
            contentType = params.get("contenttype");
        }

        String expectedResponseCode = "";
        boolean hasExpectedResponseCode = false;
        if (params.containsKey("expectedresponsecode")){
            expectedResponseCode = params.get("expectedresponsecode");
            hasExpectedResponseCode = true;
        }

        String expectedResponseBody = "";
        boolean hasExpectedResponseBody = false;
        if (params.containsKey("expectedresponsebody")){
            expectedResponseBody = params.get("expectedresponsebody");
            hasExpectedResponseBody = true;
        }

        try {
            Response response = null;
            if (action.equalsIgnoreCase("POST")){
                response =

                        given().
                                contentType(contentType).
                                body(body).
                                when().
                                post(url);
            }
            else if (action.equalsIgnoreCase("GET")){
                response =

                        given().
                                contentType(contentType).
                                when().
                                get(url);
            }



            String responseCode = String.valueOf(response.getStatusCode());
            String responseBody = response.asString();
            if (params.containsKey("returnbodytojson")){
                String jsonName = params.get("returnbodytojson");

                try {
                    JSONObject currentJSON = new JSONObject(responseBody);
                    Test.putGlobalJSONObject(jsonName,currentJSON);

                } catch (Exception ej){
                    System.out.println("Error creating json " + ej.toString());
                }

            }

            if (hasExpectedResponseCode){
                if (!responseCode.matches(expectedResponseCode)){
                    endResult.setOutcome("FAIL");
                    endResult.setErrorMessage(endResult.getErrorMessage() + "Expected response code '" + expectedResponseCode + "' does not match actual response code '" + responseCode + "'. ");
                }
            }

            if (hasExpectedResponseBody){
                if (!responseBody.matches(expectedResponseBody)){
                    endResult.setOutcome("FAIL");
                    endResult.setErrorMessage(endResult.getErrorMessage() + "Expected response body '" + expectedResponseBody + "' does not match actual response body '" + responseBody + "'. ");
                }
            }
            endResult.setOutput(responseBody); //Return response body for now. . .


        } catch (Exception e){
            endResult.setOutcome("FAIL");
            endResult.setErrorMessage(e.toString());

        }

        return endResult;

    }
}
