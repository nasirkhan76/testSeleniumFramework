package com.automatedTest.engine;



import com.automatedTest.engine.Utilities.httpAction;
import com.automatedTest.engine.Utilities.ExecuteSql;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: DarragK
 * Date: 03/07/13
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
public class RunnerList {
    public static  HashMap <String,Runner> runners = new HashMap<String, Runner>();
    static {
        runners.put("executesql" , new ExecuteSql());
        runners.put("httpaction" , new httpAction());
    }

}
