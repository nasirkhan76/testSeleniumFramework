package com.automatedTest.engine.Utilities;




import com.automatedTest.engine.AppProperties;
import com.automatedTest.engine.Result;
import com.automatedTest.engine.Runner;
import com.automatedTest.engine.Test;
import org.openqa.selenium.WebDriver;

import java.sql.*;
import java.util.HashMap;


public class ExecuteSql extends Runner {

    public Result run(WebDriver d, HashMap<String,String> params) throws Exception {

        String sql = "";
        String connectionString = "";
        String user = "";
        String password = "";
        Result executesqlResult = new Result();
        executesqlResult.setOutcome("PASS");

        boolean bContinue = true;
        if (params.containsKey("sql")){
            sql = params.get("sql");
        }
        String columnSeparator="";
        String rowSeparator="";
        if (params.containsKey("rowseparator")){
            rowSeparator = params.get("rowseparator");
        }
        if (params.containsKey("columnseparator")){
            columnSeparator = params.get("columnseparator");
        }
        String commit = "N";
        if (params.containsKey("commit")){
            commit = params.get("commit").toUpperCase();
        }


        if (params.containsKey("connectionstring")){
            connectionString = params.get("connectionstring");
        }

        if (params.containsKey("connectionstring")){
            connectionString = params.get("connectionstring");
        }
        else {
            if (AppProperties.get("brand").toLowerCase().equals("psi")){
                connectionString = AppProperties.get("psiconnectionstring");
            }

        }
        if (params.containsKey("user")){
            user = params.get("user");
        }
        else {
            if (AppProperties.get("brand").toLowerCase().equals("psi")){
                user = AppProperties.get("psiconnectionuser");
            }

        }
        if (params.containsKey("password")){
            password = params.get("password");
        }
        else {
            if (AppProperties.get("brand").toLowerCase().equals("psi")){
                password = AppProperties.get("psiconnectionpassword");
            }

        }


        // Check to see if the jdbc driver is installed. Needs to be located in local maven repository.
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");


        } catch (ClassNotFoundException e) {

            Test.reportEvent("ERROR", "You do not have jdbc driver for Oracle installed.");
            executesqlResult.setOutcome("FAIL");
            bContinue = false;

        }





        if (bContinue) {
            //Set the connection to the database
            String sConnStr = connectionString;
            Connection connection = null;
            Statement statement = null;
            ResultSet searchQuery = null;
//            System.setProperty("oracle.net.tns_admin",
//                    "c:\\oracle\\ora92\\NETWORK\\ADMIN");

            try {
                Test.reportEvent("INFO", "About to open the connection " + sConnStr + " user: " + user + ", password: " + password);

                connection = DriverManager.getConnection(sConnStr, user, password);

            }
            catch (Exception e) {
                executesqlResult.setErrorMessage(e.toString());
                executesqlResult.setOutcome("FAIL");
                bContinue = false;

            }

            if (bContinue) {
                try {
                    //create the statement
                    statement = connection.createStatement();

                    //execute the statement
                    Test.reportEvent("INFO", "About to execute the following sql statement " + sql);
                    searchQuery = statement.executeQuery(sql);

                    //if this is a select query, then read through the result set and output the entire result set.
                    if (sql.substring(0,6).toLowerCase().equals("select")){
                        String searchQueryOutput = "";
                        int numberOfCols = searchQuery.getMetaData().getColumnCount();
                        while (searchQuery.next()) {
                            int i = 1;
                            while (i <= numberOfCols){
                                searchQueryOutput = searchQueryOutput + searchQuery.getString(i) + columnSeparator ;
                                i = i + 1;
                            }
                            searchQueryOutput = searchQueryOutput + rowSeparator;

                        }
                        executesqlResult.setOutput(searchQueryOutput);

                    }

                    if (commit.equals("Y")){
                        statement.executeQuery("commit");
                    }



                }
                catch (Exception f){
                    executesqlResult.setErrorMessage(f.toString());
                    executesqlResult.setOutcome("FAIL");
                    bContinue = false;

                }
                finally{
                    Test.reportEvent("INFO", "Closing connection");
                    //searchQuery.close();
                    statement.close();
                    connection.close();

                }

            }


        }

        return executesqlResult;

    }

}