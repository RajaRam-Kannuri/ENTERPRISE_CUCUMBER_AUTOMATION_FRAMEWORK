package com.Pages;

import java.io.IOException;

import com.Excel_Operations.DatabankController;
import com.webDriverInteractions.WebDriverInteractions;

public class Aviva_Home_Page {
	
	protected WebDriverInteractions webDriverInteractions;
	DatabankController<String,String> dbController = null;
	
	public enum Aviva_Home_Properties
	{
		AVIVA_TEXT1,
		AVIVA_TEXT2,
		LOGIN,
		ERROR
	}
	
	public Aviva_Home_Page(WebDriverInteractions webDriverInteractions)
	{
		this.webDriverInteractions = webDriverInteractions;
	}
	public String getElementText(String prop) throws IOException
	{
		return webDriverInteractions.captureText(prop);
	}
	public void Click(String prop) throws IOException
	{
		webDriverInteractions.clickUsingJSExecutor(prop);
	}

	
	

}
