package com.stepdefs;

import org.junit.Assert;
import com.Excel_Operations.DatabankController;
import com.Pages.AppConstants.AppConstant_Properties;
import com.Pages.Aviva_Home_Page;
import com.Pages.Aviva_Home_Page.Aviva_Home_Properties;
import com.Pages.Google_Search.Google_Search_Properties;
import com.allPageObjects.AllPageObjects;
import io.cucumber.java.en.*;


public class MODULE_1_STEP_DEF {

	public AllPageObjects allPageObjects = null;
	public static DatabankController<String, String> dbController = null;

	public MODULE_1_STEP_DEF(AllPageObjects allPageObjects) {
		this.allPageObjects = allPageObjects;
	}

	
	
	@Given("^Launch the URL \"([^\"]*)\"$")
	public void launch_the_URL(String URL) throws Throwable {
		allPageObjects.appConstants.launchBrowser(AppConstant_Properties.BROWSER.toString(),AppConstant_Properties.URL.toString());
	}

	@Given("^Load the Databank with Row\"([^\"]*)\" FileName\"([^\"]*)\" and SheetName\"([^\"]*)\"$")
	public static DatabankController<String, String> load_the_Databank_with_Row_FileName_and_SheetName(String row,
			String fileName, String SheetName) throws Throwable {
		dbController = DatabankController.getdataBankCounterRecord(row, fileName, SheetName);
		return dbController;
	}

	@When("^Enter the Text \"([^\"]*)\"$")
	public void enter_the_Text(String input) throws Throwable {
		input = dbController!=null?dbController.get(input): input;	
		allPageObjects.google.SendKeys(Google_Search_Properties.GOOGLE_SEARCH.toString(), input);
	}

	@When("^click on button \"([^\"]*)\"$")
	public void click_on_button(String input) throws Throwable {

		allPageObjects.google.Click(Google_Search_Properties.SEARCH_BUTTON.toString());
	}

	@Then("^application should display links returned in resultspage and verify \"([^\"]*)\"$")
	public void application_should_display_links_returned_in_resultspage_and_verify(String expected) throws Throwable {
		expected = dbController != null ? dbController.get(expected) : expected;
		int actual = allPageObjects.google.getElementsCount(Google_Search_Properties.ALL_LINKS_PATH.toString());
		Assert.assertEquals(Integer.parseInt(expected), actual);
		
		System.out.println("Expected :" + expected + " EQUALS ACTUAL :" + actual);
	}

	@Then("^user should display \"([^\"]*)\" link$")
	public void user_should_display_link(String index) throws Throwable {
		index = dbController != null ? dbController.get(index) :index;
		String link = allPageObjects.google.getElementonIndex(Google_Search_Properties.ALL_LINKS_PATH.toString(),
					   index);
		
		System.out.println("THe " + index + "link is:" + link);
	}

	@Then("^click on Aviva Login Link in the Google Search Page$")
	public void click_on_Aviva_Login_Link_in_the_Google_Search_Page() throws Throwable {
		allPageObjects.google.Click(Google_Search_Properties.AVIVA_LOGIN_LINK.toString());
		allPageObjects.webdriverinteractions.waitForPageLoad();
	}

	@Then("^verify Title of the \"([^\"]*)\" of the Aviva Home Page$")
	public void verify_Title_of_the_of_the_Aviva_Home_Page(String expected) throws Throwable {
		expected = dbController != null ? dbController.get(expected) : expected;
		String actual = allPageObjects.avivaHomePage.getElementText(Aviva_Home_Page.Aviva_Home_Properties.AVIVA_TEXT2.toString());
		
		System.out.println("Expected :" + expected + " EQUALS ACTUAL :" + actual);
	}

	@Then("^click on Aviva Login Link in the Google Search Page and click on Login$")
	public void click_on_Aviva_Login_Link_in_the_Google_Search_Page_and_click_on_Login() throws Throwable {
		allPageObjects.google.Click(Google_Search_Properties.AVIVA_LOGIN_LINK.toString());
		allPageObjects.google.Click(Aviva_Home_Properties.LOGIN.toString());
	}

	@Then("^verify the Error Message in the Aviva Login Page\"([^\"]*)\"$")
	public void verify_the_Error_Message_in_the_Aviva_Login_Page(String expectedError) throws Throwable {
		expectedError = dbController != null ? dbController.get(expectedError) : expectedError;
		String actual_Error = allPageObjects.avivaHomePage.getElementText(Aviva_Home_Properties.ERROR.toString());
		
		System.out.println("Expected :" + expectedError + " EQUALS ACTUAL :" + actual_Error);
	}
}
