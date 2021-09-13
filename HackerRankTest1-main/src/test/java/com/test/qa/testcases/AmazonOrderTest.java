package com.test.qa.testcases;

import java.io.FileNotFoundException;

import com.test.qa.pages.AmazonCartPage;
import com.test.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.qa.base.TestBase;
import com.test.qa.pages.AmazonPage;

public class AmazonOrderTest extends TestBase{
	AmazonPage amazonPage;
	AmazonCartPage amazonCartPage;

	public AmazonOrderTest(){
		super();
	}

	@BeforeClass
	public void setUp() throws FileNotFoundException{
		initialization();
		amazonPage = new AmazonPage();
		amazonCartPage = new AmazonCartPage();
	}

	@Test(priority=1)
	public void loginPageTitleTest(){
		//URL is opened and verify amazon logo
		boolean logo = amazonPage.validateAmazonLogo();
		Assert.assertEquals(logo, true);
	}

	@Test(priority=2)
	public void AddCheapestPhoneToCartAndVerify() throws Exception{
		//Enter mobile phones in searchbox, click search
		amazonPage.enterValueOnSearchBox("Mobile Phones");
		amazonPage.FilterbyPrice("100","400");
		amazonPage.sortByPriceAndFilterMobiles("low");
		String productToVerify = amazonPage.addFirstPhoneToCart();
		System.out.println(productToVerify);
		amazonCartPage.navigateToCart();
		//Verification 1 - to check if Item is added to cart
		Assert.assertTrue(amazonCartPage.isCartPageValidIfItemsInCart());
		//Verification 2 - checking if the same item is added to cart
		Assert.assertTrue(amazonCartPage.isItemPresentInCart(productToVerify));
		TestUtil.takeScreenshotAtEndOfTest();
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}

}
