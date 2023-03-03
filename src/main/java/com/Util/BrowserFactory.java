
package com.Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	public static WebDriver driver;
	public static String browserVersion = "";

	/**
	 * This method will open browser according to user argument passed
	 * 
	 * @param browser
	 * @return it will return the driver
	 */
	public static WebDriver getBrowser() {
		String browser = FileOperations.DerivergetProperty("BROWSER");
		switch (browser.toUpperCase()) {
//		case "FIREFOX":
//			driver = firefoxBrowser();
//			break;
		case "CHROME":
			driver = chromebrowser();
			break;
//		case "IE":
//			driver = iEbrowser();
//			break;
//		default:
//			driver = firefoxBrowser();
//			break;

		}
		return driver;

	}

	public static WebDriver iEbrowser() {
//		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
//		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		cap.setCapability("ignoreProtectedModeSettings", true);
//		System.setProperty("webdriver.ie.driver",
//				System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver(cap);
//		String uAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
//		if (uAgent.contains("MSIE") && uAgent.contains("Windows")) {
//			browserVersion = uAgent.substring(uAgent.indexOf("MSIE") + 5, uAgent.indexOf("Windows") - 2);
//		} else if (uAgent.contains("Trident/7.0")) {
//			browserVersion = "11.0";
//		} else {
//			browserVersion = "0.0";
//		}
//		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver chromebrowser() {
		WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

//	public static WebDriver firefoxBrowser() {
//
////		driver = new FirefoxDriver(firefoxDesiredCapabilities());
////		Capabilities capb = ((RemoteWebDriver) driver).getCapabilities();
////		browserVersion = (String) capb.getCapability("browserVersion");
////		driver.manage().window().maximize();zzzzz
//		return driver;
//	}
//
//	public static DesiredCapabilities firefoxDesiredCapabilities() {
//		DesiredCapabilities cap = DesiredCapabilities.firefox();
//		System.setProperty("webdriver.gecko.driver",
//				System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\geckodriver.exe");
//		try {
//			FirefoxProfile profile = new FirefoxProfile();
//			profile.setPreference("dom.popup_maximum", 20);
//			profile.setPreference("browser.popups.showPopupBlocker", false);
//			profile.setPreference("privacy.popups.showBrowserMessage", false);
//			profile.setPreference("dom.popup_allowed_events",
//					"change click dblclick mouseup notificationclick reset submit touchend");
//			profile.setPreference("app.update.enabled", false);
//			profile.setPreference("browser.download.folderList", 2);
//			profile.setPreference("browser.download.manager.showWhenStarting", false);
//			profile.setPreference("browser.download.useDownloadDir", true);
//			profile.setPreference("browser.helperApps.neverAsk.openFile",
//					"text/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel");
//			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
//					"application/pdf, text/csv, application/x-pdf, application/acrobat, applications/vnd.pdf, text/pdf, text/x-pdf, application/octet-stream");
//			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
//			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
//			profile.setPreference("browser.download.manager.focusWhenStarting", false);
//			profile.setPreference("browser.download.manager.useWindow", false);
//			profile.setPreference("browser.download.manager.showAlertOnComplete", false);
//			profile.setPreference("browser.download.manager.closeWhenDone", false);
//			profile.setPreference("browser.download.manager.useWindow", false);
//			profile.setPreference("dom.popup_allowed_events", true);
//			profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
//			profile.setPreference("pdfjs.disabled", true);
//			profile.setAcceptUntrustedCertificates(true);
//			profile.setAssumeUntrustedCertificateIssuer(false);
//			cap.setCapability(FirefoxDriver.PROFILE, profile);
//			return cap;
//		} catch (Exception e) {
//		}
//		return cap;
//	}
}