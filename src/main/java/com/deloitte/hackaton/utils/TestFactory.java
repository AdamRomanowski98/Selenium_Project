package com.deloitte.hackaton.utils;
import com.deloitte.hackaton.data.user.JSONInvalidEmails;
import com.deloitte.hackaton.data.product.JSONProductData;
import com.deloitte.hackaton.data.user.JSONUserData;
import com.deloitte.hackaton.page.*;
import com.deloitte.hackaton.page.AddAddressPage;
import com.deloitte.hackaton.page.LoginPage;
import com.deloitte.hackaton.page.RegisterPage;
import org.openqa.selenium.WebDriver;

public class TestFactory {
    public static RegisterPage startNewUserTest(WebDriver driver, JSONUserData userData) {
        return new RegisterPage(driver, userData);
    }

    public static LoginPage startNewLoginTest(WebDriver driver, JSONUserData userData) {
        return new LoginPage(driver, userData);
    }

    public static NewsletterPage startNewNewsletterNegativeTest(WebDriver driver, JSONInvalidEmails mailData){
        return new NewsletterPage(driver, mailData);
    }

    public static MainPage mainPage(WebDriver driver, JSONUserData userData) {
        return new MainPage(driver, userData);
    }

    public static MainPage mainPage(WebDriver driver, JSONProductData productData) {
        return new MainPage(driver, productData);
    }

    public static ContactUsNonLoginUserPage startNewContactUsFormNonLoginTest(WebDriver driver, JSONUserData userData){
        return new ContactUsNonLoginUserPage(driver, userData);
    }

    public static ContactUsLoginUserPage startNewContactUsFormLoginTest(WebDriver driver, JSONUserData userData){
        return new ContactUsLoginUserPage(driver, userData);
    }

    public static AddAddressPage startNewAddressAddTest(WebDriver driver, JSONUserData userData) {
        return new AddAddressPage(driver, userData);
    }

    public static ProductPage startNewProductTest(WebDriver driver, JSONProductData productData, JSONUserData userData){
        return new ProductPage(driver, productData, userData);
    }

    public static RateReviewPage startNewReviewProductTest(WebDriver driver, JSONProductData productData, JSONUserData userData){
        return  new RateReviewPage(driver,productData,userData);
    }

    public static ProductPage startNewProductTest(WebDriver driver, JSONProductData productData){
        return new ProductPage(driver, productData);
    }

    public static CartPage startNewCartTest(WebDriver driver, JSONProductData productData, JSONUserData userData){
        return new CartPage(driver, productData, userData);
    }

    public static CartPage startNewCartTest(WebDriver driver, JSONUserData userData){
        return new CartPage(driver, userData);
    }

    public static CartPage startNewCartTest(WebDriver driver, JSONProductData productData){
        return new CartPage(driver, productData);
    }

    public static JeweleryPage startNewJeweleryTest(WebDriver driver){
        return new JeweleryPage(driver);
    }

    public static ReOrderPage startNewReOrderTest(WebDriver driver, JSONProductData productData, JSONUserData userData){
        return new ReOrderPage(driver, productData, userData);
    }


    public static BooksPage startBooksTest(WebDriver driver){
        return new BooksPage(driver);
    }


    public static GiftCardsPage startGiftCardTest(WebDriver driver, JSONUserData userData){
        return new GiftCardsPage(driver, userData);
    }

    public static SneakersProductPage startSneakersProductTest(WebDriver driver, JSONUserData userData){
        return new SneakersProductPage(driver, userData);
    }

    public static AddressesInfoPage startNewAddressesInfoTest(WebDriver driver, JSONUserData userData){
        return new AddressesInfoPage(driver, userData);
    }

}

