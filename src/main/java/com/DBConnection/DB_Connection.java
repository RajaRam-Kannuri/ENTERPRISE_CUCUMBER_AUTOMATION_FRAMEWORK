package com.DBConnection;

import java.sql.Connection;


public class DB_Connection {
	private static Connection connection = null;
	public static Connection get_dbConnection(String url,String userName,String password,String driverType)
	{
		
	        try {
                if(driverType.equalsIgnoreCase("Oracle"))
                {
                	Class.forName("oracle.jdbc.driver.OracleDriver");
                }
                else if(driverType.equalsIgnoreCase("Mysql"))
                {
                	 Class.forName("com.mysql.jdbc.Driver");
                }
                else if (driverType.equalsIgnoreCase("SQLServer"))
                {
                	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                }
	        } catch (ClassNotFoundException e) {

	            System.out.println("Where is your Oracle JDBC Driver?");
	            e.printStackTrace();
	            return null;

	        }

	        System.out.println("JDBC Driver Registered!");

	        if (connection != null) {
	            System.out.println("You made it, take control your database now!");
	        } else {
	            System.out.println("Failed to make connection!");
	        }
	    
		return connection;
	}
	
	public static void close_Db()
	{
		connection = null;
	}

}
