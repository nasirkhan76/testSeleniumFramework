package com.automatedTest.engine;

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
public abstract class Runner {


    public abstract Result run(WebDriver d, HashMap<String,String> params) throws Exception;
    //TODO get rid of Webdriver d as param
}
