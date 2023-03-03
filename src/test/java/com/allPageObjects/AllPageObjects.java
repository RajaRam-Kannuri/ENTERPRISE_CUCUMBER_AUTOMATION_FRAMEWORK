package com.allPageObjects;

import com.Excel_Operations.DatabankController;
import com.Pages.AppConstants;
import com.Pages.Aviva_Home_Page;
import com.Pages.Google_Search;
import com.webDriverInteractions.WebDriverInteractions;

/**
 * @Framework Developed by : RAJARAM KANNURI
 */

public class AllPageObjects {
	public WebDriverInteractions webdriverinteractions;
	public Google_Search google;
	public AppConstants appConstants;
	public Aviva_Home_Page avivaHomePage;
	public DatabankController<String,String> dbController = null;
	
	public AllPageObjects() {
		webdriverinteractions = new WebDriverInteractions();
		appConstants = new AppConstants(webdriverinteractions);
		google = new Google_Search(webdriverinteractions);
		avivaHomePage = new  Aviva_Home_Page(webdriverinteractions);
		
	}
	
}
