package com.test.qa.pages;

import net.bytebuddy.TypeCache;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.qa.base.TestBase;
import com.test.qa.util.TestUtil;
import org.testng.annotations.Test;

public class AmazonPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(xpath="//a[@id='nav-logo-sprites']")
	WebElement amazonLogo;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchAmazon;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement clickOnSearchIcon;
	
	@FindBy(xpath="(//span[contains(text(),'mobile phones')])[1]")
	WebElement MobilePhoneText;

	@FindBy(xpath="//input[@id='low-price']")
	WebElement MinimumPriceField;

	@FindBy(xpath="//input[@id='high-price']")
	WebElement MaxiumPriceField;

	@FindBy(xpath="//input[@type='submit']")
	WebElement ButtonGo;

	@FindBy(xpath="//span[text()='Sort by:']")
	WebElement SortByField;

	@FindBy(xpath="//a[text()='Price: Low to High']")
	WebElement LowToHigh;

	@FindBy(xpath="//a[text()='Price: High to Low']")
	WebElement HighToLow;

	@FindBy(xpath="//span[text()='SIM-free Mobile Phones & Smartphones']")
	WebElement MobilePhonesFilterButton;

	@FindBy(xpath="//div[@data-index='2' and @data-component-type = 's-search-result']//img")
	WebElement FirstPhoneInResults;

	@FindBy(xpath="//span[@id='productTitle']")
	WebElement ProductTitle;

	@FindBy(xpath="//input[@id='add-to-cart-button']")
	WebElement AddToCartButton;



	//Initializing the Page Objects:
	public AmazonPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	Actions actions = new Actions(driver);

	public boolean validateAmazonLogo(){
		return amazonLogo.isDisplayed();
	}
	
	public void enterValueOnSearchBox(String value) throws InterruptedException {
		//testUtil = new TestUtil();
		TestUtil.clickableElement(searchAmazon, driver);
		TestUtil.PresenceOfElement(By.xpath("//input[@id='twotabsearchtextbox']"), driver);
		searchAmazon.sendKeys(value);
		TestUtil.clickableElement(clickOnSearchIcon, driver);
		clickOnSearchIcon.click();
		Thread.sleep(3000);
	}

	public void FilterbyPrice(String min, String max) throws InterruptedException {
		//moving to Minium price field element
		TestUtil.clickableElement(MinimumPriceField, driver);
		actions.moveToElement(MinimumPriceField).click().perform();
		MinimumPriceField.sendKeys(min);
		TestUtil.clickableElement(MaxiumPriceField, driver);
		MaxiumPriceField.sendKeys(max, Keys.ENTER);
		//Thread.sleep(5000);
	}

	public void sortByPriceAndFilterMobiles(String highOrLow) throws InterruptedException {
		try {
			TestUtil.clickableElement(MobilePhonesFilterButton, driver);
			MobilePhonesFilterButton.click();
		}
		catch(Exception e) {
			System.out.println("Unable to select Mobile phones filter:"+e.getMessage());
		}
		TestUtil.clickableElement(SortByField, driver);
		SortByField.click();
		if(highOrLow.equalsIgnoreCase("low")) {
			TestUtil.clickableElement(LowToHigh, driver);
			LowToHigh.click();
			//Thread.sleep(5000);
		}
		else if (highOrLow.equalsIgnoreCase("high")) {
			TestUtil.clickableElement(HighToLow, driver);
			HighToLow.click();
			//Thread.sleep(5000);
		}
		//driver.findElement(By.xpath("//a[text()='"+highOrLow+"'")).click(); //dynamically selecting High or low based on Sort Criteria
	}
	public String addFirstPhoneToCart() throws Exception{
		TestUtil.PresenceOfElement(By.xpath("//div[@data-cel-widget='search_result_2']//img"), driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@data-cel-widget='search_result_2']//img"))).click().perform();
		TestUtil.PresenceOfElement(By.xpath("//span[@id='productTitle']"), driver);
		String productName = driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
		TestUtil.clickableElement(AddToCartButton, driver);
		AddToCartButton.click();
		return productName;
	}
	public boolean validateMobilePhoneText(){
		return MobilePhoneText.isDisplayed();
	}
}
