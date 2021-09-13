package com.test.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.test.qa.util.TestUtil;
import com.test.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/test"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization() throws FileNotFoundException{
		String browserName = prop.getProperty("browser");
		
		String chromeFilePath = System.getProperty("user.dir") + "/drivers/MacDrivers/chromedriver";
		File chromeDatafile = new File(chromeFilePath);
		String chromefullpath = chromeDatafile.getAbsolutePath();
		System.out.println("chrome path:- "+chromefullpath);
		
		String fireFoxFilePath = System.getProperty("user.dir") + "/drivers/geckodriver.exe";
		File fireFoxDatafile = new File(fireFoxFilePath);
		String fireFoxFullpath = fireFoxDatafile.getAbsolutePath();
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",  chromefullpath);	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", fireFoxFullpath);	
			driver = new FirefoxDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}

}
