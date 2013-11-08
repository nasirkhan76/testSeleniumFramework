package com.automatedTest.scriptFunctions;

import com.automatedTest.engine.Result;
import com.automatedTest.engine.Runner;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

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
public class TemplateScriptFunctionToCopy extends Runner {

    public Result run(WebDriver d, HashMap<String,String> params) throws Exception {
        HashMap <String,String> actionParams = new HashMap<String,String>();

        Result res = new Result();
        Result endResult = new Result();
        endResult.setErrorMessage("");
        endResult.setOutcome("PASS");
        boolean bContinue = true;
        // PLEASE ENSURE you add your test to the list of tests in engine.RunnerList. In here you define what
        // "name" to give your script in terms of what to put in the actual test script.

        //(To comment stuff in and out you can use Control+"/" keys)

        //Unpack any passed in parameters here
        //E.g:  for a parameter 'username' which is mandatory:
//        String username = "";
//        if (params.containsKey("username")) {
//            username = params.get("username");
//        }
//        else {
//            endResult.setOutcome("FAIL");
//            endResult.setErrorMessage("Must pass in parameter 'username'");
//            bContinue = false;
//
//        }
        //E.g. for a parameter 'checkhomepage' which is optional and defaults to 'Y':
//        String checkhomepage = "Y";
//        if (params.containsKey("checkhomepage")) {
//            checkhomepage = params.get("checkhomepage");
//        }



        if (bContinue){

            // Now do steps of test in normal way you would in a test script, by calling method
//            //Describe what step is doing, e.g. Check we are on the  page. . .
//            actionParams.clear();    //Always clear these out
//            actionParams.put("comment", "Check we are on page (could be either IIP splash screen or normal website page.)");
//            actionParams.put("bytype", "id");
//            actionParams.put("bytext", "footer_screen");
//            //actionParams.put("byinnertext", ".*Practical Law.*");
//            actionParams.put("action", "exists");
//            actionParams.put("wait", "");
//            res = Test.actOnElement(actionParams, runnerScriptValue);
//            //if the failure of the step means the whole scriptfunction should fail, put in the following. This will accumulate the errors.
//            if (res.getOutcome().equals("FAIL")){
//                endResult.setOutcome("FAIL");
//                endResult.setErrorMessage(endResult.getErrorMessage() + " " + res.getErrorMessage());
//            }

            //The possible action params and the columns they represent in a script are:
            //comment =  comment
            //bytype = Locator type
            //bytext = Locator text
            //byinnertext = Innertext
            //action = Action
            //inputvalue = Input value
            //wait = wait    - must pass this as a string e.g. "0"
            //resultname = resultname - unlikely to need this one


            // If you need to get any output that was returned in a step (e.g. for the gettext action etc), then
            //do:

            //String MyString = res.getOutput();
            //You may prefer to declare your variable MyString earlier and
            //initialise it, e.g. to "".
            //--------------------------------------------------------------------------------------------------
            // If you want to call another scriptFunction or a utility function, please follow the example below:
//            //Set up your parameters:
//            actionParams.clear();
//            actionParams.put("yourparameter1","Your parameter 1 value");
//            actionParams.put("yourparameter2","Your parameter 2 value");
//            //etc
//            //nameofscriptyouwantocall is the name in Runnerlist which maps to the class whose run method
//            //you want to call. E.g. to call engine.Utilities.ExecuteSql.run you would put "executesql" in place
//            //of nameofscriptyouwanttocall below:
//            res = RunnerList.runners.get("nameofscriptyouwanttocall").run(Test.getDriver(), actionParams);
//            if (res.getOutcome().equals("FAIL")){
//                endResult.setOutcome("FAIL");
//                endResult.setErrorMessage(endResult.getErrorMessage() + " " + res.getErrorMessage());
//
//            }



            //-------------------------------------------------------------------------------------------------
            //etc mixture of steps calling actOnElement and scriptFunctions and Utility functions
            //.
            //.
            //If the script is supposed to return an output value you do this as follows:
//            endResult.setOutput(YourStringVariable);
            //
        }
        // Must return the endResult at the end
        return endResult;

    }
}
