package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.product.JSONProductData;
import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartPage extends ProductAbstract{

    @FindBy(xpath = "//input[@class='qty-input']")
    WebElement quantity;

    @FindBy(css = ".product-unit-price")
    WebElement price;

    @FindBy(css = ".product-subtotal")
    WebElement totalPrice;

    @FindBy(xpath = "//a[@class='product-name']")
    WebElement itemName;

    @FindBy(xpath = "//*[@id=\"CountryId\"]")
    WebElement countryDropDown;

    @FindBy(xpath = "//*[@id=\"StateProvinceId\"]")
    WebElement stateDropdown;

    @FindBy(xpath = "//*[@id=\"termsofservice\"]")
    WebElement termsServiceButton;

    @FindBy(xpath = "//*[@id=\"checkout\"]")
    WebElement checkoutButton;

    @FindBy(xpath = "//*[@id=\"topcartlink\"]/a/span[@class=\"cart-label\"]")
    WebElement cartButton;

    @FindBy(xpath = "//tr[@class=\"cart-item-row\"][2]//input[@name=\"removefromcart\"]")
    WebElement deleteItemCheckbox;

    @FindBy(xpath = "//input[@name='updatecart']")
    WebElement updateCart;

    @FindBy(xpath = "//div[@class=\"ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-draggable ui-resizable\"]")
    WebElement widget;

    @FindBy(xpath = "//div[@id=\"terms-of-service-warning-box\"]//p")
    WebElement errorMessage;

    public static List<String> productDataElements;

    public CartPage(WebDriver driver, JSONProductData productData, JSONUserData userData) {
        super(driver, productData);
        this.userData = userData;
        productDataElements = new ArrayList<>();
    }

    public CartPage(WebDriver driver,JSONUserData userData) {
        super(driver, userData);
        productDataElements = new ArrayList<>();
    }

    public CartPage(WebDriver driver, JSONProductData productData){
        super(driver, productData);
    }

    public static List<String> getList(){
        return productDataElements;
    }


    @Step("Delete product if cart not empty")
    public boolean checkIfCartEmpty(){
        cartButton.click();
        try{
            boolean doesExist =  driver.findElements(By.xpath("//div[@class=\"order-summary-content\"][contains(text(), \"Your Shopping Cart is empty!\")]")).isEmpty();
            return doesExist;
        }catch (NoSuchElementException e) {
            System.out.println("Such element doesn't exist");
            return false;
        }
    }

    public CartPage deleteIfNotEmpty() {
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@name='removefromcart']"));
        for(WebElement checkbox : checkBoxes){
            checkbox.click();
        }
        updateCart.click();
        try{
            boolean doesExist =  !driver.findElements(By.xpath("//div[@class=\"order-summary-content\"][contains(text(), \"Your Shopping Cart is empty!\")]")).isEmpty();
            assertTrue(doesExist);
        }catch (NoSuchElementException e){
            System.out.println("Such element doesn't exist");
        }
        return this;
    }

    @Step("Go to cart page")
    public CartPage goToCart(){
        cartButton.click();
        return this;
    }

    @Step("Delete product from shipping cart")
    public CartPage deleteProduct(){
        deleteItemCheckbox.click();
        return this;
    }

    @Step("Update cart")
    public CartPage updateCart(){
        updateCart.click();
        return this;
    }


    @Step("Verify product data")
    public CartPage saveProductData(){
        productDataElements.add(itemName.getText());
        productDataElements.add(quantity.getAttribute("value"));
        productDataElements.add(price.getText());
        productDataElements.add(totalPrice.getText());
        return this;
    }
    @Step("Select country from dropdown")
    public CartPage selectCountry(){
        Select countryDropDownSelect = new Select(countryDropDown);
        countryDropDownSelect.selectByVisibleText("United States");
        String text = countryDropDownSelect.getAllSelectedOptions().stream().iterator().next().getText();
        assertEquals("United States", text);
        WebElement unitedStates = driver.findElement(By.xpath("//*[@id=\"CountryId\"]//option[@value=\"1\"]"));
        assertTrue(unitedStates.isSelected());
        return this;
    }

    @Step ("Select users country")
    public CartPage selectUsersCountry(){
        Select countryDropDownSelect = new Select(countryDropDown);
        countryDropDownSelect.selectByVisibleText(userData.getCountry());
        String text = countryDropDownSelect.getAllSelectedOptions().stream().iterator().next().getText();
        assertEquals(text, getUserData().getCountry());
        return this;
    }

    @Step("Select Alaska from dropdown")
    public CartPage selectState(){
        Select stateDropDownSelect = new Select(stateDropdown);
        stateDropDownSelect.selectByVisibleText("Alaska");
        String text = stateDropDownSelect.getAllSelectedOptions().stream().iterator().next().getText();
        assertEquals("Alaska", text);
        WebElement alaska = driver.findElement(By.xpath("//*[@id=\"StateProvinceId\"]/option[@value=\"4\"]"));
        assertTrue(alaska.isSelected());
        return this;
    }

    @Step("Accept terms")
    public CartPage acceptTerms(){
        termsServiceButton.click();
        return this;
    }

    @Step("Click checkout button")
    public CartPage clickCheckoutButton(){
        checkoutButton.click();
        return this;
    }

    @Step("Go to checkout")
    public CheckoutPage goToCheckout(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
        return new CheckoutPage(this.driver, this.productData, this.userData);
    }

    @Step("Verify checkout without accepting terms")
    public CartPage validateCheckout(){
        String display = widget.getCssValue("display");
        assertEquals(display, "block");
        assertEquals(errorMessage.getText(), "Please accept the terms of service before the next step.");
        return this;
    }

}