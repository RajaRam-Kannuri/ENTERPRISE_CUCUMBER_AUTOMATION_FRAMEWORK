package com.Pages;

import java.io.IOException;

import com.Excel_Operations.DatabankController;
import com.webDriverInteractions.WebDriverInteractions;

public class Google_Search{

	protected WebDriverInteractions webDriverInteractions;
	public static DatabankController<String,String> dbController = null;
	public enum Google_Search_Properties {
		GOOGLE_SEARCH,
		SEARCH_BUTTON,
		ALL_LINKS_PATH,
		AVIVA_LOGIN_LINK
	}
	
	public Google_Search( WebDriverInteractions webdriverintractions){
		this.webDriverInteractions=webdriverintractions;
		
	}

	public void SendKeys(String prop,String value) throws IOException
	{    	
		webDriverInteractions.sendKeys(prop,value);
	}
	public void Click(String prop) throws IOException
	{
		webDriverInteractions.clickUsingJSExecutor(prop);
	}
	public int getElementsCount(String prop) throws IOException
	{
		return webDriverInteractions.findElements(prop).size();
	}
	public String getElementonIndex(String prop,String index) throws IOException
	{
		return webDriverInteractions.getElementFromListOnIndex(Integer.parseInt(index),prop);
	}
	
	
}
