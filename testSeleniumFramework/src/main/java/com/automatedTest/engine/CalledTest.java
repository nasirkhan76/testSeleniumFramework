package com.automatedTest.engine;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: DarragK
 * Date: 11/10/13
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
@RunWith(ConcordionRunner.class)
public class CalledTest extends StepBase{
    public CalledTest(){
       setTestType("CALLEDTEST");
    }

}
