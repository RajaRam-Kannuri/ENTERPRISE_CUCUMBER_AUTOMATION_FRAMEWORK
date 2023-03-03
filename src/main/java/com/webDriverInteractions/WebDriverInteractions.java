
package com.webDriverInteractions;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.Util.BrowserFactory;
import com.byOperations.ByOperations;
import com.google.common.base.Function;


public class WebDriverInteractions {
	private HashMap<String, WebDriver> driverList = new HashMap<String, WebDriver>();
	private HashMap<String, ArrayList<String>> windowHandlesList = new HashMap<String, ArrayList<String>>();

	public WebDriverInteractions() {
	
	}
	public boolean isCurrentDriverNull() {
		if (driverList.get(Thread.currentThread().getName()) == null) {
			return true;
		} else {
			return false;
		}
	}

	private String getCurrentThreadName() {
		try {
			return Thread.currentThread().getName().isEmpty() ? "default" : Thread.currentThread().getName();
		} catch (Exception e) {
			return "default";
		}
	}

	public synchronized WebDriver getCurrentDriver() {

		if (driverList.get(getCurrentThreadName()) == null) {
			windowHandlesList.put(getCurrentThreadName(), new ArrayList<String>());
			windowHandlesList.get(getCurrentThreadName()).clear();
			try {
				driverList.put(getCurrentThreadName(), BrowserFactory.getBrowser());
			} catch (UnreachableBrowserException e) {
				driverList.put(getCurrentThreadName(), BrowserFactory.getBrowser());
			} catch (WebDriverException e) {
				driverList.put(getCurrentThreadName(), BrowserFactory.getBrowser());
			}
			addWindowHandleToArrayList();
		}
		return driverList.get(getCurrentThreadName());
	}
	private void addWindowHandleToArrayList() {
		for (String winHandle : driverList.get(getCurrentThreadName()).getWindowHandles()) {
			if (!windowHandlesList.get(getCurrentThreadName()).contains(winHandle)) {
				windowHandlesList.get(getCurrentThreadName()).add(winHandle);
			}
		}

	}
	public String getElementFromListOnIndex(int index,String prop)
	{
		List<WebElement> elem = findElements(ByOperations.getByargument(prop));
		Iterator<WebElement> iter = elem.listIterator();
		String value="";
		int i =1;
		while(iter.hasNext())
		{
			if(i==index)
			{
				value = iter.next().getText();
				break;
			}
			i++;
		}
		return value;
	}
	public void quitAllBrowsers() throws UnreachableBrowserException {
		try {
			if (driverList.get(getCurrentThreadName()) != null) {
				getCurrentDriver().quit();
				driverList.remove(getCurrentThreadName());
				windowHandlesList.remove(getCurrentThreadName());
			}
		} catch (Exception e) {
		}
	}

	public void clickUsingJSExecutor(String locator) throws IOException {
		clickUsingJSExecutor(findElement(locator));
	}
	public String captureText(String propName) throws IOException {
		String elementValue = "";
		elementValue = findElement(ByOperations.getByargument(propName)).getText();
		return elementValue;
	}
	public FluentWait<WebDriver> getFluentWait() {
		List<Class<? extends Throwable>> ignoreExceptionClasses = new ArrayList<Class<? extends Throwable>>();
		ignoreExceptionClasses.add(NoSuchElementException.class);
		ignoreExceptionClasses.add(ElementNotVisibleException.class);
		ignoreExceptionClasses.add(InvalidElementStateException.class);
		ignoreExceptionClasses.add(StaleElementReferenceException.class);
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getCurrentDriver()).withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).ignoreAll(ignoreExceptionClasses);
			return wait;
		} catch (Exception e) {
		}
		return null;

	}

	
	public void maximize() {
		getCurrentDriver().manage().window().maximize();
	}


	public void clickUsingJSExecutor(WebElement webElement) throws IOException {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getCurrentDriver();
		jsExecutor.executeScript("arguments[0].click();", webElement);
	}

	
	public void click(WebElement webElement) {
		try {
			webElement.click();
		} catch (WebDriverException webDriverException) {
			if (webDriverException.getMessage().contains("Element is not clickable at point")) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) getCurrentDriver();
				jsExecutor.executeScript("arguments[0].click();", webElement);
			} else {
				throw webDriverException;
			}
		}
	}
	public void get(String strURL) {
		getCurrentDriver().get(strURL);
	}

	public void back() {
		getCurrentDriver().navigate().back();
	}

	public void refresh() {
		getCurrentDriver().navigate().refresh();
	}

	public String getCurrentUrl() throws IOException {
		return this.getCurrentDriver().getCurrentUrl();
	}

	public String getCurrentWindowHandle() {
		return getCurrentDriver().getWindowHandle();

	}

	public void switchToWindow(String parentWindow) {
		getCurrentDriver().switchTo().window(parentWindow);
	}

	public void Capturescreenshot (String config)throws AWTException {
	try {
    	Robot robot = new Robot();
    	String format = "bmp";
        String fileName = System.getProperty("user.dir")+"/"+config+"."+format; 
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		ImageIO.write(screenFullImage, "png", new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void waitForPageLoad() {
		String.valueOf(((JavascriptExecutor) getCurrentDriver()).executeScript("return document.readyState"))
							.equals("complete");
				}
			
	public WebElement findElement(final By locator) {
				return getCurrentDriver().findElement(locator);	
	}
	public List<WebElement> findElements(String propName) throws IOException {
		return findElements(propName, false);
	}

	public List<WebElement> findElements(final By locator) {
		return findElements(locator, false);
	}

	public List<WebElement> findElements(String propName, boolean waitForElementsVisibility) throws IOException {
		final By by = ByOperations.getByargument(propName);
		return findElements(by, waitForElementsVisibility);
	}
	public List<WebElement> findElements(final By locator, boolean waitForElementsVisibility) {
							
		return getCurrentDriver().findElements(locator);
	}
	public void sendKeys(String propName, String strValue) throws IOException {
		WebElement webElement = findElement(propName);
		webElement.clear();
		webElement.sendKeys(strValue);
	}
	public void click(String propName) throws IOException {
		By by = ByOperations.getByargument(propName);
		click(by);
	}
	public void clickOnNonHiddenElement(String propName) throws Throwable {
		boolean blnContinueWhile = true;
		while (blnContinueWhile) {
			blnContinueWhile = false;
			try {
				List<WebElement> link = findElements(propName);
				for (int i = 0; i < link.size(); i++) {
					if (link.get(i).isDisplayed()) {
						link.get(i).click();
						break;
					}
				}
			} catch (StaleElementReferenceException staleElementReferenceException) {
				blnContinueWhile = true;
			}
		}
	}

	public void click(By locator) throws IOException {
		
	}
	public void type(String propName, String strValue) throws IOException {
		WebElement webElement = findElement(propName);
		webElement.clear();
		((JavascriptExecutor) getCurrentDriver()).executeScript("arguments[0].value = arguments[1];", webElement,
				strValue);
	}
	public WebElement findElement(String propName) throws IOException {
		final By by = ByOperations.getByargument(propName);
		return findElement(by);
	}
	public void selectDropdownListOptionWithPartialText(List<WebElement> propName, String text) throws Exception {

		List<WebElement> optionsList = propName;
		if(text.contains("_"))
			text = text.replace("_", " ");
		for (WebElement option : optionsList) {
			if (option.getText().contains(text)) {
				option.click();
				break;
			}
		}
	}
	public boolean isOptionPresentInDropDownListOptions(List<WebElement> propName, String optionValue) throws Exception {
		boolean returnValue = false;
		List<WebElement> optionsList = propName;
		for (WebElement option : optionsList) {
			String text = option.getText();
			if (text.equals(optionValue)) {
				returnValue = true;
				break;
			}
		}
		return returnValue;
	}
	public boolean isTextPresent(List<WebElement> propName, String optionValue) throws Exception {
		boolean returnValue = false;
		List<WebElement> optionsList = propName;
		for (WebElement option : optionsList) {
			String text = option.getText();
			if (text.contains(optionValue)) {
				returnValue = true;
				break;
			}
		}
		return returnValue;
	}
	public String getTextFromList(List<WebElement> propName, String optionValue) throws Exception {
		String returnValue = "";
		List<WebElement> optionsList = propName;
		for (WebElement option : optionsList) {
			String text = option.getText();
			if (text.contains(optionValue)) {
				returnValue = text;
				break;
			}
		}
		return returnValue;
	}
	
	public int getRowNumOfOption(List<WebElement> propName, String optionValue) throws Exception {
		int returnRowNum = 0;
		List<WebElement> optionsList = propName;
		for (WebElement option : optionsList) {
			String text = option.getText();
			returnRowNum++;
			if (text.equals(optionValue))
				break;
			
		}
		return returnRowNum;
	}
	public void selectfromList(WebElement propName) throws Exception {
		FluentWait<WebDriver> fluentWait = getFluentWait();
		WebElement option = propName;
		option.click();
	}
	public void scrollwindowdown() {
		try {
			((JavascriptExecutor) getCurrentDriver()).executeScript("window.scrollBy(0,250)", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void scrollwindowUp() {
		try {
			((JavascriptExecutor) getCurrentDriver()).executeScript("window.scrollBy(0,-250)", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	


}
