package com.unimelb.npd.games.tools;


import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties prop = new Properties();
	static{
		try {
			prop.load(Config.class.getResourceAsStream("dbconfig.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static final String SERVER_IP = prop.getProperty("SERVER_IP");
	public static final String SERVER_PORT = prop.getProperty("SERVER_PORT");
	public static final String SERVER_NAME = prop.getProperty("SERVER_NAME");
	public static final String LOGIN = prop.getProperty("LOGIN");
	public static final String GAMERECORD = prop.getProperty("GAMERECORD");
	
}