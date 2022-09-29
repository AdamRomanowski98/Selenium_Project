package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GiftCards extends UserAbstract {


    WebDriver driver;

    public GiftCards(WebDriver driver, JSONUserData userData){
        super(driver, userData);
    }


    @FindBy(id = "giftcard_4_RecipientName")
    WebElement recipientsName;

    @FindBy(id = "giftcard_4_SenderName")
    WebElement yourName;

    @FindBy(id = "giftcard_4_Message")
    WebElement giftCardMessage;

    @FindBy(id = "add-to-cart-button-4")
    WebElement addToCart;

    @FindBy (xpath = "//*[@id=\"topcartlink\"]/a/span[1]")
    WebElement goToCart;

    @FindBy (xpath = "//p[@class=\"content\"]")
    WebElement addToCartConfirmation;

    @FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[3]/div[4]/div/div[1]/a/img")
    WebElement physicalGiftCard100$;




    @Step ("locate Physical Gift Card and go to product details")
    public GiftCards locatePhisicalGiftCard100$(){
        physicalGiftCard100$.click();
        return this;
    }
    @Step("Provide recipient info & write a message and add to cart")
    public GiftCards provideInfoAndAddToCart() {
        recipientsName.sendKeys("Flip");
        yourName.sendKeys("Flap");
        giftCardMessage.sendKeys("This is the message");
        addToCart.click();
        addToCartConfirmation.click();
        return this;
    }


}
