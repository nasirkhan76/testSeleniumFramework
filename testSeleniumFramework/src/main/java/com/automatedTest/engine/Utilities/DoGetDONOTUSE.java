package com.automatedTest.engine.Utilities;

import com.automatedTest.engine.Result;
import com.automatedTest.engine.Runner;
import com.automatedTest.engine.Test;
import com.jayway.restassured.response.Response;
import org.concordion.integration.junit4.ConcordionRunner;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.get;

//import utilities.*;
//import static utilities.kirstyutilities.*;

/**
 * Created with IntelliJ IDEA.
 * User: DarragK
 * Date: 17/06/13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */

@RunWith(ConcordionRunner.class)
public class DoGetDONOTUSE extends Runner {

    public Result run(WebDriver d, HashMap<String,String> params) throws Exception {
        HashMap <String,String> actionParams = new HashMap<String,String>();

        Result res = new Result();
        Result endResult = new Result();
        endResult.setErrorMessage("");
        endResult.setOutcome("PASS");
        boolean bContinue = true;

        //Unpack parameters:
        String url = "";
        if (params.containsKey("url")){
            url = params.get("url");

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

//        String baseUrl = "http://192.168.6.120:9000/";
//        String paramUrl="media/78C73DCC-EF81-4D49-9314-A4D616C43333";
        Response response = get(url);
        String returnedBody = response.getBody().asString();
        endResult.setOutput(returnedBody);
        if (params.containsKey("returnbodytojson")){
            String jsonName = params.get("returnbodytojson");

            try {
                JSONObject currentJSON = new JSONObject(returnedBody);
                Test.putGlobalJSONObject(jsonName,currentJSON);
//                String myCheckSum = com.jayway.restassured.path.json.JsonPath.from(json).get("checksum");
//                String myMduc =  com.jayway.restassured.path.json.JsonPath.from(json).get("mduc");
//                int myCounter = myJson.length();

                //List<Integer> winnerIds = from(json).get("lotto.winners.winnerId");
//                System.out.println(json);
//                System.out.println("Checksum: " + myCheckSum);
//                System.out.println("mduc: " + myMduc);
//                System.out.println("count: " + myCounter);

                //System.out.println(response.getBody().jsonPath("count"));
            } catch (Exception ej){
                System.out.println("Error creating json " + ej.toString());
            }

        }





        return endResult;

    }
}
