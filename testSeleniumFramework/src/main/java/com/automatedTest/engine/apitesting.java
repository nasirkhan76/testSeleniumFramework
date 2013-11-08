package com.automatedTest.engine;

import com.automatedTest.engine.Utilities.Message;
import com.jayway.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Test;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


/**
 * Created with IntelliJ IDEA.
 * User: kirsty.darragh
 * Date: 31/10/13
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */

public class apitesting {
    //@Test
    public void myTest(){
//        try{
//            HttpURLConnection connection = null;
//            String defaultUrl = "http://192.168.6.120:9000/";
//            String paramUrl = "media";
//            URL url = new URL(defaultUrl + paramUrl); //defaultUrl is like http://localhost:8080/index.htbl and paramUrl is parts of api call
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST"); //decides whether request is GET/POST
//            connection.setDoOutput(true);
//            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
//            wr.write(PostData);
//            wr.flush();
//        } catch (Exception ex)  {
//
//        }
        JSONObject json = new JSONObject();

        try {
            json.put("uuid", "78C73DCC-EF81-4D49-9314-A4D616C43333");
            json.put("mduc", "9910");
            json.put("file", "/foo/barr.jj");
            json.put("checksum", "Galaxy1644");
            json.put("mimtype", "image/jpeg");
        } catch (JSONException e1) {

            e1.printStackTrace();
        }



        HttpURLConnection urlConnection = null;
        try {
            String baseUrl = "http://192.168.6.120:9000/";
            String paramUrl="media";
            URL url = new URL(baseUrl + paramUrl);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            OutputStreamWriter os = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            os.write(json.toString());
            os.flush();
            os.close();
            System.out.println(urlConnection.getResponseCode());
            System.out.println(urlConnection.getResponseMessage());
            System.out.println(urlConnection.getInputStream());
        }
        catch (Exception e2){

        }

    }

    //@Test
    public void checkGet(){
        String baseUrl = "http://192.168.6.120:9000/";
        String paramUrl="media/78C73DCC-EF81-4D49-9314-A4D616C43333";
        try {
            expect().body("mduc", equalTo("9910")).when().get(baseUrl + paramUrl);
            System.out.println("Passed");
        }
        catch (AssertionError ae1){
            System.out.println("Failed: " + ae1.toString());
        }


    }
   @Test
    public void checkResponse(){
       String baseUrl = "http://192.168.6.120:9000/";
       String paramUrl="media/78C73DCC-EF81-4D49-9314-A4D616C43333";
       Response response = get(baseUrl + paramUrl);

       String json = response.getBody().asString();
       try {
           JSONObject myJson = new JSONObject(json);
           String myCheckSum = com.jayway.restassured.path.json.JsonPath.from(json).get("checksum");
           String myMduc =  com.jayway.restassured.path.json.JsonPath.from(json).get("mduc");
           int myCounter = myJson.length();

           //List<Integer> winnerIds = from(json).get("lotto.winners.winnerId");
           System.out.println(json);
           System.out.println("Checksum: " + myCheckSum);
           System.out.println("mduc: " + myMduc);
           System.out.println("count: " + myCounter);

           //System.out.println(response.getBody().jsonPath("count"));
       } catch (Exception ej){
           System.out.println("Error creating json " + ej.toString());
       }





    }

    @Test
    public void doPostOther(){
        String baseUrl = "http://192.168.6.120:9000/";
        String paramUrl="media";
//        JSONObject json = new JSONObject();
//
//        try {
//            json.put("uuid", "78C73DCC-EF81-4D49-9314-A4D616C43333");
//            json.put("mduc", "9999");
//            json.put("file", "/foo/barr.jj");
//            json.put("checksum", "GalaxyClass");
//            json.put("mimetype", "image/jpeg");
//        } catch (JSONException e1) {
//
//            e1.printStackTrace();
//        }

//        Message message = new Message();
//        message.setMessage("{\n" +
//                "  \"mduc\": \"9999\",\n" +
//                "  \"mimetype\": \"image/jpeg\",\n" +
//                "  \"file\": \"/foo/barr.jj\",\n" +
//                "  \"uuid\": \"78C73DCC-EF81-4D49-9314-A4D616C43333\",\n" +
//                "  \"checksum\": \"GalaxyClassStarship\"\n" +
//                "}\n");

                String myMessage= ("{\n" +
                "  \"mimetype\": \"image/jpeg\",\n" +
                "  \"mduc\": \"9999\",\n" +
                "  \"file\": \"/foo/barr.jj\",\n" +
                "  \"uuid\": \"78C73DCC-EF81-4D49-9314-A4D616C43333\",\n" +
                "  \"checksum\": \"GalaxyClassStarship1547\"\n" +
                "}\n");

        try {
            Response response =

                    given().
                            contentType("application/json").
                            body(myMessage).
                            when().
                            post(baseUrl + paramUrl);

            System.out.println("Response code " + response.getStatusCode());

            System.out.println("Response body " + response.asString());


        } catch (Exception e){
            System.out.println("Failed: " + e.toString());

        }



        //given().body("{ \"mduc\" : \"3333\"}").expect().contentType("application/json").and().body(equalTo("hello world")).when().post("/jsonBodyAcceptHeader");


    }

    @Test
    public void doPost(){
        String baseUrl = "http://192.168.6.120:9000/";
        String paramUrl="media";
        JSONObject json = new JSONObject();

        try {
            json.put("uuid", "78C73DCC-EF81-4D49-9314-A4D616C43333");
            json.put("mduc", "9999");
            json.put("file", "/foo/barr.jj");
            json.put("checksum", "AlphaCenturi1645");
            json.put("mimetype", "image/jpeg");
            json.put("wombat", "lalala");
        } catch (JSONException e1) {

            e1.printStackTrace();
        }
        System.out.println("Count is " + json.length());
        Message message = new Message();
//        message.setMessage("{\n" +
//                "  \"mduc\": \"9999\",\n" +
//                "  \"mimetype\": \"image/jpeg\",\n" +
//                "  \"file\": \"/foo/barr.jj\",\n" +
//                "  \"uuid\": \"78C73DCC-EF81-4D49-9314-A4D616C43333\",\n" +
//                "  \"checksum\": \"GalaxyClassStarship\"\n" +
//                "}\n");


        given().
                contentType("application/json").
                body(json.toString()).
                expect()
                .statusCode(201).
                when().
                post(baseUrl + paramUrl);




        //given().body("{ \"mduc\" : \"3333\"}").expect().contentType("application/json").and().body(equalTo("hello world")).when().post("/jsonBodyAcceptHeader");


    }




}
