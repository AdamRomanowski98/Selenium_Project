package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.product.JSONProductData;
import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends ProductAbstract {

    JSONProductData productData;

    public static List<String> listOfElementsInProductDetails;

    public MainPage(WebDriver driver, JSONUserData userData){
        super(driver, userData);
        listOfElementsInProductDetails = new ArrayList<>();
    }

    public static List<String> getList(){
        return listOfElementsInProductDetails;
    }

    public MainPage(WebDriver driver, JSONProductData productData){
        super(driver, productData);
    }

    public MainPage(WebDriver driver, JSONProductData productData, JSONUserData userData){
        super(driver, userData);
        this.productData = productData;
    }

    @FindBy(xpath = "//a[@href=\"/gift-cards\"]")
    WebElement giftCards;

    @FindBy(id = "small-searchterms")
    WebElement searchField;

    @FindBy(xpath = "//div[@data-productid=\"28\"]/div[@class=\"details\"]/h2[@class=\"product-title\"]")
    WebElement blueAndGreenSneaker;

    @FindBy(xpath = "//div[@data-productid=\"31\"]/div[@class=\"details\"]/h2[@class=\"product-title\"]")
    WebElement laptop14inch;

    @FindBy(id = "add-to-cart-button-31")
    WebElement laptop14inchAddToCart;

    @FindBy (xpath = "//p[@class=\"content\"]")
    WebElement addToCartConfirmation;

    @FindBy(xpath = "//*[@id=\"topcartlink\"]/a/span[@class=\"cart-label\"]")
    WebElement goToCartButton;

    @FindBy (xpath = "//h1[@itemprop=\"name\"]")
    WebElement nameInProductDetails;

    @FindBy (xpath = "//span[@itemprop=\"price\"]")
    WebElement priceInProductDetailsPage;

    @FindBy (xpath = "//input[@data-val-number=\"The field Qty must be a number.\"]")
    WebElement quantityInProductDetails;


    public MainPage navigateToMainPage() {
        driver.get(getBaseUrl());
        return this;
    }

    public GiftCardsPage goToGiftCards() {
        giftCards.click();
        return new GiftCardsPage(driver, userData);
    }

    public SneakersProductPage searchForBlueAndGreenSneaker() {
        searchField.sendKeys("blue and green sneaker");
        searchField.sendKeys(Keys.RETURN);
        blueAndGreenSneaker.click();
        return new SneakersProductPage(driver, userData);
    }

    @Step ()
    public MainPage searchFor14laptop() {
        searchField.sendKeys("14.1-inch Laptop");
        searchField.sendKeys(Keys.RETURN);
        laptop14inch.click();
        return this;
    }

    public MainPage getElementsFromProductDetails(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(nameInProductDetails));
        listOfElementsInProductDetails.add(nameInProductDetails.getText());
        listOfElementsInProductDetails.add(priceInProductDetailsPage.getText());
        listOfElementsInProductDetails.add(quantityInProductDetails.getAttribute("value"));
        return this;
    }

    public CartPage laptop14inchAddToCart(){
        laptop14inchAddToCart.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addToCartConfirmation));
        goToCartButton.click();
        return new CartPage(this.driver, this.productData, this.userData);
    }

}








