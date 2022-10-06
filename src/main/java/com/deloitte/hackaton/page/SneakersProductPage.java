package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SneakersProductPage extends UserAbstract {

    public static List<String> listOfElements;
    public SneakersProductPage(WebDriver driver, JSONUserData userData){
        super(driver, userData);
        listOfElements = new ArrayList<>();
    }

    public static List<String> getList(){
        return listOfElements;
    }

    @FindBy(id = "product_attribute_28_7_10")
    WebElement sizeDropdown;

    @FindBy(xpath = "//span[@style=\"background-color:#1fcb1a;\"]")
    WebElement colorGreen;

    @FindBy(id = "add-to-cart-button-28")
    WebElement addToCart;

    @FindBy (xpath = "//span[@class=\"close\"]")
    WebElement addToCartConfirmation;

    @FindBy (xpath = "//option[@value=\"28\"]")
    WebElement size;

    @FindBy(xpath = "//*[@id=\"topcartlink\"]/a/span[@class=\"cart-label\"]")
    WebElement goToCartButton;

    @FindBy(xpath = "//*[@id=\"color-squares-11\"]//span[@title=\"Green\"]")
    WebElement greenColor;

    @FindBy(xpath = "//div[@class=\"product-name\"]//h1")
    WebElement shoeName;

    @Step("Product is customized and added to cart")
    public SneakersProductPage selectSize() {
        Select sizeofSneakers = new Select(sizeDropdown);
        sizeofSneakers.selectByVisibleText("11");
        listOfElements.add(size.getText());
        return this;
    }

    @Step("Product is customized and added to cart")
    public SneakersProductPage selectColor() {
        colorGreen.click();
        listOfElements.add(greenColor.getAttribute("title"));
        listOfElements.add(shoeName.getText());
        return this;
    }

    @Step("Product is customized and added to cart")
    public CartPage addToCart(){
        addToCart.click();
        addToCartConfirmation.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", goToCartButton);
        return new CartPage(this.driver, this.userData);
    }
}
