package com.deloitte.hackaton;

import com.deloitte.hackaton.data.JSONDataReader;
import com.deloitte.hackaton.data.product.JSONProductData;
import com.deloitte.hackaton.data.user.JSONUserData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.stream.Stream;

import static com.deloitte.hackaton.utils.TestFactory.*;

public class ReOrderTestSuite {
    WebDriver driver;

    @BeforeAll
    static void setupAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup(){
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        this.driver.quit();
    }

    @ParameterizedTest
    @MethodSource("productsDataStream")
    void reOrder(JSONProductData productData) throws InterruptedException {
        JSONUserData userData = JSONDataReader.readUsers().getUsers().get(0);

        startNewLoginTest(driver, userData)
                .openLoginPage()
                .typeEmail()
                .typePassword()
                .logIn()
                .verifyLogin();
        startNewCustomerInfoTest(driver, userData).openAddressPage().deleteAllAddresses();

        startNewCustomerInfoTest(driver, userData)
                .clickOnAddNewButton()
                .typeFirstName()
                .typeLastName()
                .typeEmail()
                .selectCountry()
                .typeCity()
                .typeAddress1()
                .typePostalCode()
                .typePhoneNumber()
                .addAddress();

        boolean isEmpty = startNewCartTest(driver, productData, userData).checkIfCartEmpty();
        if(isEmpty){
            startNewCartTest(driver, productData, userData).deleteIfNotEmpty();
        }

        startNewProductTest(driver, productData, userData)
                .openProductPage()
                .verifyProductName()
                .verifyAvailability()
                .selectQuantity()
                .orderProduct()
                .verifyNotification();
        startNewCartTest(driver, productData, userData)
                .goToCart()
                .saveProductData()
                .selectCountry()
                .selectState()
                .acceptTerms()
                .goToCheckout()
                .clickThroughPaymentMethods()
                .confirm()
                .validateShippingInfo()
                .validateBillingInfo();

        startNewReOrderTest(driver, productData, userData)
                .openOrdersInfo()
                .detailsButton()
                .clickOnReOrderButton()
                .reOrderCheckData();
        startNewCartTest(driver, productData, userData)
                .acceptTerms()
                .goToCheckout()
                .clickThroughPaymentMethods()
                .confirm()
                .validateBillingInfo()
                .validateShippingInfo();
        startNewLoginTest(driver,userData)
                .verifyLogin();
        startNewReOrderTest(driver, productData, userData)
                .openOrdersInfo()
                .checkOrdersNumberForFirstItem();
    }

    private static Stream<JSONProductData> productsDataStream() {
        return JSONDataReader.readProducts().getProducts().stream();
    }
}