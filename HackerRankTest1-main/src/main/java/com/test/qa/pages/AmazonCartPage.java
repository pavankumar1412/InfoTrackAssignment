/* This page ie added to maintain webelements and method related to Cart Page */
package com.test.qa.pages;

import com.test.qa.base.TestBase;
import com.test.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonCartPage extends TestBase {

    @FindBy(xpath="//a[@id='nav-cart']")
    WebElement CartButton;

    @FindBy(xpath="//h1[contains(text(),'Shopping Cart')]")
    WebElement ShoppingCartHeading;


    public AmazonCartPage(){
        PageFactory.initElements(driver, this);
    }

    public void navigateToCart() throws Exception {
        TestUtil.clickableElement(CartButton, driver);
        CartButton.click();
        Thread.sleep(3000);
    }

    public boolean isItemPresentInCart(String itemName) throws Exception{
        try {
            TestUtil.PresenceOfElement(By.xpath("//span[contains(text(),'" + itemName + "')]"), driver);
            return true;
        }
        catch(Exception e){
            System.out.println("Could not find the element with exception:"+e.getMessage());
            return false;
        }
    }

    public boolean isCartPageValidIfItemsInCart() throws Exception {
        return ShoppingCartHeading.isDisplayed();//If Item is not added to cart, this heading will not be displayed.
    }
}
