package com.expedia.flightsbooking;

import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import pageclasses.SearchPage;

public class TestNG_TestSuite {

	private String baseUrl;
	static Logger log = Logger.getLogger(TestNG_TestSuite.class);
	RemoteWebDriver driver;


	@BeforeClass
	public void beforeClass() throws MalformedURLException {
	    String username = "curtmanning%40ltgc.com"; // Your username
	    String authkey = "ucc45ee689bfd25a";  // Your authkey
		String baseDir= System.getProperty("user.dir");
        DesiredCapabilities caps = new DesiredCapabilities();
        
        caps.setCapability("name", "Login Form Example");
        caps.setCapability("build", "1.0");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "72");
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("screenResolution", "1366x768");
        caps.setCapability("record_video", "true");
        caps.setCapability("record_network", "false");
		
		// System.setProperty("webdriver.gecko.driver", geckoDriver);
		driver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey +"@hub.crossbrowsertesting.com:80/wd/hub"), caps);
		// driver = new FirefoxDriver();
		baseUrl = "https://www.expedia.com/";

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(baseUrl);
	}

	@Test
	public void fillBasicInfo() throws Exception {
		SearchPage.navigateToFlightsTab(driver);
		SearchPage.fillOriginTextBox(driver, "New York");
		SearchPage.fillDestinationTextBox(driver, "Chicago");
		//SearchPage.fillDepartureDateTextBox(driver, "12/25/2020");
		//System.out.println("fillDepartureDateTextBox");
		//SearchPage.fillReturnDateTextBox(driver, "12/31/2020");
		//System.out.println("fillReturnDateTextBox");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

