package com.Pages;

import java.io.IOException;

import com.Util.BrowserTasks;
import com.Util.FileOperations;
import com.webDriverInteractions.WebDriverInteractions;

public class AppConstants {
	
	protected WebDriverInteractions webDriverInteractions;
	public enum AppConstant_Properties
	{
		BROWSER,
		URL,
		USERNAME,
		PASSWORD
	}
	public AppConstants(WebDriverInteractions webdriverintractions){
		this.webDriverInteractions=webdriverintractions;
	}
	public void launchBrowser(String browser,String URL ) throws IOException
	{
		webDriverInteractions.get(FileOperations.DerivergetProperty(URL));
		webDriverInteractions.waitForPageLoad();
	}
	
}

